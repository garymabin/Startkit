package com.thoughtworks.android.startkit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class BookDetailActivity extends Activity {


    public TextView title;
    public TextView information;
    public TextView summary;
    public ImageView image;
    public RatingBar ratingBar;
    public TextView ratingVal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        Intent intent = getIntent();
        BookItem book = intent.getParcelableExtra("BOOK");

        title = (TextView) findViewById(R.id.title);
        information = (TextView) findViewById(R.id.information);
        summary = (TextView) findViewById(R.id.summary);
        image = (ImageView) findViewById(R.id.thumbnail);
        ratingBar = (RatingBar) findViewById(R.id.rating);
        ratingVal = (TextView) findViewById(R.id.ratingValue);


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
