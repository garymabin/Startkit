package com.thoughtworks.android.startkit.dagger;

import android.content.Context;

import com.thoughtworks.android.startkit.StartkitApplication;
import com.thoughtworks.android.startkit.dagger.module.ApplicationModule;
import com.thoughtworks.android.startkit.dagger.module.TestLocalDatabaseModule;
import com.thoughtworks.android.startkit.dagger.module.TestLocalMovieModule;
import com.thoughtworks.android.startkit.dagger.module.annotation.ApplicationContext;
import com.thoughtworks.android.startkit.dagger.module.annotation.DatabaseInfo;
import com.thoughtworks.android.startkit.douban.movie.dagger.module.DoubanMovieListFragmentModule;
import com.thoughtworks.android.startkit.douban.movie.dagger.module.DoubanMovieViewModelModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        DoubanMovieViewModelModule.class,
        TestLocalMovieModule.class,
        DoubanMovieListFragmentModule.class,
        ApplicationModule.class,
        TestLocalDatabaseModule.class
})
public interface AndroidTestAppComponent extends AndroidInjector<StartkitApplication> {
    @ApplicationContext
    Context getContext();

    @DatabaseInfo
    String getDatabaseName();
}
