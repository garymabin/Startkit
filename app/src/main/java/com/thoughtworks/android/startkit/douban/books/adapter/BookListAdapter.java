package com.thoughtworks.android.startkit.douban.books.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.thoughtworks.android.startkit.BookDetailActivity;
import com.thoughtworks.android.startkit.douban.books.data.domain.BookItem;
import com.thoughtworks.android.startkit.R;
import com.thoughtworks.android.startkit.StartkitApplication;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {

    private List<BookItem> mBooks = new ArrayList<>();

    public void addAll(List<BookItem> newBooks) {
        mBooks.addAll(newBooks);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(v);
    }

    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final BookItem data = mBooks.get(position);

        holder.title.setText(data.getItemTitle());
        holder.summary.setText(data.getItemSummary());
        holder.information.setText(data.getInformation());
        holder.ratingBar.setRating((float) (data.getItemRating() / 2));
        holder.ratingVal.setText(String.valueOf(data.getItemRating()));


        Glide.with(StartkitApplication.getApplication())
                .load(data.getItemImage())
                .apply(RequestOptions
                        .placeholderOf(R.drawable.ic_default_cover)
                        .centerCrop()
                        .dontAnimate()
                        .dontTransform())
                .into(holder.image);

        holder.itemView.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, BookDetailActivity.class);
            intent.putExtra("BOOK", mBooks.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public void clearAll() {
        mBooks.clear();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

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

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
