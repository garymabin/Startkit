package com.thoughtworks.android.startkit.douban.movie.repository;

import com.thoughtworks.android.startkit.douban.movie.data.api.IDoubanMovieAPI;
import com.thoughtworks.android.startkit.douban.movie.data.domain.MovieData;
import com.thoughtworks.android.startkit.douban.movie.data.domain.MovieItem;
import com.thoughtworks.android.startkit.douban.movie.data.domain.DouBanMovieResponseData;
import com.thoughtworks.android.startkit.retrofit.DouBanMovie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.observers.ConsumerSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DoubanMovieRepository implements IDoubanMovieRepository {

    private int DATA_PER_PAGE = 20;

    private final IDoubanMovieAPI doubanMovieAPI;

    @Inject
    public DoubanMovieRepository(IDoubanMovieAPI doubanMovieAPI){
        this.doubanMovieAPI = doubanMovieAPI;
    }

    @Override
    public LiveData<MovieData> getBooks(int startIndex) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("count", String.valueOf(DATA_PER_PAGE));
        queryMap.put("start", String.valueOf(startIndex));

        final MutableLiveData<MovieData> data = new MutableLiveData<>();

        doubanMovieAPI.getMovie(queryMap)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ConsumerSingleObserver<>(
                        response -> {
                            List<MovieItem> list = new ArrayList<>();
                            for (DouBanMovieResponseData.MovieBean bean : response.getSubjects()) {
                                list.add(new DouBanMovie(bean));
                            }
                            data.setValue(new MovieData(list, response.getTotal(), response.getCount(), response.getStart()));
                        }, null));
        return data;
    }
}
