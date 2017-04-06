package com.br.desafiozup.ui.screens.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.br.desafiozup.R;
import com.br.desafiozup.persistence.MovieRepository;
import com.br.desafiozup.persistence.RepositoryFactory;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract void myOnCreate();

    protected abstract int getLayoutId();

    protected MovieRepository movieRepository;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    protected DisplayImageOptions options = new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .showImageForEmptyUri(R.drawable.movieempty)
            .showImageOnFail(R.drawable.movieempty)
            .bitmapConfig(Bitmap.Config.RGB_565).build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        movieRepository = RepositoryFactory.getInstance().createMoviesRepository();
        myOnCreate();
    }

    public void prepareActionBar(final Toolbar mToolbar, final int title) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setSupportActionBar(mToolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(true);
            }
        });
    }
}
