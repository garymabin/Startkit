package com.thoughtworks.android.startkit.dagger.module;

import com.thoughtworks.android.startkit.LocalDoubanBooksAPI;
import com.thoughtworks.android.startkit.douban.movie.data.api.IDoubanMovieAPI;
import com.thoughtworks.android.startkit.douban.movie.repository.DoubanMovieRepository;
import com.thoughtworks.android.startkit.douban.movie.repository.IDoubanMovieRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TestLocalMovieModule {

    @Provides
    @Singleton
    static IDoubanMovieAPI provideDoubanBooksAPI() {
        return new LocalDoubanBooksAPI();
    }

    @Provides
    @Singleton
    static IDoubanMovieRepository provideDoubanBooksRepository() {
        return new DoubanMovieRepository(provideDoubanBooksAPI());
    }
}
