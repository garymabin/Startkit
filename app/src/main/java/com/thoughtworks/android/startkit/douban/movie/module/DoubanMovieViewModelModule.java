package com.thoughtworks.android.startkit.douban.movie.module;

import com.thoughtworks.android.startkit.dagger.AppViewModelFactory;
import com.thoughtworks.android.startkit.dagger.ViewModelKey;
import com.thoughtworks.android.startkit.douban.movie.viewmodel.DoubanMovieViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


@Module
public abstract class DoubanMovieViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(AppViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(DoubanMovieViewModel.class)
    abstract ViewModel bindUserViewModel(DoubanMovieViewModel userViewModel);

}
