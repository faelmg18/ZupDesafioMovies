package com.br.desafiozup.adapter;

import android.app.Activity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.desafiozup.R;
import com.br.desafiozup.model.CategorizedMovies;
import com.br.desafiozup.model.Movie;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CategorizedMoviesAdapter extends RecyclerView.Adapter<CategorizedMoviesAdapter.MyViewHolderMoviesCategorized> implements GravitySnapHelper.SnapListener {

    private ArrayList<CategorizedMovies> mCategorizedMovies;
    private Activity mActivity;

    public CategorizedMoviesAdapter(Activity activity) {
        mCategorizedMovies = new ArrayList<>();
        mActivity = activity;
    }

    public void addMovies(CategorizedMovies snap) {
        mCategorizedMovies.add(snap);
    }

    @Override
    public CategorizedMoviesAdapter.MyViewHolderMoviesCategorized onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categorized_movies_item, parent, false);

        return new CategorizedMoviesAdapter.MyViewHolderMoviesCategorized(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        return Gravity.CENTER_HORIZONTAL;
    }

    @Override
    public void onBindViewHolder(final CategorizedMoviesAdapter.MyViewHolderMoviesCategorized holder, final int position) {

        CategorizedMovies categorizedMovies = mCategorizedMovies.get(position);

        ArrayList<Movie> movies = categorizedMovies.getMovies(); /*(ArrayList<Movie>) getElementByIndex(hasMapMovies, position);*/

        MoviesAdapter mAdapter = new MoviesAdapter(mActivity, movies, new MoviesAdapter.OnItemClick<Movie>() {
            @Override
            public void onItemClick(Movie item, int position, ImageView imageView) {

            }
        });

        holder.textViewtextGenre.setText(categorizedMovies.getGenre());

        holder.recyclerView.setLayoutManager(new LinearLayoutManager(holder
                .recyclerView.getContext(), LinearLayoutManager.HORIZONTAL, false));

        holder.recyclerView.setOnFlingListener(null);

        new GravitySnapHelper(Gravity.START, false, this).attachToRecyclerView(holder.recyclerView);

        holder.recyclerView.setItemAnimator(new DefaultItemAnimator());
        holder.recyclerView.setAdapter(mAdapter);

    }

    @Override
    public int getItemCount() {
        return mCategorizedMovies.size();
    }

    @Override
    public void onSnap(int position) {
        Log.d("Snapped: ", position + "");
    }

    public class MyViewHolderMoviesCategorized extends RecyclerView.ViewHolder {
        public RecyclerView recyclerView;
        public TextView textViewtextGenre;

        public MyViewHolderMoviesCategorized(View view) {
            super(view);
            recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
            textViewtextGenre = (TextView) view.findViewById(R.id.text_view_movie_genre);
        }
    }

    public List<Movie> getElementByIndex(LinkedHashMap map, int index) {
        return (List<Movie>) map.get((map.keySet().toArray())[index]);
    }
}
