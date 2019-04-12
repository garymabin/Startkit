package com.thoughtworks.android.startkit.douban.books.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtworks.android.startkit.R;
import com.thoughtworks.android.startkit.douban.books.adapter.BookListAdapter;
import com.thoughtworks.android.startkit.douban.books.viewmodel.DoubanBooksViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.thoughtworks.android.startkit.douban.books.viewmodel.DoubanBooksViewModel.START_LOADING;
import static com.thoughtworks.android.startkit.douban.books.viewmodel.DoubanBooksViewModel.START_LOADING_MORE;
import static com.thoughtworks.android.startkit.douban.books.viewmodel.DoubanBooksViewModel.STOP_LOADING;
import static com.thoughtworks.android.startkit.douban.books.viewmodel.DoubanBooksViewModel.STOP_LOADING_MORE;


public class BookItemListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private DoubanBooksViewModel bookListViewModel;

    @BindView(android.R.id.list)
    RecyclerView mListView;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.view_loading_more)
    View loadingView;

    private BookListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        bookListViewModel = ViewModelProviders.of(this).get(DoubanBooksViewModel.class);

        bookListViewModel.getBooks().observe(this, books -> {
            assert books != null;
            boolean isLoadingMode = books.getStart() != 0;
            bookListViewModel.stopLoading(isLoadingMode);
            if (isLoadingMode) {
                mAdapter.addAll(books.getBooks());
            } else {
                mAdapter.clearAll();
                mAdapter.addAll(books.getBooks());
            }
        });
        bookListViewModel.getLoadingEvent()
                .observe(this, loadingStatus -> {
                    assert loadingStatus != null;
                    switch (loadingStatus.getContent()) {
                        case START_LOADING:
                            swipeRefreshLayout.setRefreshing(true);
                            break;
                        case STOP_LOADING:
                            swipeRefreshLayout.setRefreshing(false);
                            break;
                        case START_LOADING_MORE:
                            loadingView.setVisibility(VISIBLE);
                            break;
                        case STOP_LOADING_MORE:
                            loadingView.setVisibility(INVISIBLE);
                            break;

                    }
                });
        bookListViewModel.startLoading(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_list, container, false);
        ButterKnife.bind(this, view);

        final int columns = getResources().getInteger(R.integer.gallery_columns);
        mLayoutManager = new GridLayoutManager(getContext(), columns);


        mListView.setHasFixedSize(true);
        mListView.setLayoutManager(mLayoutManager);
        mAdapter = new BookListAdapter();
        mListView.setAdapter(mAdapter);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        mListView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int visibleItemCount = mLayoutManager.getChildCount();
                int totalItemCount = mLayoutManager.getItemCount();
                int firstVisibleItem = ((LinearLayoutManager) mLayoutManager).findFirstVisibleItemPosition();

                if (totalItemCount > 0) {
                    int lastVisibleItem = firstVisibleItem + visibleItemCount;
                    if (lastVisibleItem == totalItemCount) {
                        bookListViewModel.loadMore(mAdapter.getItemCount());
                    }
                }
            }
        });
        return view;
    }

    @Override
    public void onRefresh() {
        bookListViewModel.refresh();
    }
}