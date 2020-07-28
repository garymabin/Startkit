package com.thoughtworks.android.startkit.douban.movie.intereactor;

import com.thoughtworks.android.startkit.AppDatabase;
import com.thoughtworks.android.startkit.AppExecutors;
import com.thoughtworks.android.startkit.douban.movie.data.persistence.IMovieDao;
import com.thoughtworks.android.startkit.douban.movie.data.persistence.impl.RoomMovie;
import com.thoughtworks.android.startkit.douban.movie.data.persistence.record.IMovieRecord;
import com.thoughtworks.android.startkit.douban.movie.data.vo.DouBanMovie;
import com.thoughtworks.android.startkit.douban.movie.data.vo.DouBanMovieResponseData;
import com.thoughtworks.android.startkit.douban.movie.data.vo.MovieData;
import com.thoughtworks.android.startkit.douban.movie.data.vo.MovieItem;
import com.thoughtworks.android.startkit.douban.movie.repository.IDoubanMovieRepository;
import com.thoughtworks.android.startkit.retrofit.ApiResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

public class DoubanMovieInteractor {

    IMovieDao<RoomMovie> movieDao;

    IDoubanMovieRepository doubanMovieRepository;

    AppExecutors appExecutors;

    @Inject
    DoubanMovieInteractor(AppDatabase appDatabase, IDoubanMovieRepository doubanMovieRepository, AppExecutors appExecutors) {
        this.doubanMovieRepository = doubanMovieRepository;
        this.appExecutors = appExecutors;
        this.movieDao = appDatabase.movieDao();
    }

    private MediatorLiveData<MovieData> result = new MediatorLiveData<>();

    public LiveData<MovieData> loadMovie(int startIndex) {
        LiveData<List<RoomMovie>> dataFromDB = movieDao.getAll();
        result.addSource(dataFromDB, data -> {
            result.removeSource(dataFromDB);
            if (shouldFetchFromNetwork(data, startIndex)) {
                loadFromNetwork(dataFromDB, startIndex);
            } else {
                result.addSource(dataFromDB, newData -> {
                    List<MovieItem> list = new ArrayList<>();
                    for (IMovieRecord record : newData) {
                        list.add(new DouBanMovie(record));
                    }
                    // handle more logic about loading from database
                    result.setValue(new MovieData(list, list.size(), list.size(), 0));
                });
            }
        });
        return result;
    }

    private void loadFromNetwork(LiveData<List<RoomMovie>> dataFromDB, int startIndex) {
        LiveData<ApiResponse<DouBanMovieResponseData>> dataFromNetwork = doubanMovieRepository.getMovie(startIndex);
        result.addSource(dataFromNetwork, response -> {
            result.removeSource(dataFromNetwork);
            appExecutors.getDiskIO().execute(
                    () -> this.saveAPIResponse(response)
            );
            result.setValue(transformFromAPIResponseToPageData().apply(response));
        });
        doubanMovieRepository.getMovie(startIndex);
    }

    private boolean shouldFetchFromNetwork(List<RoomMovie> data, int startIndex) {
        return data == null || data.isEmpty() || data.size() <= startIndex;
    }

    private void saveAPIResponse(ApiResponse<DouBanMovieResponseData> response) {
        movieDao.insertAll(transformFromAPIResponseToRoomData().apply(response));
    }

    /* package */ static Function<ApiResponse<DouBanMovieResponseData>, MovieData> transformFromAPIResponseToPageData() {
        return response -> {
            DouBanMovieResponseData douBanMovieResponseData = response.body;
            List<MovieItem> list = new ArrayList<>();
            for (DouBanMovieResponseData.MovieBean bean : douBanMovieResponseData.getSubjects()) {
                list.add(new DouBanMovie(bean));
            }
            return new MovieData(list, douBanMovieResponseData.getTotal(), douBanMovieResponseData.getCount(), douBanMovieResponseData.getStart());
        };
    }

    /*package*/ Function<ApiResponse<DouBanMovieResponseData>, List<RoomMovie>> transformFromAPIResponseToRoomData() {
        return response -> {
            DouBanMovieResponseData douBanMovieResponseData = response.body;
            List<RoomMovie> list = new ArrayList<>();
            List<DouBanMovieResponseData.MovieBean> subjects = douBanMovieResponseData.getSubjects();
            for (int index = 0; index < subjects.size(); index ++) {
                DouBanMovieResponseData.MovieBean movieBean = subjects.get(index);
                list.add(
                        RoomMovie.builder()
                                .author(movieBean.getDirectors().get(0).getName())
                                .image(movieBean.getImages().getLarge())
                                .index(douBanMovieResponseData.getStart() + index)
                                .publishDate(movieBean.getYear().toString())
                                .publisher(movieBean.getOrigin_title())
                                .rating(Double.valueOf(movieBean.getRating().getAverage()))
                                .summary(movieBean.getTitle())
                                .title(movieBean.getTitle())
                                .build());
            }
            return list;
        };
    }
}
