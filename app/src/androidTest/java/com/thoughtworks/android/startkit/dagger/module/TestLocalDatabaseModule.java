package com.thoughtworks.android.startkit.dagger.module;

import android.content.Context;

import com.thoughtworks.android.startkit.AppDatabase;
import com.thoughtworks.android.startkit.LocalDoubanBooksAPI;
import com.thoughtworks.android.startkit.dagger.module.annotation.ApplicationContext;
import com.thoughtworks.android.startkit.dagger.module.annotation.DatabaseInfo;
import com.thoughtworks.android.startkit.douban.movie.data.api.IDoubanMovieAPI;
import com.thoughtworks.android.startkit.douban.movie.data.persistence.IMovieDao;
import com.thoughtworks.android.startkit.douban.movie.data.persistence.impl.RoomMovie;
import com.thoughtworks.android.startkit.douban.movie.repository.DoubanMovieRepository;
import com.thoughtworks.android.startkit.douban.movie.repository.IDoubanMovieRepository;

import javax.inject.Singleton;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import dagger.Module;
import dagger.Provides;

@Module
public class TestLocalDatabaseModule {

    @DatabaseInfo
    private final String mDBName = "douban_database.db";

    @ApplicationContext
    private final Context mContext;

    public TestLocalDatabaseModule (@ApplicationContext Context context) {
        mContext = context;
    }

    @Singleton
    @Provides
    AppDatabase provideDatabase () {
        return Room.inMemoryDatabaseBuilder(
                mContext,
                AppDatabase.class
        ).fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() { return mDBName; }

    @Singleton
    @Provides
    IMovieDao<RoomMovie> providePersonDao(AppDatabase db) { return db.movieDao(); }
}
