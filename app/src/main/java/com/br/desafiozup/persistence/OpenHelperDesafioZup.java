package com.br.desafiozup.persistence;


import android.content.Context;

import com.br.desafiozup.database.DatabaseHelper;
import com.br.desafiozup.database.ReflectionException;
import com.br.desafiozup.database.TableUtils;
import com.br.desafiozup.model.Movie;

public class OpenHelperDesafioZup extends DatabaseHelper {

    public static final String DATA_BASE_NAME = "databasedesafiozup_homolog";
   /* public static final String DATA_BASE_NAME = "databasedesafiozup_prod";*/
    private static final int DATA_BASE_VERSION = 4;
    private volatile static OpenHelperDesafioZup instance = null;

    private OpenHelperDesafioZup(Context context) {
        super(context, DATA_BASE_NAME, DATA_BASE_VERSION);
    }

    public static OpenHelperDesafioZup getInstance(Context context) {
        if (instance == null) {
            instance = new OpenHelperDesafioZup(context.getApplicationContext());
        }

        return instance;
    }

    @Override
    public String[] getScriptsCreateDataBase() throws ReflectionException {
        String[] script = {
                TableUtils.createTableScript(Movie.class),
        };

        return script;
    }

    @Override
    public String[] getScriptsUpdateDataBase() throws ReflectionException {
        String[] script = {
                TableUtils.createDropTableScript(Movie.class),
        };

        return script;
    }
}
