package com.thoughtworks.android.startkit.douban.movie.data.vo;

import android.os.Parcel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Movie extends MovieItem {
    private String itemTitle;
    private String itemImage;
    private String itemAuthor;
    private String itemPublisher;
    private String itemPublishDate;
    private String itemSummary;
    private double itemRating;

    protected Movie(Parcel in) {
        itemTitle = in.readString();
        itemImage = in.readString();
        itemAuthor = in.readString();
        itemPublisher = in.readString();
        itemPublishDate = in.readString();
        itemSummary = in.readString();
        itemRating = in.readDouble();
    }

    public static final Creator<MovieItem> CREATOR = new Creator<MovieItem>() {
        @Override
        public MovieItem createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public MovieItem[] newArray(int size) {
            return new MovieItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemTitle);
        dest.writeString(itemImage);
        dest.writeString(itemAuthor);
        dest.writeString(itemPublisher);
        dest.writeString(itemPublishDate);
        dest.writeString(itemSummary);
        dest.writeDouble(itemRating);
    }
}
