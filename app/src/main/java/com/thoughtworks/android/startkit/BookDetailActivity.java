package com.thoughtworks.android.startkit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.thoughtworks.android.startkit.douban.movie.data.domain.MovieItem;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BookDetailActivity extends Activity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.information)
    TextView information;
    @BindView(R.id.summary)
    TextView summary;
    @BindView(R.id.thumbnail)
    ImageView image;
    @BindView(R.id.rating)
    RatingBar ratingBar;
    @BindView(R.id.ratingValue)
    TextView ratingVal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        MovieItem book = intent.getParcelableExtra("BOOK");

        title.setText(book.getItemTitle());
        summary.setText(book.getItemSummary());
        information.setText(book.getInformation());
        ratingBar.setRating((float) (book.getItemRating() / 2));
        ratingVal.setText(String.valueOf(book.getItemRating()));


        Glide.with(StartkitApplication.getApplication())
                .load(book.getItemImage())
                .apply(RequestOptions
                        .placeholderOf(R.drawable.ic_default_cover)
                        .centerCrop()
                        .dontAnimate()
                        .dontTransform())
                .into(image);

    }
}
