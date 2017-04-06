package com.br.desafiozup.ui.screens.fragments;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.br.desafiozup.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public abstract class BaseFragment extends Fragment {

    abstract void myOnCreate(View view);

    abstract int getLayoutId();

    public View mView;

    public ImageLoader imageLoader = ImageLoader.getInstance();
    public DisplayImageOptions options = new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .showImageForEmptyUri(R.drawable.movieempty)
            .showImageOnFail(R.drawable.movieempty)
            .bitmapConfig(Bitmap.Config.RGB_565).build();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        mView = inflater.inflate(getLayoutId(),
                container, false);
        myOnCreate(mView);
        return mView;
    }

    public void closeKeyboard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public View findViewById(int resourceId) {
        return mView.findViewById(resourceId);
    }
}
