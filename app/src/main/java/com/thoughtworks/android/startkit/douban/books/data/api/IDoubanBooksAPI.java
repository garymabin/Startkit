package com.thoughtworks.android.startkit.douban.books.data.api;

import com.thoughtworks.android.startkit.douban.books.data.domain.DouBanResponseData;

import java.util.Map;

import io.reactivex.Single;

public interface IDoubanBooksAPI {
    //TODO: need to find a way to use a non rx-related return type here
    Single<DouBanResponseData> getBooks(Map<String, String> options);
}
