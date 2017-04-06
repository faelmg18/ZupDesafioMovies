package com.br.desafiozup.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.desafiozup.R;
import com.br.desafiozup.model.MenuDrawerItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;

public class MenuDrawerAdapter extends BaseListViewAdapter<MenuDrawerItem> {

    public int selectedPosition;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = new DisplayImageOptions.Builder()
            .displayer(new RoundedBitmapDisplayer(120))
            .showImageOnLoading(R.drawable.movieempty)
            .showImageForEmptyUri(R.drawable.movieempty)
            .showImageOnFail(R.drawable.movieempty)
            .cacheInMemory(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .build();

    public MenuDrawerAdapter(ArrayList<MenuDrawerItem> dataList, Context context) {
        super(dataList, context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.menu_drawe_item;
    }

    @Override
    protected View myGetView(int position, View convertView, ViewGroup parent) {

        MenuDrawerItem item = dataList.get(position);
        View view = convertView;
        ImageView imageMenu = null;
        TextView titleMenu = null;

        if (item.isHeader()) {
            view = LayoutInflater.from(mContext).inflate(R.layout.nav_header, null);

            TextView nameUser = (TextView) view.findViewById(R.id.name_user);
            ImageView imageProfile = (ImageView) view.findViewById(R.id.image_profile);
            imageProfile.setImageResource(R.drawable.logo_zup);
            nameUser.setText(item.getNameUser());

        } else {
            view = LayoutInflater.from(mContext).inflate(getLayoutId(), null);
            titleMenu = (TextView) view.findViewById(R.id.text_view_item_drawer);
            titleMenu.setText(item.getMenuName());
            imageMenu = (ImageView) view.findViewById(R.id.image_view_item_drawer);
            imageMenu.setImageResource(item.getImageResource());
        }

        if (selectedPosition == position) {
            view.setBackgroundResource(R.color.selected);
            imageMenu.setColorFilter(ContextCompat.getColor(mContext, R.color.colorPrimary));
            titleMenu.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
        } else {
            view.setBackgroundResource(R.color.white);
            if (imageMenu != null) {
                imageMenu.setColorFilter(ContextCompat.getColor(mContext, R.color.dialog_content_color));
                titleMenu.setTextColor(ContextCompat.getColor(mContext, android.R.color.primary_text_light));
            }
        }

        return view;
    }
}
