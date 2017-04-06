package com.br.desafiozup.persistence;


import com.br.desafiozup.model.Movie;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public interface MovieRepository extends BaseRepository {

    void delete(Movie movie);

    List<Movie> retrieveAll();

    List<Movie>retrieveAllByName(String movieName);
    LinkedHashMap<String, ArrayList<Movie>> retrieveAllGroupBy();

    long save(Movie movie);

    Movie retrieveById(long id);
}
