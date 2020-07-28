package com.thoughtworks.android.startkit.douban.movie.repository;

import com.thoughtworks.android.startkit.douban.movie.data.api.IDoubanMovieAPI;
import com.thoughtworks.android.startkit.douban.movie.data.vo.DouBanMovieResponseData;
import com.thoughtworks.android.startkit.retrofit.ApiResponse;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;

public class DoubanMovieRepository implements IDoubanMovieRepository {

    private int DATA_PER_PAGE = 20;

    private final IDoubanMovieAPI doubanMovieAPI;

    @Inject
    public DoubanMovieRepository(IDoubanMovieAPI doubanMovieAPI){
        this.doubanMovieAPI = doubanMovieAPI;
    }

    @Override
    public LiveData<ApiResponse<DouBanMovieResponseData>> getMovie(int startIndex) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("count", String.valueOf(DATA_PER_PAGE));
        queryMap.put("start", String.valueOf(startIndex));
        return doubanMovieAPI.getMovie(queryMap);
    }
}
