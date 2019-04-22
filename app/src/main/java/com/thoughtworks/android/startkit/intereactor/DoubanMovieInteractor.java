package com.thoughtworks.android.startkit.intereactor;

import com.thoughtworks.android.startkit.AppExecutors;
import com.thoughtworks.android.startkit.douban.movie.data.persistence.IMovieDao;
import com.thoughtworks.android.startkit.douban.movie.data.persistence.record.IMovieRecord;
import com.thoughtworks.android.startkit.douban.movie.data.vo.DouBanMovieResponseData;
import com.thoughtworks.android.startkit.douban.movie.data.vo.MovieData;
import com.thoughtworks.android.startkit.douban.movie.data.vo.MovieItem;
import com.thoughtworks.android.startkit.douban.movie.repository.IDoubanMovieRepository;
import com.thoughtworks.android.startkit.retrofit.ApiResponse;
import com.thoughtworks.android.startkit.retrofit.DouBanMovie;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

public class DoubanMovieInteractor {

    @Inject
    IMovieDao<IMovieRecord> movieDao;

    @Inject
    IDoubanMovieRepository doubanMovieRepository;

    @Inject
    AppExecutors appExecutors;

    private MediatorLiveData<MovieData> result = new MediatorLiveData<>();

    public LiveData<MovieData> loadMovie(int startIndex) {
        LiveData<List<IMovieRecord>> dataFromDB = movieDao.getAll();
        result.addSource(dataFromDB, data -> {
            result.removeSource(dataFromDB);
            if (shouldFetchFromNetwork(data)) {
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

    private void loadFromNetwork(LiveData<List<IMovieRecord>> dataFromDB, int startIndex) {
        LiveData<ApiResponse<DouBanMovieResponseData>> dataFromNetwork = doubanMovieRepository.getMovie(startIndex);
        result.addSource(dataFromNetwork, response -> {
            result.removeSource(dataFromNetwork);
            appExecutors.getDiskIO().execute(
                    movieDao.insertAll();
            );
        });
        doubanMovieRepository.getMovie(startIndex);
    }

    private boolean shouldFetchFromNetwork(List<IMovieRecord> data) {
        return data == null || data.isEmpty();
    }

    private void saveAPIResponse() {
        movieDao.insertAll();
    }
}
