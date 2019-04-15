package com.thoughtworks.android.startkit.dagger;

import com.thoughtworks.android.startkit.StartkitApplication;
import com.thoughtworks.android.startkit.douban.movie.module.DoubanMovieListFragmentModule;
import com.thoughtworks.android.startkit.douban.movie.module.DoubanMovieModule;
import com.thoughtworks.android.startkit.douban.movie.module.DoubanMovieViewModelModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        DoubanMovieViewModelModule.class,
        DoubanMovieListFragmentModule.class,
        DoubanMovieModule.class
})
public interface AppComponent extends AndroidInjector<StartkitApplication> {
}
