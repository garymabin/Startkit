package com.thoughtworks.android.startkit.douban.movie.dagger.module;

import com.thoughtworks.android.startkit.douban.movie.view.MovieItemListFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = DoubanMovieListFragmentSubcomponent.class)
public abstract class DoubanMovieListFragmentModule {
    @Binds
    @IntoMap
    @ClassKey(MovieItemListFragment.class)
    abstract AndroidInjector.Factory<?>
    bindYourFragmentInjectorFactory(DoubanMovieListFragmentSubcomponent.Factory factory);
}
