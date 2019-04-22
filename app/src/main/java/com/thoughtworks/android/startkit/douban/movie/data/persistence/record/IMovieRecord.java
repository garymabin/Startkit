package com.thoughtworks.android.startkit.douban.movie.data.persistence.record;

public interface IMovieRecord {

    Integer getId();

    void setId(Integer integer);

    String getTitle();

    void setTitle(String title);

    String getSummary();

    void setSummary(String summary);

    double getRating();

    void setRating(double rating);

    String getImage();

    void setImage(String image);

    String getAuthor();

    void setAuthor(String author);

    String getPublisher();

    void setPublisher(String publisher);

    String getPublishDate();

    void setPublishDate(String publishDate);
}
