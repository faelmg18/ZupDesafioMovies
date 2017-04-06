package com.br.desafiozup.persistence;

import android.content.Context;

import com.br.desafiozup.model.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class MovieDAO extends BaseDAOOS<Movie> implements MovieRepository {

    private static volatile MovieDAO instance;


    public MovieDAO(Context context) {
        super(context);
    }

    public static MovieDAO getInstance(Context context) {

        if (instance == null) {
            instance = new MovieDAO(context);
        }

        return instance;
    }

    @Override
    public Class<Movie> getEntitieClass() {
        return Movie.class;
    }

    @Override
    public List<Movie> retrieveAll() {
        return retrieveAllMovies();
    }

    @Override
    public List<Movie> retrieveAllByName(String movieName) {
        String sql = "SELECT * FROM " + getEntitieClass().getSimpleName() + " where title like '" + movieName + "'";
        List<Movie> movies = getElementsRawQuery(sql, null);
        return movies;
    }

    @Override
    public LinkedHashMap<String, ArrayList<Movie>> retrieveAllGroupBy() {
        ArrayList<Movie> movies = (ArrayList<Movie>) retrieveAll();
        LinkedHashMap<String, ArrayList<Movie>> moviesCategorized = createMoviesCategorized(movies);
        return moviesCategorized;
    }


    @Override
    public Movie retrieveById(long id) {
        return getEntitieByID(id);
    }

    private List<Movie> retrieveAllMovies() {

        String sql = "SELECT * FROM " + getEntitieClass().getSimpleName() + " order by genre";
        List<Movie> clienteModels = getElementsRawQuery(sql, null);

        return clienteModels;
    }

    private LinkedHashMap<String, ArrayList<Movie>> createMoviesCategorized(ArrayList<Movie> movies) {

        LinkedHashMap<String, ArrayList<Movie>> hasMapMovies = new java.util.LinkedHashMap<>();

        for (Movie movie : movies) {

            if (!hasMapMovies.containsKey(movie.getGenre())) {
                ArrayList<Movie> list = new ArrayList<>();
                list.add(movie);

                hasMapMovies.put(movie.getGenre(), list);
            } else {
                hasMapMovies.get(movie.getGenre()).add(movie);
            }
        }

        return hasMapMovies;
    }
}
