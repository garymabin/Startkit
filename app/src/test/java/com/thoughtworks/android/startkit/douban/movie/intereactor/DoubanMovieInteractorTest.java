package com.thoughtworks.android.startkit.douban.movie.intereactor;

import com.thoughtworks.android.startkit.AppDatabase;
import com.thoughtworks.android.startkit.AppExecutors;
import com.thoughtworks.android.startkit.douban.movie.adapter.MovieListAdapter;
import com.thoughtworks.android.startkit.douban.movie.data.persistence.IMovieDao;
import com.thoughtworks.android.startkit.douban.movie.data.persistence.impl.RoomMovie;
import com.thoughtworks.android.startkit.douban.movie.data.persistence.impl.RoomMovieDao;
import com.thoughtworks.android.startkit.douban.movie.data.vo.DouBanMovieResponseData;
import com.thoughtworks.android.startkit.douban.movie.data.vo.MovieData;
import com.thoughtworks.android.startkit.douban.movie.repository.IDoubanMovieRepository;
import com.thoughtworks.android.startkit.douban.movie.rule.RxSchedulersOverrideRule;
import com.thoughtworks.android.startkit.retrofit.ApiResponse;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import retrofit2.Response;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.calls;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DoubanMovieInteractorTest {

    @Mock
    IMovieDao<RoomMovie> movieDao;

    @Mock
    IDoubanMovieRepository doubanMovieRepository;

    @Mock
    AppExecutors appExecutors;

    @InjectMocks
    DoubanMovieInteractor doubanMovieInteractor;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();
    @Rule
    public RxSchedulersOverrideRule rxSchedulersOverrideRule = new RxSchedulersOverrideRule();


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldLoadMovieFromNetworkIfLocalDatabaseEmpty() {
        //Given
        int startIndex = 0;
        Executor ioExecutor = mock(Executor.class);

        LiveData<List<RoomMovie>> roomLiveData = new MutableLiveData<>();
        ((MutableLiveData<List<RoomMovie>>) roomLiveData).setValue(
                Collections.emptyList());

        List<DouBanMovieResponseData.MovieBean> expectedBooks = Collections.emptyList();
        LiveData<ApiResponse<DouBanMovieResponseData>> apiResponseLiveData = new MutableLiveData<>();
        ((MutableLiveData<ApiResponse<DouBanMovieResponseData>>) apiResponseLiveData).setValue(
                new ApiResponse<>(Response.success(DouBanMovieResponseData.builder()
                        .subjects(expectedBooks)
                        .build())));

        //When
        when(movieDao.getAll()).thenReturn(roomLiveData);
        when(doubanMovieRepository.getMovie(startIndex)).thenReturn(apiResponseLiveData);
        when(appExecutors.getDiskIO()).thenReturn(ioExecutor);

        doubanMovieInteractor.getResult().observeForever(movieData -> {
        });

        doubanMovieInteractor.loadMovie(startIndex);

        //Then
        verify(doubanMovieRepository).getMovie(startIndex);
        verify(ioExecutor).execute(any());
    }
}