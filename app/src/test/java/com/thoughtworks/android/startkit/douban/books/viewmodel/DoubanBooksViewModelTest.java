package com.thoughtworks.android.startkit.douban.books.viewmodel;

import com.thoughtworks.android.startkit.douban.books.data.domain.BookData;
import com.thoughtworks.android.startkit.douban.books.repository.IDoubanBooksRepository;
import com.thoughtworks.android.startkit.wrapper.Event;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import static com.thoughtworks.android.startkit.douban.books.viewmodel.DoubanBooksViewModel.START_LOADING;
import static com.thoughtworks.android.startkit.douban.books.viewmodel.DoubanBooksViewModel.START_LOADING_MORE;
import static com.thoughtworks.android.startkit.douban.books.viewmodel.DoubanBooksViewModel.STOP_LOADING;
import static com.thoughtworks.android.startkit.douban.books.viewmodel.DoubanBooksViewModel.STOP_LOADING_MORE;
import static org.junit.Assert.*;

public class DoubanBooksViewModelTest {

    @InjectMocks
    private DoubanBooksViewModel doubanBooksViewModel;
    @Mock
    private IDoubanBooksRepository doubanBooksRepository;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetNonNullBooksByDefault() {
        assertNotNull(doubanBooksViewModel.getBooks());
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
    public void shouldCallRepositoryToLoadMoreIfNotYetLoading() {
        MutableLiveData<BookData> expectedLiveData = new MutableLiveData<>();
        final int expectedStartIndex = 0;

        Mockito.when(doubanBooksRepository.getBooks(expectedStartIndex)).thenReturn(expectedLiveData);

        doubanBooksViewModel.loadMore(expectedStartIndex);

        Mockito.verify(doubanBooksRepository).getBooks(expectedStartIndex);

        assertEquals(expectedLiveData, doubanBooksViewModel.getBooks());
    }

    @Test
    public void shouldNotCallRepositoryToLoadMoreIfAlreadyLoading() {
        MutableLiveData<BookData> expectedLiveData = new MutableLiveData<>();
        final int expectedStartIndex = 0;

        Mockito.when(doubanBooksRepository.getBooks(expectedStartIndex)).thenReturn(expectedLiveData);

        doubanBooksViewModel.loadingEvent.setValue(new Event<>(START_LOADING));
        doubanBooksViewModel.loadMore(expectedStartIndex);

        Mockito.verify(doubanBooksRepository, Mockito.never()).getBooks(expectedStartIndex);
    }
}