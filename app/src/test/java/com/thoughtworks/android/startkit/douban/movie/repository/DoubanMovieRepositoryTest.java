package com.thoughtworks.android.startkit.douban.movie.repository;


import com.thoughtworks.android.startkit.douban.movie.data.api.IDoubanMovieAPI;
import com.thoughtworks.android.startkit.douban.movie.data.vo.DouBanMovieResponseData;
import com.thoughtworks.android.startkit.douban.movie.rule.RxSchedulersOverrideRule;
import com.thoughtworks.android.startkit.retrofit.ApiResponse;

import org.junit.Before;
import org.junit.Rule;
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

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DoubanMovieRepositoryTest {

    @Mock
    private IDoubanMovieAPI doubanMovieAPI;

    @InjectMocks
    private DoubanMovieRepository doubanMovieRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();
    @Rule
    public RxSchedulersOverrideRule rxSchedulersOverrideRule = new RxSchedulersOverrideRule();

    @Test
    public void shouldCallDoubanBooksAPIForBooks() {
        //Given
        ArgumentCaptor<Map<String, String>> queryMapCaptor = ArgumentCaptor.forClass(Map.class);
        //TODO: better to create real data to verify detail fields.
        List<DouBanMovieResponseData.MovieBean> expectedBooks = Collections.emptyList();

        LiveData<ApiResponse<DouBanMovieResponseData>> apiResponseLiveData = new MutableLiveData<>();
        ((MutableLiveData<ApiResponse<DouBanMovieResponseData>>) apiResponseLiveData).setValue(
                new ApiResponse<>(Response.success(DouBanMovieResponseData.builder()
                        .subjects(expectedBooks)
                        .build())));
        //When
        Mockito.when(doubanMovieAPI.getMovie(ArgumentMatchers.any()))
                .thenReturn(apiResponseLiveData);
        //Then
        LiveData<ApiResponse<DouBanMovieResponseData>> actualLiveData = doubanMovieRepository.getMovie(0);

        Mockito.verify(doubanMovieAPI).getMovie(queryMapCaptor.capture());

        assertEquals("20", queryMapCaptor.getValue().get("count"));
        assertEquals("0", queryMapCaptor.getValue().get("start"));

        assertNotNull(actualLiveData.getValue());
    }
}