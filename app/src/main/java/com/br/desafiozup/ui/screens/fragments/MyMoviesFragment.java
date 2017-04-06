package com.br.desafiozup.ui.screens.fragments;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.br.desafiozup.R;
import com.br.desafiozup.adapter.CategorizedMoviesAdapter;
import com.br.desafiozup.adapter.MoviesAdapter;
import com.br.desafiozup.model.CategorizedMovies;
import com.br.desafiozup.model.Movie;
import com.br.desafiozup.omdpapi.OmdbApi;
import com.br.desafiozup.persistence.MovieRepository;
import com.br.desafiozup.persistence.RepositoryFactory;
import com.br.desafiozup.ui.screens.activities.MovieDetail;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class MyMoviesFragment extends BaseFragment {

    public static final int MOVIE_DELETE = 012;
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;
    private MovieRepository movieRepository;
    private LinkedHashMap<String, ArrayList<Movie>> hasMapMovies;

    @Override
    int getLayoutId() {
        return R.layout.my_movies_list;
    }


    @Override
    void myOnCreate(View view) {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        findMovies();
    }

    private void findMovies() {
        movieRepository = RepositoryFactory.getInstance().createMoviesRepository();
        hasMapMovies = movieRepository.retrieveAllGroupBy();
        CategorizedMoviesAdapter mAdapter = new CategorizedMoviesAdapter(getActivity());
        addMoviesCategorizedOnAdapter(mAdapter);
        setUpAdapter(mAdapter);
    }

    private void setUpAdapter(CategorizedMoviesAdapter mAdapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    private void addMoviesCategorizedOnAdapter(CategorizedMoviesAdapter mAdapter) {
        for (String key : hasMapMovies.keySet()) {
            mAdapter.addMovies(new CategorizedMovies(hasMapMovies.get(key), key));
        }
    }
}
