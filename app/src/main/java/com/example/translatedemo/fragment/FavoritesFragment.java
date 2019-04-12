package com.example.translatedemo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.translatedemo.R;
import com.example.translatedemo.adpter.FavoritesAdapter;
import com.example.translatedemo.database.DataBaseSqliteCreate;
import com.example.translatedemo.model.FavoritesModel;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {
    //    public class FavoritesFragment extends Fragment implements OnDataListionler {
    private View view;
    private RecyclerView rccDistlay;
    private ArrayList<FavoritesModel> arrFavorites;
    private FavoritesAdapter adapter;
    private DataBaseSqliteCreate databaseSqlite;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favorites, container, false);
        initView();
        databaseSqlite = new DataBaseSqliteCreate(getContext());
        arrFavorites = databaseSqlite.selecterFavorites();
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
//        databaseSqlite = new DataBaseSqliteCreate(getContext());
//        arrHistoryModel = databaseSqlite.selecterFavorites();

        addRccFavorites();
    }

    private void addRccFavorites() {
        adapter = new FavoritesAdapter(arrFavorites, getContext());
        adapter.setHasStableIds(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rccDistlay.setLayoutManager(layoutManager);
        rccDistlay.setNestedScrollingEnabled(false);
        rccDistlay.setAdapter(adapter);
    }

    private void initView() {
        rccDistlay = view.findViewById(R.id.rccFavorites);
    }

//    @Override
//    public void arrFavorit(ArrayList<FavoritesModel> favoritesArr) {
//        arrFavorites=favoritesArr;
//        addRccFacorites();
//    }
}
