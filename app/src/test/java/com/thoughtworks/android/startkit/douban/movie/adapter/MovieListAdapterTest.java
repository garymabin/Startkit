package com.thoughtworks.android.startkit.douban.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.thoughtworks.android.startkit.BookDetailActivity;
import com.thoughtworks.android.startkit.MainActivity;
import com.thoughtworks.android.startkit.R;
import com.thoughtworks.android.startkit.douban.movie.adapter.MovieListAdapter.ViewHolder;
import com.thoughtworks.android.startkit.douban.movie.data.vo.Movie;
import com.thoughtworks.android.startkit.douban.movie.data.vo.MovieItem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.Arrays;
import java.util.Collections;

import androidx.fragment.app.Fragment;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class MovieListAdapterTest {

    @Mock
    private View mockedView;

    @Mock
    private Fragment mockedFragment;

    private MovieListAdapter movieListAdapter;

    @Before
    public void setUp () {
        MockitoAnnotations.initMocks(this);
        movieListAdapter = new MovieListAdapter();
        when(mockedFragment.getString(anyInt())).thenReturn("Movie");
    }

    @Test
    public void onCreateViewHolder() {
    }

    @Test
    public void onBindViewHolder_setsTextAndClickEventForMovieItemView() {
        MovieItem movie = new Movie();
        ((Movie) movie).setItemTitle("The Matrix");

        MainActivity spyOnMain = Mockito.spy(Robolectric.buildActivity(MainActivity.class).get());

        movieListAdapter.addAll(Collections.singletonList(movie));

        LayoutInflater inflater = (LayoutInflater) spyOnMain.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View listItemView = inflater.inflate(R.layout.list_item_book, null, false);

        ViewHolder viewHolder = new ViewHolder(listItemView);
        movieListAdapter.onBindViewHolder(viewHolder, 0);

        Assert.assertEquals("The Matrix", viewHolder.title.getText());

        viewHolder.itemView.performClick();
        Intent intent = new Intent(spyOnMain, BookDetailActivity.class);
        intent.putExtra("BOOK", movie);

        verify(spyOnMain).startActivity(intent);
    }

    @Test
    public void testGetItemCountAndClear() {
        MovieItem movie = new Movie();
        movieListAdapter.addAll(Arrays.asList(movie, movie, movie));
        Assert.assertEquals(3, movieListAdapter.getItemCount());

        movieListAdapter.clearAll();

        Assert.assertEquals(0, movieListAdapter.getItemCount());
    }
}