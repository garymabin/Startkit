package com.thoughtworks.android.startkit.douban.movie.data.domain;

import android.os.Parcel;

public class Movie extends MovieItem {
    private String title;
    private String image;
    private String author;
    private String publisher;
    private String publishDate;
    private String summary;
    private double rating;

    public Movie(String title, String image, String author, String publisher, String publishDate, String summary, double rating) {
        this.title = title;
        this.image = image;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.summary = summary;
        this.rating = rating;
    }

    protected Movie(Parcel in) {
        title = in.readString();
        image = in.readString();
        author = in.readString();
        publisher = in.readString();
        publishDate = in.readString();
        summary = in.readString();
        rating = in.readDouble();
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

    protected Movie() {
    }

    public String getItemTitle() {
        return title;
    }

    public String getItemImage() {
        return image;
    }

    public String getItemSummary() {
        return summary;
    }


    public String getItemAuthor() {
        return author;
    }

    public String getItemPublisher() {
        return publisher;
    }

    public String getItemPublishDate() {
        return publishDate;
    }

    public double getItemRating() {
        return rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(image);
        dest.writeString(author);
        dest.writeString(publisher);
        dest.writeString(publishDate);
        dest.writeString(summary);
        dest.writeDouble(rating);
    }
}
