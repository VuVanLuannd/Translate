package com.example.translatedemo.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.translatedemo.model.SearchModel;

import java.util.ArrayList;
import java.util.List;

public class SqlQuery {

    private SQLiteDatabase database;
    private DataBaseSqliteCopy copyDataBaseSqlite;

    //    public SqlQuery(){}
    public SqlQuery(Context context) {
        copyDataBaseSqlite = new DataBaseSqliteCopy(context);
        database = copyDataBaseSqlite.openDB();
    }

    public List<SearchModel> Selecter(String key) {
        List<SearchModel> array = new ArrayList<>();
        String sql = "select * from words where word like" + "'" + key + "%'";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            SearchModel searchModel = new SearchModel();
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String definition = cursor.getString(cursor.getColumnIndex("definition"));
            String word = cursor.getString(cursor.getColumnIndex("word"));

            searchModel.setId(id);
            searchModel.setDifinition(definition);
            searchModel.setWord(word);

            array.add(searchModel);

            cursor.moveToNext();
        }
        return array;
    }

    public List<SearchModel> SelecterDefinition(String key) {
        List<SearchModel> array = new ArrayList<>();
        String sql = "select * from words where definition like" + "'%" + key + "%'";
        Cursor cursor = database.rawQuery(sql, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            SearchModel searchModel = new SearchModel();
            String word = cursor.getString(cursor.getColumnIndex("word"));

            searchModel.setId(0);
            searchModel.setDifinition("");
            searchModel.setWord(word);

            array.add(searchModel);
            cursor.moveToNext();
        }
        return array;
    }
}
