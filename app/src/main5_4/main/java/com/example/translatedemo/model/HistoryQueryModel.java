package com.example.translatedemo.model;

import android.content.Context;

import com.example.translatedemo.database.DataBaseSqliteCreate;

public class HistoryQueryModel {
    private DataBaseSqliteCreate dataBaseSqliteCreate;

    public HistoryQueryModel(Context context) {
        dataBaseSqliteCreate = new DataBaseSqliteCreate(context);

    }



}
