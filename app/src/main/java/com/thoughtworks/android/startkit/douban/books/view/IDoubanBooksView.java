package com.thoughtworks.android.startkit.douban.books.view;

import com.thoughtworks.android.startkit.douban.books.data.domain.BookItem;

import java.util.List;

public interface IDoubanBooksView {
    void setRefreshing(boolean refreshing);

    void loadData(List<BookItem> bookArray);

    void showLoadingMore();

    void hideLoadingMore();

    void loadMoreData(List<BookItem> bookArray);

    int getItemCount();
}
