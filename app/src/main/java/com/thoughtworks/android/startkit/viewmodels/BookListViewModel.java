package com.thoughtworks.android.startkit.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.thoughtworks.android.startkit.BookData;
import com.thoughtworks.android.startkit.BookItem;
import com.thoughtworks.android.startkit.repository.BookRepository;
import com.thoughtworks.android.startkit.retrofit.DouBanService;
import com.thoughtworks.android.startkit.wrapper.Event;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.Getter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Getter
public class BookListViewModel extends ViewModel {

    public final static int START_LOADING = 1;
    public final static int STOP_LOADING = 2;
    public final static int START_LOADING_MORE = 3;
    public final static int STOP_LOADING_MORE = 4;

    private final static String DATA_URL = "https://api.douban.com";
    private final static String DATA_TAG = "IT";

    private LiveData<BookData> books;

    private MutableLiveData<Event<Integer>> loadingEvent = new MutableLiveData<>();

    public BookRepository bookRepository;

    public BookListViewModel() {
        //to be optimized with Dagger
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DATA_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DouBanService doubanService = retrofit.create(DouBanService.class);
        bookRepository = new BookRepository(doubanService, executorService);
    }

    public void init() {
        books = bookRepository.getBooks();
    }

    public LiveData<BookData> getBooks() {
        return this.books;
    }

    public void startLoading(boolean isLoadingMore) {
        loadingEvent.setValue(new Event(isLoadingMore ? START_LOADING_MORE : START_LOADING));
    }

    public void stopLoading(boolean isLoadingMore) {
        loadingEvent.setValue(new Event(isLoadingMore ? STOP_LOADING_MORE : STOP_LOADING));
    }

    private boolean isLoading() {
        return loadingEvent.getValue().getContent().equals(START_LOADING) ||
                loadingEvent.getValue().getContent().equals(START_LOADING_MORE);
    }

    public void loadMore(int startIndex) {
        if (!isLoading()) {
            startLoading(true);
            //fetch more data;
            books = bookRepository.getBooks(startIndex);
        }
    }
}
