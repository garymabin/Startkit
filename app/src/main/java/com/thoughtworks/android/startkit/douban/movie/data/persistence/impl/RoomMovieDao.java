package com.thoughtworks.android.startkit.douban.movie.data.persistence.impl;

import com.thoughtworks.android.startkit.douban.movie.data.persistence.IMovieDao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


@Dao
public interface RoomMovieDao extends IMovieDao<RoomMovie> {
    @Query("SELECT * FROM RoomMovie ORDER BY record_index ASC")
    LiveData<List<RoomMovie>> getAll();

    @Insert
    void insertAll(RoomMovie... users);
}
