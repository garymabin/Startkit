package com.thoughtworks.android.startkit.douban.books.repository;


import com.thoughtworks.android.startkit.douban.books.data.domain.BookData;
import com.thoughtworks.android.startkit.douban.books.data.api.IDoubanBooksAPI;
import com.thoughtworks.android.startkit.douban.books.data.domain.DouBanResponseData;

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

public class DoubanBooksRepositoryTest {

    @Mock
    private IDoubanBooksAPI doubanBooksAPI;

    @InjectMocks
    private DoubanBooksRepository doubanBooksRepository;

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
        List<DouBanResponseData.BooksBean> expectedBooks = Collections.emptyList();

        //When
        Mockito.when(doubanBooksAPI.getBooks(ArgumentMatchers.any()))
                .thenReturn(Single.just(
                        DouBanResponseData.builder()
                                .books(expectedBooks)
                                .build()));
        //Then
        LiveData<BookData> actualLiveData = doubanBooksRepository.getBooks(0);

        Mockito.verify(doubanBooksAPI).getBooks(queryMapCaptor.capture());

        assertEquals("IT", queryMapCaptor.getValue().get("tag"));
        assertEquals("20", queryMapCaptor.getValue().get("count"));
        assertEquals("0", queryMapCaptor.getValue().get("start"));

        assertNull(actualLiveData.getValue());
    }
}