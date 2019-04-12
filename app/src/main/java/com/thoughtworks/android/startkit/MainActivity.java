package com.thoughtworks.android.startkit;

import android.os.Bundle;

import com.thoughtworks.android.startkit.douban.books.view.BookItemListFragment;

import androidx.fragment.app.FragmentActivity;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new BookItemListFragment()).commit();
        }
    }
}