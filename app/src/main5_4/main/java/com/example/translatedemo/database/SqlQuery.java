package com.example.translatedemo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.translatedemo.model.SearchModel;

import java.util.ArrayList;

public class SqlQuery {

    private SQLiteDatabase database;
    private DataBaseSqliteCopy copyDataBaseSqlite;
//    public SqlQuery(){}
    public SqlQuery(Context context){
        copyDataBaseSqlite =new DataBaseSqliteCopy(context);
        database = copyDataBaseSqlite.openDB();
    }

    public ArrayList<SearchModel> Selecter(String key){
            ArrayList<SearchModel> array= new ArrayList<>();
            String sql= "select * from words where word like" + "'" + key + "%'";
            Cursor cursor= database.rawQuery(sql,null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                int id=cursor.getInt(cursor.getColumnIndex("_id"));
                String definition=cursor.getString(cursor.getColumnIndex("definition"));
                String word=cursor.getString(cursor.getColumnIndex("word"));
                array.add(new SearchModel(word,definition,id));
                cursor.moveToNext();
            }
            return array;
    }
    public ArrayList<SearchModel> SelecterDefinition(String key){
        ArrayList<SearchModel> array= new ArrayList<>();
        String sql= "select * from words where definition like" + "'" + key + "%'";
        Cursor cursor= database.rawQuery(sql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id=cursor.getInt(cursor.getColumnIndex("_id"));
            String definition=cursor.getString(cursor.getColumnIndex("definition"));
            String word=cursor.getString(cursor.getColumnIndex("word"));
            array.add(new SearchModel(word,definition,id));
            cursor.moveToNext();
        }
        return array;
    }
}
