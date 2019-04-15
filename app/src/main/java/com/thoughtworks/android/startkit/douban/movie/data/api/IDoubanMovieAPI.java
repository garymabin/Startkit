package com.thoughtworks.android.startkit.douban.movie.data.api;

import com.thoughtworks.android.startkit.douban.movie.data.domain.DouBanMovieResponseData;

import java.util.Map;

import io.reactivex.Single;

public interface IDoubanMovieAPI {
    //TODO: need to find a way to use a non rx-related return type here
    Single<DouBanMovieResponseData> getMovie(Map<String, String> options);
}
