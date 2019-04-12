package com.thoughtworks.android.startkit.douban.books.repository;

import com.thoughtworks.android.startkit.douban.books.data.domain.BookData;
import com.thoughtworks.android.startkit.douban.books.data.domain.BookItem;
import com.thoughtworks.android.startkit.douban.books.data.api.IDoubanBooksAPI;
import com.thoughtworks.android.startkit.retrofit.DouBanBook;
import com.thoughtworks.android.startkit.douban.books.data.domain.DouBanResponseData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.observers.ConsumerSingleObserver;
import io.reactivex.schedulers.Schedulers;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class DoubanBooksRepository implements IDoubanBooksRepository {

    private String DATA_TAG = "IT";
    private int DATA_PER_PAGE = 20;

    @NonNull
    private final IDoubanBooksAPI doubanBooksAPI;

    @Override
    public LiveData<BookData> getBooks(int startIndex) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("tag", DATA_TAG);
        queryMap.put("count", String.valueOf(DATA_PER_PAGE));
        queryMap.put("start", String.valueOf(startIndex));

        final MutableLiveData<BookData> data = new MutableLiveData<>();

        doubanBooksAPI.getBooks(queryMap)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ConsumerSingleObserver<>(
                        response -> {
                            List<BookItem> list = new ArrayList<>();
                            for (DouBanResponseData.BooksBean bean : response.getBooks()) {
                                list.add(new DouBanBook(bean));
                            }
                            data.setValue(new BookData(list, response.getTotal(), response.getCount(), response.getStart()));
                        }, null));
        return data;
    }
}
