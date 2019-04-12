package com.thoughtworks.android.startkit.douban.books.data.domain;

import android.os.Parcelable;
import android.text.TextUtils;

/**
 * Created by zhuang on 12/03/2017.
 */

public abstract class BookItem implements Parcelable{

    public abstract String getItemTitle();

    public abstract String getItemSummary();

    public abstract double getItemRating();

    public abstract String getItemImage();

    public abstract String getItemAuthor();

    public abstract String getItemPublisher();

    public abstract String getItemPublishDate();

    public String getInformation() {
        return TextUtils.join(" / ", new String[]{getItemAuthor(), getItemPublisher(), getItemPublishDate()});
    }
}
