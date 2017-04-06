package com.br.desafiozup.model;

import java.util.ArrayList;

public class CategorizedMovies {
    private ArrayList<Movie> movies;
    private String genre;

    public CategorizedMovies(ArrayList<Movie> movie,String genre)
    {
        this.genre = genre;
        this.movies = movie;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        genre = genre;
    }
}
