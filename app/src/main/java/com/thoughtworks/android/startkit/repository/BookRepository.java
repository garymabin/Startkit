package com.thoughtworks.android.startkit.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.thoughtworks.android.startkit.BookData;
import com.thoughtworks.android.startkit.BookItem;
import com.thoughtworks.android.startkit.retrofit.DouBanBook;
import com.thoughtworks.android.startkit.retrofit.DouBanResponseData;
import com.thoughtworks.android.startkit.retrofit.DouBanService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

import lombok.AllArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@AllArgsConstructor
public class BookRepository {

    private static int DATA_PER_PAGE = 20;
    private static String DATA_TAG = "IT";

    private DouBanService douBanService;
    private final Executor executor;
    private final MutableLiveData<BookData> mData = new MutableLiveData<>();

    public LiveData<BookData> getBooks() {
        return getBooks(0);
    }

    public LiveData<BookData> getBooks(int start) {
        executor.execute(() -> {

            Map<String, String> queryMap = new HashMap<>();
            queryMap.put("tag", DATA_TAG);
            queryMap.put("count", String.valueOf(DATA_PER_PAGE));
            queryMap.put("start", String.valueOf(start));
            douBanService.getBooks(queryMap).enqueue(new Callback<DouBanResponseData>() {
                @Override
                public void onResponse(Call<DouBanResponseData> call, Response<DouBanResponseData> response) {
                    // error case is left out for brevity
                    DouBanResponseData douBanResponseData = response.body();
                    List<BookItem> list = new ArrayList<>();
                    for (DouBanResponseData.BooksBean bean : douBanResponseData.getBooks()) {
                        list.add(new DouBanBook(bean));
                    }
                    mData.setValue(new BookData(list, douBanResponseData.getTotal(), douBanResponseData.getCount(), douBanResponseData.getStart()));
                }

                @Override
                public void onFailure(Call<DouBanResponseData> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        });

        return mData;
    }
}
