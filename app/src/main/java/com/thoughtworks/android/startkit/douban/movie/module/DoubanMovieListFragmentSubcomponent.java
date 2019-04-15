package com.thoughtworks.android.startkit.douban.movie.module;

import com.thoughtworks.android.startkit.douban.movie.view.MovieItemListFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = {DoubanMovieViewModelModule.class, DoubanMovieModule.class})
public interface DoubanMovieListFragmentSubcomponent extends AndroidInjector<MovieItemListFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<MovieItemListFragment> {}
}
