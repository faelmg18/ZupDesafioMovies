package com.br.desafiozup.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.desafiozup.R;
import com.br.desafiozup.model.Movie;
import com.br.desafiozup.ui.screens.activities.MovieDetail;
import com.br.desafiozup.ui.screens.fragments.MyMoviesFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Movie> moviesList;
    private Activity mContext;
    public OnItemClick<Movie> listener;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .showImageForEmptyUri(R.drawable.movieempty)
            .showImageOnFail(R.drawable.movieempty)
            .bitmapConfig(Bitmap.Config.RGB_565).build();

    public MoviesAdapter(Activity context, ArrayList<Movie> arrayList, OnItemClick<Movie> listener) {
        mContext = context;
        moviesList = arrayList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item_row, parent, false);

        return new MyViewHolder(itemView);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, year, genre, ratingTextView;
        public ImageView imagePhotAlbum;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.nameTextView);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
            imagePhotAlbum = (ImageView) view.findViewById(R.id.imageView);
            ratingTextView = (TextView) view.findViewById(R.id.ratingTextView);
        }

        @Override
        public void onClick(View v) {

            if (listener != null) {
                Movie movie = moviesList.get(getAdapterPosition());
                listener.onItemClick(movie, getAdapterPosition(), imagePhotAlbum);
            }
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final Movie movie = moviesList.get(position);

        holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getGenre());
        holder.year.setText(movie.getYear());
        holder.ratingTextView.setText(movie.getImdbRating());

        imageLoader.displayImage(movie.getPoster(), holder.imagePhotAlbum, options);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MovieDetail.class);
                Pair<View, String> p1 = Pair.create((View) holder.imagePhotAlbum, "photoAlbum");
                Pair<View, String> p2 = Pair.create((View) holder.title, "titleMovie");

                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(mContext, p1, p2);

                Bundle bundle = options.toBundle();
                bundle.putLong("id", movie.getId());
                intent.putExtras(bundle);
                mContext.startActivityForResult(intent, MyMoviesFragment.MOVIE_DELETE, bundle);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public interface OnItemClick<T> {
        void onItemClick(T item, int position, ImageView imageView);
    }
}