package com.thoughtworks.android.startkit;

import com.thoughtworks.android.startkit.douban.movie.data.persistence.impl.RoomMovie;
import com.thoughtworks.android.startkit.douban.movie.data.persistence.impl.RoomMovieDao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {RoomMovie.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract RoomMovieDao movieDao();
}
