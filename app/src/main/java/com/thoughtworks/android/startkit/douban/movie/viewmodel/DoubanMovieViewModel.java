package com.thoughtworks.android.startkit.douban.movie.viewmodel;


import com.thoughtworks.android.startkit.douban.movie.data.domain.MovieData;
import com.thoughtworks.android.startkit.douban.movie.repository.IDoubanMovieRepository;
import com.thoughtworks.android.startkit.wrapper.Event;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class DoubanMovieViewModel extends ViewModel {

    public final static int START_LOADING = 1;
    public final static int STOP_LOADING = 2;
    public final static int START_LOADING_MORE = 3;
    public final static int STOP_LOADING_MORE = 4;

    private LiveData<MovieData> books;

    @Getter
    /**/ MutableLiveData<Event<Integer>> loadingEvent = new MutableLiveData<>();

    private IDoubanMovieRepository bookRepository;

    @Inject
    public DoubanMovieViewModel(IDoubanMovieRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public LiveData<MovieData> getBooks() {
        if (this.books == null) {
            refresh();
        }
        return this.books;
    }

    public void startLoading(boolean isLoadingMore) {
        loadingEvent.setValue(new Event<>(isLoadingMore ? START_LOADING_MORE : START_LOADING));
    }

    public void stopLoading(boolean isLoadingMore) {
        loadingEvent.setValue(new Event<>(isLoadingMore ? STOP_LOADING_MORE : STOP_LOADING));
    }

    private boolean isLoading() {
        Event<Integer> currentEvent = loadingEvent.getValue();
        return currentEvent != null && (currentEvent.getContent().equals(START_LOADING) ||
                currentEvent.getContent().equals(START_LOADING_MORE));
    }

    public void loadMore(int startIndex) {
        if (!isLoading()) {
            startLoading(true);
            //fetch more data;
            this.books = this.bookRepository.getBooks(startIndex);
        }
    }

    public void refresh() {
        this.books = this.bookRepository.getBooks(0);
    }
}
