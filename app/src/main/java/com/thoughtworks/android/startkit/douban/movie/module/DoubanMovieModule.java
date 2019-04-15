package com.thoughtworks.android.startkit.douban.movie.module;


import com.thoughtworks.android.startkit.douban.movie.data.api.IDoubanMovieAPI;
import com.thoughtworks.android.startkit.douban.movie.repository.DoubanMovieRepository;
import com.thoughtworks.android.startkit.douban.movie.repository.IDoubanMovieRepository;
import com.thoughtworks.android.startkit.retrofit.DoubanAPIRetrofitSingletonImpl;

import dagger.Module;
import dagger.Provides;

@Module(includes = DoubanMovieViewModelModule.class)
public class DoubanMovieModule {

    @Provides
    static IDoubanMovieAPI provideDoubanBooksAPI() {
        return new DoubanAPIRetrofitSingletonImpl();
    }

    @Provides
    static IDoubanMovieRepository provideDoubanBooksRepository() {
        return new DoubanMovieRepository(provideDoubanBooksAPI());
    }
}

