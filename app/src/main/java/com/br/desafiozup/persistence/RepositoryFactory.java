package com.br.desafiozup.persistence;

import android.content.Context;

import com.br.desafiozup.ApplicationContext;

public class RepositoryFactory {

    private static volatile RepositoryFactory instance;
    private Context context;

    private RepositoryFactory() {
    }

    private RepositoryFactory(Context context) {
        this.context =  context;
    }

    public static RepositoryFactory getInstance() {
        if (instance == null) {
            instance = new RepositoryFactory();
        }

        return instance;
    }

    public static RepositoryFactory getInstance(Context context) {
        if (instance == null) {
            instance = new RepositoryFactory(context);
        }

        return instance;
    }


    public MovieRepository createMoviesRepository() {
        return MovieDAO.getInstance(retrieveContext());
    }

    private Context retrieveContext() {
        if(context == null) {
            return ApplicationContext.getAppContext();
        } else {
            return context;
        }

    }
}
