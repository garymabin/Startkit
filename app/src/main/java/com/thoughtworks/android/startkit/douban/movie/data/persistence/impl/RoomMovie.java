package com.thoughtworks.android.startkit.douban.movie.data.persistence.impl;

import com.thoughtworks.android.startkit.douban.movie.data.persistence.record.IMovieRecord;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class RoomMovie implements IMovieRecord {

    @PrimaryKey
    private Integer id;

    @ColumnInfo(name = "record_index")
    private Integer index;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "summary")
    private String summary;

    @ColumnInfo(name = "rating")
    public double rating;

    @ColumnInfo(name = "image")
    private String image;
    
    @ColumnInfo(name = "author")
    private String author;

    @ColumnInfo(name = "publisher")
    private String publisher;

    @ColumnInfo(name = "publish_date")
    private String publishDate;
}
