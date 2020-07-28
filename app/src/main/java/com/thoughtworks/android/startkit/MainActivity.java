package com.thoughtworks.android.startkit;

import android.os.Bundle;

import com.thoughtworks.android.startkit.douban.movie.view.MovieItemListFragment;

import androidx.fragment.app.FragmentActivity;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new MovieItemListFragment()).commit();
        }
    }
}