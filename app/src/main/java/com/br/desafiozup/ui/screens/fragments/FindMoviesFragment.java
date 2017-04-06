package com.br.desafiozup.ui.screens.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.br.desafiozup.R;
import com.br.desafiozup.model.Movie;
import com.br.desafiozup.omdpapi.OmdApiFinderImp;
import com.br.desafiozup.omdpapi.OmdbApi;
import com.br.desafiozup.persistence.MovieRepository;
import com.br.desafiozup.persistence.RepositoryFactory;
import com.br.desafiozup.ui.components.ConnectionUtils;
import com.br.desafiozup.ui.components.DialogBuilder;
import com.br.desafiozup.ui.components.DrawableClickListener;
import com.br.desafiozup.ui.components.DrawablePosition;
import com.br.desafiozup.ui.components.SearchEditext;
import com.br.desafiozup.ui.components.SimpleViewAnimator;

import java.util.List;

public class FindMoviesFragment extends BaseFragment {
    private SearchEditext searchEditext;
    private MovieRepository movieRepository;
    private OmdbApi omdbApi;
    private TextView textViewTitleMovie;
    private TextView textViewActors;
    private TextView textViewPlot;
    private SimpleViewAnimator viewAnimator;
    private FloatingActionButton fabSave;
    private ImageView imageViewMovie;
    private Movie movieToSave;


    @Override
    int getLayoutId() {
        return R.layout.find_movies_frament;
    }

    @Override
    void myOnCreate(View view) {

        movieRepository = RepositoryFactory.getInstance().createMoviesRepository();
        omdbApi = new OmdbApi(getActivity());
        viewAnimator = (SimpleViewAnimator) findViewById(R.id.animated_view);

        searchEditext = (SearchEditext) findViewById(R.id.edit_text_serach_movie);
        searchEditext.setDrawableClickListener(new DrawableClickListener() {
            @Override
            public void onClick(DrawablePosition target) {
                findMovies();
            }
        });
        searchEditext.addDrawableAnimationOnTextChange(getActivity());
        searchEditext.setImeOptions(EditorInfo.IME_ACTION_DONE);

        textViewTitleMovie = (TextView) findViewById(R.id.text_view_title_movie);
        textViewActors = (TextView) findViewById(R.id.text_view_actors);
        textViewPlot = (TextView) findViewById(R.id.text_view_plot);
        imageViewMovie = (ImageView) findViewById(R.id.imageView);

        fabSave = (FloatingActionButton) findViewById(R.id.fab);
        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (movieToSave != null) {
                    movieRepository.save(movieToSave);
                    Toast.makeText(getActivity(), R.string.movie_saved, Toast.LENGTH_LONG).show();
                    movieToSave = null;
                }
            }
        });

        viewAnimator.setInAnimation(AnimationUtils.loadAnimation(
                getActivity(),
                R.anim.slide_up));

        viewAnimator.setOutAnimation(AnimationUtils.loadAnimation(
                getActivity(),
                R.anim.slide_down));

        performSearch();
    }

    private void performSearch() {
        searchEditext.clearFocus();
        InputMethodManager in = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(searchEditext.getWindowToken(), 0);
    }

    private void findMovies() {
        String movieTitle = searchEditext.getText().toString();
        cleanMoveToView();
        List<Movie> movies = movieRepository.retrieveAllByName(movieTitle);

        if (movies.size() == 0) {
            if (ConnectionUtils.isOnline(getContext())) {
                omdbApi.findMovie(movieTitle, new OmdApiFinderImp() {
                    @Override
                    public void onFindMovie(Movie movie) {
                        loadMovieToViewScreen((movie));
                    }

                    @Override
                    public void onError(Exception e) {
                        DialogBuilder.showErrorServerInformation(getContext());
                    }
                });
            } else {
                DialogBuilder.showDialogConfirm(getContext(), getString(R.string.you_are_not_connected), getString(R.string.connection_not_found), new DialogBuilder.ButtonCallback() {
                    @Override
                    protected void onAny(AlertDialog.Builder builder, DialogInterface dialogInterface) {
                        dialogInterface.dismiss();
                    }
                });
            }

        } else {
            Movie movie = movies.get(0);
            loadMovieToViewScreen(movie);
        }
        searchEditext.setText("");
    }

    public void cleanMoveToView() {
        closeKeyboard(searchEditext);
        textViewTitleMovie.setText("");
        textViewActors.setText("");
        textViewPlot.setText("");
        viewAnimator.setVisibility(View.GONE);
        imageViewMovie.setImageBitmap(null);
    }

    private void loadMovieToViewScreen(Movie movie) {
        if (Boolean.parseBoolean(movie.getResponse())) {
            movieToSave = movie;
            textViewTitleMovie.setText(movie.getTitle());
            textViewActors.setText(movie.getActors());
            textViewPlot.setText(movie.getPlot());
            viewAnimator.setVisibility(View.VISIBLE);
            imageLoader.displayImage(movie.getPoster(), imageViewMovie, options);
        } else {
            Toast.makeText(getActivity(), R.string.movie_not_found, Toast.LENGTH_LONG).show();
        }
    }
}
