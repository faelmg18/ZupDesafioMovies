package com.br.desafiozup.ui.screens.activities;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.br.desafiozup.R;
import com.br.desafiozup.model.Movie;
import com.br.desafiozup.ui.components.DialogBuilder;

public class MovieDetail extends BaseActivity {

    public static final String EXTRA_CONTACT = "detail";

    private TextView textViewTitleMovie;
    private TextView textViewActors;
    private TextView textViewPlot;
    private ImageView imageViewMovie;
    private FloatingActionButton fab;
    private Movie movie;
    @Override
    protected int getLayoutId() {
        return R.layout.movie_detail_activity;
    }

    @Override
    protected void myOnCreate() {

        getSupportActionBar().setTitle(getString(R.string.stringEmpty));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        fab = (FloatingActionButton) findViewById(R.id.fab);
        textViewTitleMovie = (TextView) findViewById(R.id.text_view_title_movie);
        textViewActors = (TextView) findViewById(R.id.text_view_actors);
        textViewPlot = (TextView) findViewById(R.id.text_view_plot);
        imageViewMovie = (ImageView) findViewById(R.id.imageView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(movie != null){
                    DialogBuilder.showDialogPositiveNegative(MovieDetail.this, getString(R.string.stringEmpty), getString(R.string.remove_movie), new DialogBuilder.ButtonCallback() {
                        @Override
                        protected void onPositive(AlertDialog.Builder builder, DialogInterface dialogInterface) {
                            movieRepository.delete(movie);
                            setResult(RESULT_OK);
                            finish();
                            Toast.makeText(MovieDetail.this, R.string.deleted_movie_successfully,Toast.LENGTH_LONG).show();
                        }

                        @Override
                        protected void onNegative(AlertDialog.Builder builder, DialogInterface dialogInterface) {
                            dialogInterface.dismiss();
                        }
                    });
                }
            }
        });

        if (bundle != null) {
            long id = bundle.getLong("id");
            movie = movieRepository.retrieveById(id);

            loadMovieToViewScreen(movie);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadMovieToViewScreen(Movie movie) {

        if(movie == null){
            finish();
        }

        textViewTitleMovie.setText(movie.getTitle());
        textViewActors.setText(movie.getActors());
        textViewPlot.setText(movie.getPlot());
        imageLoader.displayImage(movie.getPoster(), imageViewMovie, options);
    }
}
