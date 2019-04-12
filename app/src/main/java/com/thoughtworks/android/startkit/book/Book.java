package com.thoughtworks.android.startkit.book;

import android.os.Parcel;

import com.thoughtworks.android.startkit.douban.books.data.domain.BookItem;

public class Book extends BookItem {
    private String title;
    private String image;
    private String author;
    private String publisher;
    private String publishDate;
    private String summary;
    private double rating;

    public Book(String title, String image, String author, String publisher, String publishDate, String summary, double rating) {
        this.title = title;
        this.image = image;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.summary = summary;
        this.rating = rating;
    }

    protected Book(Parcel in) {
        title = in.readString();
        image = in.readString();
        author = in.readString();
        publisher = in.readString();
        publishDate = in.readString();
        summary = in.readString();
        rating = in.readDouble();
    }

    public static final Creator<BookItem> CREATOR = new Creator<BookItem>() {
        @Override
        public BookItem createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public BookItem[] newArray(int size) {
            return new BookItem[size];
        }
    };

    protected Book() {
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
