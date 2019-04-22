package com.thoughtworks.android.startkit.douban.movie.viewmodel;

import com.thoughtworks.android.startkit.douban.movie.data.vo.MovieData;
import com.thoughtworks.android.startkit.douban.movie.repository.IDoubanMovieRepository;
import com.thoughtworks.android.startkit.wrapper.Event;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import static com.thoughtworks.android.startkit.douban.movie.viewmodel.DoubanMovieViewModel.START_LOADING;
import static com.thoughtworks.android.startkit.douban.movie.viewmodel.DoubanMovieViewModel.START_LOADING_MORE;
import static com.thoughtworks.android.startkit.douban.movie.viewmodel.DoubanMovieViewModel.STOP_LOADING;
import static com.thoughtworks.android.startkit.douban.movie.viewmodel.DoubanMovieViewModel.STOP_LOADING_MORE;
import static org.junit.Assert.*;

public class DoubanMovieViewModelTest {

    @InjectMocks
    private DoubanMovieViewModel doubanBooksViewModel;
    @Mock
    private IDoubanMovieRepository doubanBooksRepository;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFireStartLoadingMoreEventIfStartLoadingMore() {
        doubanBooksViewModel.startLoading(true);

        assertEquals(new Event<>(START_LOADING_MORE), doubanBooksViewModel.loadingEvent.getValue());
    }

    @Test
    public void shouldFireStartLoadingEventIfStartLoadingMore() {
        doubanBooksViewModel.startLoading(false);

        assertEquals(new Event<>(START_LOADING), doubanBooksViewModel.loadingEvent.getValue());
    }

    @Test
    public void shouldFireStopLoadingMoreEventIfStopLoading() {
        doubanBooksViewModel.stopLoading(true);

        assertEquals(new Event<>(STOP_LOADING_MORE), doubanBooksViewModel.loadingEvent.getValue());
    }

    @Test
    public void shouldFireStopLoadingEventIfStopLoading() {
        doubanBooksViewModel.stopLoading(false);

        assertEquals(new Event<>(STOP_LOADING), doubanBooksViewModel.loadingEvent.getValue());
    }

    @Test
    @Ignore
    public void shouldCallRepositoryToLoadMoreIfNotYetLoading() {
        MutableLiveData<MovieData> expectedLiveData = new MutableLiveData<>();
        final int expectedStartIndex = 0;

//        Mockito.when(doubanBooksRepository.getMovie(expectedStartIndex)).thenReturn(expectedLiveData);

        doubanBooksViewModel.loadMore(expectedStartIndex);

        Mockito.verify(doubanBooksRepository).getMovie(expectedStartIndex);

        assertEquals(expectedLiveData, doubanBooksViewModel.getMovies());
    }

    @Test
    @Ignore
    public void shouldNotCallRepositoryToLoadMoreIfAlreadyLoading() {
        MutableLiveData<MovieData> expectedLiveData = new MutableLiveData<>();
        final int expectedStartIndex = 0;

//        Mockito.when(doubanBooksRepository.getMovie(expectedStartIndex)).thenReturn(expectedLiveData);

        doubanBooksViewModel.loadingEvent.setValue(new Event<>(START_LOADING));
        doubanBooksViewModel.loadMore(expectedStartIndex);

        Mockito.verify(doubanBooksRepository, Mockito.never()).getMovie(expectedStartIndex);
    }
}