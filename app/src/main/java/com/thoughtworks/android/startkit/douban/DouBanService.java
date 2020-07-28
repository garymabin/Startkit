package com.thoughtworks.android.startkit.douban;

import com.thoughtworks.android.startkit.douban.movie.data.vo.DouBanMovieResponseData;
import com.thoughtworks.android.startkit.retrofit.ApiResponse;

import java.util.Map;

import androidx.lifecycle.LiveData;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by zhuang on 12/03/2017.
 */

public interface DouBanService {

    @GET("/v2/movie/top250")
    LiveData<ApiResponse<DouBanMovieResponseData>> getTop250Movies(@QueryMap Map<String, String> options);
}
