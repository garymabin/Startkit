package com.thoughtworks.android.startkit.douban.movie.dagger.module;


import com.thoughtworks.android.startkit.douban.DoubanAPIRetrofitSingletonImpl;
import com.thoughtworks.android.startkit.douban.movie.data.api.IDoubanMovieAPI;
import com.thoughtworks.android.startkit.douban.movie.repository.DoubanMovieRepository;
import com.thoughtworks.android.startkit.douban.movie.repository.IDoubanMovieRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DoubanMovieModule {

    @Provides
    @Singleton
    static IDoubanMovieAPI provideDoubanBooksAPI() {
        return new DoubanAPIRetrofitSingletonImpl();
    }

    @Provides
    @Singleton
    static IDoubanMovieRepository provideDoubanBooksRepository() {
        return new DoubanMovieRepository(provideDoubanBooksAPI());
    }
}

