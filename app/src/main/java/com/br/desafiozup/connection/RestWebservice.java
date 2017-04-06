package com.br.desafiozup.connection;

import android.net.Uri;

import com.br.desafiozup.model.Movie;
import com.br.desafiozup.util.AndroidAppException;

import org.apache.http.message.BasicHeader;

import java.net.URLEncoder;

public class RestWebservice extends WebServiceComun {

    public static RestWebservice instance;

    public static RestWebservice getInstance() {
        if (instance == null)
            instance = new RestWebservice();
        return instance;
    }

    public Movie getMovie(String title) throws AndroidAppException {

        try {
            String url = Uri.parse(getUrl()).buildUpon()
                    .appendQueryParameter("t", title)

                    .build().toString();

            Movie responseObject = sendDataGet(url, Movie.class);
            return responseObject;

        } catch (Exception e) {
            e.printStackTrace();
            throw new AndroidAppException(e);
        }
    }

    public BasicHeader[] getHeaders() {
        BasicHeader[] headers = {
        };

        return headers;
    }
}
