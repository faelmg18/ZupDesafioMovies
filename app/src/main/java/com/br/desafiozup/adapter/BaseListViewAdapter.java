package com.br.desafiozup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public abstract class BaseListViewAdapter<T> extends android.widget.BaseAdapter {

    public ArrayList<T> dataList;
    public Context mContext;
    private View view;
    public int currentPosition;

    protected abstract int getLayoutId();

    protected abstract View myGetView(int position, View convertView, ViewGroup parent);

    public BaseListViewAdapter(ArrayList<T> dataList, Context context) {
        this.dataList = dataList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        this.currentPosition = position;
        return myGetView(position, view, parent);
    }
}
