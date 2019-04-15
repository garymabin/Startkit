package com.thoughtworks.android.startkit.douban.movie.repository;

import com.thoughtworks.android.startkit.douban.movie.data.domain.MovieData;

import androidx.lifecycle.LiveData;

public interface IDoubanMovieRepository {
    LiveData<MovieData> getBooks(int startIndex);
}
