package com.example.translatedemo.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.translatedemo.R;
import com.example.translatedemo.adpter.HistoryAdapter;
import com.example.translatedemo.database.DataBaseSqliteCreate;
import com.example.translatedemo.model.HistoryModel;
import com.example.translatedemo.utils.IUpdateListView;

import java.util.ArrayList;

public class HistoryFragment extends Fragment implements IUpdateListView {
    private View view;
    private RecyclerView mrccView;
    private HistoryAdapter adapter;
    private ArrayList<HistoryModel> arrHistoryModel;
    private DataBaseSqliteCreate databaseSqlite;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_history, container, false);
        initView();
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        databaseSqlite = new DataBaseSqliteCreate(getContext());
        arrHistoryModel = databaseSqlite.selecterHistory();
        DisplayView();
    }

    private void DisplayView() {

        adapter = new HistoryAdapter(arrHistoryModel, getContext(), this, new HistoryAdapter.setOnClickListener() {
            @Override
            public void onclik(int position) {
                Intent intent=new Intent(getContext(), InfomationFragment.class);
                intent.putExtra("word",arrHistoryModel.get(position).getWord());
                intent.putExtra("denition",arrHistoryModel.get(position).getDifinition());
                startActivity(intent);
            }
        });
        adapter.setHasStableIds(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mrccView.setLayoutManager(layoutManager);
        mrccView.setNestedScrollingEnabled(false);
        mrccView.setAdapter(adapter);
    }

    private void initView() {
        mrccView = (RecyclerView) view.findViewById(R.id.rccHistory);
    }

    @Override
    public void update(ArrayList<HistoryModel> HistoryModelList) {
        arrHistoryModel = HistoryModelList;

        DisplayView();
    }
}
