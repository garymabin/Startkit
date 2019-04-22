package com.thoughtworks.android.startkit.retrofit;

import com.thoughtworks.android.startkit.douban.movie.data.api.IDoubanMovieAPI;
import com.thoughtworks.android.startkit.douban.movie.data.vo.DouBanMovieResponseData;

import java.util.Map;

import androidx.lifecycle.LiveData;
import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class DoubanAPIRetrofitSingletonImpl implements IDoubanMovieAPI {

    private String DATA_URL = "https://api.douban.com";
    private final Retrofit mRetrofit;
    private DouBanService mService;

    public DoubanAPIRetrofitSingletonImpl() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(DATA_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build();
        mService = mRetrofit.create(DouBanService.class);
    }
    @Override
    public LiveData<ApiResponse<DouBanMovieResponseData>> getMovie(Map<String, String> options) {
        return mService.getTop250Movies(options);
    }
}
