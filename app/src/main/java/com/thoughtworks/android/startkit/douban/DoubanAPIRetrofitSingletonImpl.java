package com.thoughtworks.android.startkit.douban;

import com.thoughtworks.android.startkit.douban.movie.data.api.IDoubanMovieAPI;
import com.thoughtworks.android.startkit.douban.movie.data.vo.DouBanMovieResponseData;
import com.thoughtworks.android.startkit.retrofit.ApiResponse;
import com.thoughtworks.android.startkit.retrofit.LiveDataCallAdapterFactory;

import java.util.Map;

import androidx.lifecycle.LiveData;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DoubanAPIRetrofitSingletonImpl implements IDoubanMovieAPI {

    private String DATA_URL = "https://api.douban.com";
    private String API_KEY = "0df993c66c0c636e29ecbb5344252a4a";
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
        options.put("apikey", API_KEY);
        return mService.getTop250Movies(options);
    }
}
