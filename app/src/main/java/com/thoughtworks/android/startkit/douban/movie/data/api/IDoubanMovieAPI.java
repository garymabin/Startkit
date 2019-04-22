package com.thoughtworks.android.startkit.douban.movie.data.api;

import com.thoughtworks.android.startkit.douban.movie.data.vo.DouBanMovieResponseData;
import com.thoughtworks.android.startkit.retrofit.ApiResponse;

import java.util.Map;

import androidx.lifecycle.LiveData;
import io.reactivex.Single;

public interface IDoubanMovieAPI {
    //TODO: need to find a way to use a non rx-related return type here
    LiveData<ApiResponse<DouBanMovieResponseData>> getMovie(Map<String, String> options);
}
