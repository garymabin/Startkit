package com.thoughtworks.android.startkit.douban.books.repository;

import com.thoughtworks.android.startkit.douban.books.data.domain.BookData;

import androidx.lifecycle.LiveData;

public interface IDoubanBooksRepository {
    LiveData<BookData> getBooks(int startIndex);
}
