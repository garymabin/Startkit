package com.thoughtworks.android.startkit.douban.movie.viewmodel;


import com.thoughtworks.android.startkit.douban.movie.data.vo.DouBanMovieResponseData;
import com.thoughtworks.android.startkit.douban.movie.data.vo.MovieData;
import com.thoughtworks.android.startkit.douban.movie.data.vo.MovieItem;
import com.thoughtworks.android.startkit.douban.movie.intereactor.DoubanMovieInteractor;
import com.thoughtworks.android.startkit.douban.movie.repository.IDoubanMovieRepository;
import com.thoughtworks.android.startkit.retrofit.ApiResponse;
import com.thoughtworks.android.startkit.douban.movie.data.vo.DouBanMovie;
import com.thoughtworks.android.startkit.util.AbsentLiveData;
import com.thoughtworks.android.startkit.wrapper.Event;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class DoubanMovieViewModel extends ViewModel {

    public final static int START_LOADING = 1;
    public final static int STOP_LOADING = 2;
    public final static int START_LOADING_MORE = 3;
    public final static int STOP_LOADING_MORE = 4;

    private MutableLiveData<Integer> startIndex = new MutableLiveData<>();

    @Getter
    private LiveData<MovieData> movies = Transformations.switchMap(startIndex, index -> {
        if (index == null) {
            return AbsentLiveData.create();
        } else {
            return this.movieInteractor.loadMovie(index);
        }
    });

    @Getter
    /**/ MutableLiveData<Event<Integer>> loadingEvent = new MutableLiveData<>();


    private DoubanMovieInteractor movieInteractor;

    @Inject
    DoubanMovieViewModel(DoubanMovieInteractor movieInteractor) {
        this.movieInteractor = movieInteractor;
    }

    public void startLoading(boolean isLoadingMore) {
        loadingEvent.setValue(new Event<>(isLoadingMore ? START_LOADING_MORE : START_LOADING));
    }

    public void stopLoading(boolean isLoadingMore) {
        loadingEvent.setValue(new Event<>(isLoadingMore ? STOP_LOADING_MORE : STOP_LOADING));
    }

    private boolean isLoading() {
        Event<Integer> currentEvent = loadingEvent.getValue();
        return currentEvent != null && (currentEvent.getContent().equals(START_LOADING) ||
                currentEvent.getContent().equals(START_LOADING_MORE));
    }

    public void loadMore(int startIndex) {
        if (!isLoading()) {
            startLoading(true);
            this.startIndex.setValue(startIndex);
        }
    }

    public void refresh() {
        this.startIndex.setValue(0);
    }
}
