package com.br.desafiozup.omdpapi;


import com.br.desafiozup.model.Movie;

public interface OmdbApiFinder {
    void onFindMovie(Movie movie);
    void onError(Exception e);
}
