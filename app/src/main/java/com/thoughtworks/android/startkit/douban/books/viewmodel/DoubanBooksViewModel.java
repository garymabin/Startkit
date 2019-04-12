package com.thoughtworks.android.startkit.douban.books.viewmodel;


import com.thoughtworks.android.startkit.douban.books.data.domain.BookData;
import com.thoughtworks.android.startkit.douban.books.repository.IDoubanBooksRepository;
import com.thoughtworks.android.startkit.wrapper.Event;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class DoubanBooksViewModel extends ViewModel {

    public final static int START_LOADING = 1;
    public final static int STOP_LOADING = 2;
    public final static int START_LOADING_MORE = 3;
    public final static int STOP_LOADING_MORE = 4;

    private LiveData<BookData> books;

    @Getter
    /**/ MutableLiveData<Event<Integer>> loadingEvent = new MutableLiveData<>();

    @NonNull
    public final IDoubanBooksRepository bookRepository;

    public LiveData<BookData> getBooks() {
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
            this.books = bookRepository.getBooks(startIndex);
        }
    }

    public void refresh() {
        this.books = bookRepository.getBooks(0);
    }
}
