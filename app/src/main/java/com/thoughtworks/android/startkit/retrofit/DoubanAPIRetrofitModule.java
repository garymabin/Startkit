package com.thoughtworks.android.startkit.retrofit;

import com.thoughtworks.android.startkit.douban.books.data.api.IDoubanBooksAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DoubanAPIRetrofitModule {

    @Provides
    @Singleton
    IDoubanBooksAPI provideDoubanBooksAPI() {
        return new DoubanAPIRetrofitSingletonImpl();
    }
}
