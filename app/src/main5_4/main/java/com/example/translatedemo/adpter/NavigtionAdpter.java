package com.example.translatedemo.adpter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.translatedemo.R;

public class NavigtionAdpter extends Fragment {
//    private View view;
    private LinearLayout Naviagion;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    public LinearLayout Home;
    public Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.Naviagion= (LinearLayout) inflater.inflate(R.layout.navigation_custom,container,false);
//        view= inflater.inflate(R.layout.navigation_custom,container,false);
        this.Home=(LinearLayout)this.Naviagion.findViewById(R.id.lnHome);
        return this.Naviagion;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar){
        setActionBar(toolbar);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,android.R.string.no,android.R.string.no);
        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setHomeAsUpIndicator(R.drawable.icon_menu);
    }
    private void setActionBar(Toolbar toolbar) {
        ((AppCompatActivity)this.getActivity()).setSupportActionBar(toolbar);
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity)this.getActivity()).getSupportActionBar();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                this.mActionBarDrawerToggle.onOptionsItemSelected(item);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
//    }
}
