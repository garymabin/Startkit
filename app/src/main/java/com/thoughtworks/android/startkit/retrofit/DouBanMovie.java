package com.thoughtworks.android.startkit.retrofit;

import com.thoughtworks.android.startkit.douban.movie.data.domain.Movie;
import com.thoughtworks.android.startkit.douban.movie.data.domain.DouBanMovieResponseData;

/**
 * Created by zhuang on 12/03/2017.
 */

public class DouBanMovie extends Movie {
    public DouBanMovie(DouBanMovieResponseData.MovieBean movieBean) {
        super(movieBean.getTitle(),
                movieBean.getImages().getLarge(),
                movieBean.getDirectors().get(0).getName(),
                movieBean.getOrigin_title(),
                movieBean.getYear().toString(),
                movieBean.getTitle(),
                Double.valueOf(movieBean.getRating().getAverage()));
    }
}
