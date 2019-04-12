package com.thoughtworks.android.startkit.retrofit;

import com.thoughtworks.android.startkit.douban.books.data.api.IDoubanBooksAPI;
import com.thoughtworks.android.startkit.douban.books.data.domain.DouBanResponseData;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by zhuang on 12/03/2017.
 */

public interface DouBanService extends IDoubanBooksAPI {

    @GET("/v2/book/search")
    Single<DouBanResponseData> getBooks(@QueryMap Map<String, String> options);
}
