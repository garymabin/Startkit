package com.thoughtworks.android.startkit.dagger.module;

import android.content.Context;

import com.thoughtworks.android.startkit.AppDatabase;
import com.thoughtworks.android.startkit.dagger.module.annotation.ApplicationContext;
import com.thoughtworks.android.startkit.dagger.module.annotation.DatabaseInfo;
import com.thoughtworks.android.startkit.douban.movie.data.persistence.IMovieDao;
import com.thoughtworks.android.startkit.douban.movie.data.persistence.impl.RoomMovie;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @DatabaseInfo
    private final String mDBName = "douban_database.db";

    @ApplicationContext
    private final Context mContext;

    public DatabaseModule (@ApplicationContext Context context) {
        mContext = context;
    }

    @Singleton
    @Provides
    AppDatabase provideDatabase () {
        return Room.databaseBuilder(
                mContext,
                AppDatabase.class,
                mDBName
        ).fallbackToDestructiveMigration().build();
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() { return mDBName; }

    @Singleton
    @Provides
    IMovieDao<RoomMovie> providePersonDao(AppDatabase db) { return db.movieDao(); }
}
