package com.thoughtworks.android.startkit.retrofit;

import com.thoughtworks.android.startkit.douban.movie.data.domain.DouBanMovieResponseData;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by zhuang on 12/03/2017.
 */

public interface DouBanService {

    @GET("/v2/movie/top250")
    Single<DouBanMovieResponseData> getTop250Movies(@QueryMap Map<String, String> options);
}
