package com.thoughtworks.android.startkit.retrofit;

import com.thoughtworks.android.startkit.book.Book;
import com.thoughtworks.android.startkit.douban.books.data.domain.DouBanResponseData;

/**
 * Created by zhuang on 12/03/2017.
 */

public class DouBanBook extends Book {
    public DouBanBook(DouBanResponseData.BooksBean booksBean) {
        super(booksBean.getTitle(),
                booksBean.getImages().getLarge(),
                booksBean.getAuthor().toString(),
                booksBean.getPublisher(),
                booksBean.getPubdate(),
                booksBean.getSummary(),
                Double.valueOf(booksBean.getRating().getAverage()));
    }
}
