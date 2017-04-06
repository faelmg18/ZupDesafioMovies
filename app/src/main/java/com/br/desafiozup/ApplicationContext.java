package com.br.desafiozup;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class ApplicationContext extends Application {
    public static final String BASE_URL = "http://www.omdbapi.com/?";
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static Context getAppContext() {
        return context;
    }
}