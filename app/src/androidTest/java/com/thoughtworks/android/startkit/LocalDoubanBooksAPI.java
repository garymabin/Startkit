package com.thoughtworks.android.startkit;

import com.thoughtworks.android.startkit.douban.movie.data.api.IDoubanMovieAPI;
import com.thoughtworks.android.startkit.douban.movie.data.vo.DouBanMovieResponseData;
import com.thoughtworks.android.startkit.retrofit.ApiResponse;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Response;

public class LocalDoubanBooksAPI implements IDoubanMovieAPI {
    @Override
    public LiveData<ApiResponse<DouBanMovieResponseData>> getMovie(Map<String, String> options) {
        List<DouBanMovieResponseData.MovieBean> expectedBooks = Collections.singletonList(
                DouBanMovieResponseData.MovieBean.builder()
                        .rating(new DouBanMovieResponseData.MovieBean.RatingBean(){{setAverage("5.7");}})
                        .title("多力特的奇幻冒险")
                        .directors(Collections.singletonList(new DouBanMovieResponseData.MovieBean.DirectorBean(){{setName("斯蒂芬·加汉");}}))
                        .year(2020)
                        .origin_title("Dolittle")
                        .images(new DouBanMovieResponseData.MovieBean.ImagesBean(){{setLarge("http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p25915.webp");}})
                        .build()
        );

        LiveData<ApiResponse<DouBanMovieResponseData>> apiResponseLiveData = new MutableLiveData<>();
        ((MutableLiveData<ApiResponse<DouBanMovieResponseData>>) apiResponseLiveData).postValue(
                new ApiResponse<>(Response.success(DouBanMovieResponseData.builder()
                        .subjects(expectedBooks)
                        .start(0)
                        .count(1)
                        .total(1)
                        .build())));
        return apiResponseLiveData;
    }
}
