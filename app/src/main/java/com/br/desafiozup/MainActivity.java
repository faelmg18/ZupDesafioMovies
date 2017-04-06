package com.br.desafiozup;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.br.desafiozup.adapter.MenuDrawerAdapter;
import com.br.desafiozup.model.MenuDrawerItem;
import com.br.desafiozup.ui.screens.activities.BaseActivity;
import com.br.desafiozup.ui.screens.fragments.FindMoviesFragment;
import com.br.desafiozup.ui.screens.fragments.MyMoviesFragment;

import java.util.ArrayList;

import static com.br.desafiozup.ui.screens.fragments.MyMoviesFragment.MOVIE_DELETE;

public class MainActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ArrayList<MenuDrawerItem> items;
    private MenuDrawerAdapter adapter;
    private int CurrentPositionFragment;
    private int oldPosition;
    private Fragment CurrentFragment;

    @Override
    protected void myOnCreate() {

        items = getItemFromMenu();
        adapter = new MenuDrawerAdapter(items, this);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDrawerLayout.closeDrawers();
                gotoFragment(position);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        gotoFragment(1);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    public void gotoFragment(int position) {
        gotoFragment(position, false);
    }

    public void gotoFragment(int position, boolean reloadFragment) {
        MenuDrawerItem menuDrawer = adapter.dataList.get(position);

        if (menuDrawer.getCode() == R.integer.header_menu_id) {
            return;
        }

        getSupportActionBar().setTitle(menuDrawer.getMenuName());

        CurrentPositionFragment = position;
        adapter.selectedPosition = position;
        adapter.notifyDataSetChanged();
        listItemClicked(menuDrawer.getCode(), reloadFragment);
        invalidateOptionsMenu();
    }

    public void listItemClicked(int position, boolean reloadFragment) {

        if (position == oldPosition && !reloadFragment)
            return;

        oldPosition = position;

        Fragment fragment = null;
        switch (position) {
            case R.integer.header_menu_id:
                return;

            case R.integer.my_movies_menu_id:
                fragment = new MyMoviesFragment();
                break;
            case R.integer.find_movies_menu_id:
                fragment = new FindMoviesFragment();
                break;

        }

        CurrentFragment = fragment;

        if (fragment == null)
            return;

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == MOVIE_DELETE) {
            gotoFragment(finItemPositionByMenuId(R.integer.my_movies_menu_id), true);
        }
    }

    private ArrayList<MenuDrawerItem> getItemFromMenu() {

        ArrayList<MenuDrawerItem> listMenu = new ArrayList<>();
        listMenu.add(new MenuDrawerItem(R.integer.header_menu_id, getString(R.string.desafio_zup)));
        listMenu.add(new MenuDrawerItem(R.integer.my_movies_menu_id, getString(R.string.my_movies), R.drawable.ic_movie));
        listMenu.add(new MenuDrawerItem(R.integer.find_movies_menu_id, getString(R.string.find_movies), R.drawable.ic_magnify));
        return listMenu;
    }

    private int finItemPositionByMenuId(int menuId) {
        int len = items.size();
        for (int i = 0; i < len; i++) {
            if (items.get(i).getCode() == menuId) {
                return i;
            }
        }
        return oldPosition;
    }

    @Override
    public void onBackPressed() {

        if (oldPosition != R.integer.my_movies_menu_id) {
            gotoFragment(finItemPositionByMenuId(R.integer.my_movies_menu_id), true);
            return;
        }

        super.onBackPressed();
    }
}