package com.thoughtworks.android.startkit.douban.movie.view;

import com.thoughtworks.android.startkit.douban.movie.data.domain.MovieItem;

import java.util.List;

public interface IDoubanMovieListView {
    void setRefreshing(boolean refreshing);

    void loadData(List<MovieItem> bookArray);

    void showLoadingMore();

    void hideLoadingMore();

    void loadMoreData(List<MovieItem> bookArray);

    int getItemCount();
}
