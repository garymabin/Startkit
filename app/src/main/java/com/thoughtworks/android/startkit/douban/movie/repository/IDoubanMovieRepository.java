package com.thoughtworks.android.startkit.douban.movie.repository;

import com.thoughtworks.android.startkit.douban.movie.data.vo.DouBanMovieResponseData;
import com.thoughtworks.android.startkit.douban.movie.data.vo.MovieData;
import com.thoughtworks.android.startkit.retrofit.ApiResponse;

import androidx.lifecycle.LiveData;

public interface IDoubanMovieRepository {
    LiveData<ApiResponse<DouBanMovieResponseData>> getMovie(int startIndex);
}
