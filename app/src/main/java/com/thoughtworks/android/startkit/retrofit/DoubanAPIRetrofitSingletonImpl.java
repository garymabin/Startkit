package com.thoughtworks.android.startkit.retrofit;

import com.thoughtworks.android.startkit.douban.books.data.api.IDoubanBooksAPI;
import com.thoughtworks.android.startkit.douban.books.data.domain.DouBanResponseData;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Singleton
public class DoubanAPIRetrofitSingletonImpl implements IDoubanBooksAPI {

    private String DATA_URL = "https://api.douban.com";
    private final Retrofit mRetrofit;
    private DouBanService mService;

    @Inject
    public DoubanAPIRetrofitSingletonImpl() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(DATA_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mService = mRetrofit.create(DouBanService.class);
    }
    @Override
    public Single<DouBanResponseData> getBooks(Map<String, String> options) {
        return mService.getBooks(options);
    }
}
