package com.thoughtworks.android.startkit.douban.movie.data.persistence;

import com.thoughtworks.android.startkit.douban.movie.data.persistence.record.IMovieRecord;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface IMovieDao<T extends IMovieRecord> {
    //TODO: is there a way to decouple from direct reference from LiveData?
    LiveData<List<T>> getAll();
    void insertAll(T... movies);
}
