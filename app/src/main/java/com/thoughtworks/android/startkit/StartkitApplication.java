package com.thoughtworks.android.startkit;


import android.app.Application;
import android.content.Context;

import com.thoughtworks.android.startkit.dagger.DaggerAppComponent;
import com.thoughtworks.android.startkit.dagger.module.ApplicationModule;
import com.thoughtworks.android.startkit.dagger.module.DatabaseModule;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class StartkitApplication extends Application implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .databaseModule(new DatabaseModule(this))
                .build()
                .inject(this);
    }


    public static Context getApplication() {
        return instance;
    }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }
}
