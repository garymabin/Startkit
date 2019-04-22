package com.thoughtworks.android.startkit.douban.movie.repository;


import com.thoughtworks.android.startkit.douban.movie.data.api.IDoubanMovieAPI;
import com.thoughtworks.android.startkit.douban.movie.data.vo.MovieData;
import com.thoughtworks.android.startkit.douban.movie.data.vo.DouBanMovieResponseData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import androidx.lifecycle.LiveData;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DoubanMovieRepositoryTest {

    @Mock
    private IDoubanMovieAPI doubanMovieAPI;

    @InjectMocks
    private DoubanMovieRepository doubanMovieRepository;

    @Before
    public void setUp() {
        RxJavaPlugins.setIoSchedulerHandler(_wtf -> Schedulers.trampoline());
        RxJavaPlugins.setComputationSchedulerHandler(_wtf -> Schedulers.trampoline());
        RxJavaPlugins.setNewThreadSchedulerHandler(_wtf -> Schedulers.trampoline());
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(_wtf -> Schedulers.trampoline());
        MockitoAnnotations.initMocks(this);
        RxJavaPlugins.setErrorHandler(throwable -> {}); // nothing or some logging
    }

    @After
    public void tearDown() {
        RxJavaPlugins.reset();
        RxAndroidPlugins.reset();
    }

    @Test
    public void shouldCallDoubanBooksAPIForBooks() {
        //Given
        ArgumentCaptor<Map<String, String>> queryMapCaptor = ArgumentCaptor.forClass(Map.class);
        //TODO: better to create real data to verify detail fields.
        List<DouBanMovieResponseData.MovieBean> expectedBooks = Collections.emptyList();

        //When
        Mockito.when(doubanMovieAPI.getMovie(ArgumentMatchers.any()))
                .thenReturn(Single.just(
                        DouBanMovieResponseData.builder()
                                .subjects(expectedBooks)
                                .build()));
        //Then
        LiveData<MovieData> actualLiveData = doubanMovieRepository.getMovie(0);

        Mockito.verify(doubanMovieAPI).getMovie(queryMapCaptor.capture());

        assertEquals("20", queryMapCaptor.getValue().get("count"));
        assertEquals("0", queryMapCaptor.getValue().get("start"));

        assertNull(actualLiveData.getValue());
    }
}