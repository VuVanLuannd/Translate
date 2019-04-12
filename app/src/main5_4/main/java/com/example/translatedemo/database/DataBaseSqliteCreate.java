package com.example.translatedemo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.translatedemo.model.FavoritesModel;
import com.example.translatedemo.model.HistoryModel;
import com.example.translatedemo.model.SearchModel;

import java.util.ArrayList;

public class DataBaseSqliteCreate extends SQLiteOpenHelper {
    static final String dataName = "QLCT";
    private final Context context;

    public static final String id = "id";
    public static final String word = "word";
    public static final String denition = "denition";
    public static final String tableWord = "TableWord";
    public static final String checked = "check";
    public static final String tableWordFacorites = "tableWordFacorites";

    public SQLiteDatabase database;

    public DataBaseSqliteCreate(Context context) {
        super(context, dataName, null, 1);
        this.context = context;
        Log.d("data", "DatabaseSqlite");
    }

    public static String CreateTable = "create table "
            + tableWord + "(" + id + " integer primary key autoincrement, "
            + word + " string, "
            + denition + " boolean);";

    public static String CreateTableFavorites = "create table "
            + tableWordFacorites + "(" + id + " integer primary key autoincrement, "
            + word + " string, "
            + denition + " string,"
            + checked + "string);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreateTable);
        db.execSQL(CreateTableFavorites);
        Log.d("data", "conCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableWord);
        Log.d("data", "onDelete");
        db.execSQL("DROP TABLE IF EXISTS " + tableWordFacorites);
        onCreate(db);
    }

    public void inSert(SearchModel object) {
        database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(id, object.getId());
        contentValues.put(word, object.getWord());
        contentValues.put(denition, object.getDifinition());
        database.insert(tableWord, null, contentValues);
        Log.d("succes", "add cussecc");
    }


    public ArrayList<HistoryModel> selecterHistory() {
        database = this.getWritableDatabase();
        ArrayList<HistoryModel> array = new ArrayList<>();
        String sql = "select * from " + DataBaseSqliteCreate.tableWord;
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String definition = cursor.getString(cursor.getColumnIndex("denition"));
            String word = cursor.getString(cursor.getColumnIndex("word"));
            array.add(new HistoryModel(word, definition, id));
            cursor.moveToNext();
        }
        return array;
    }

    public boolean Deleted(int id) {
        database = this.getWritableDatabase();
//      C1
//        int check =database.delete(tableWord, "id" +"=?",new String[]{String.valueOf(id)});;

        int check = database.delete(tableWord, "id" + " = " + id, null);
        if (check > 0) {
            return true;
        }
        return false;
    }

    public void insertFavorites(FavoritesModel object) {
        database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(id, object.getId());
        contentValues.put(word, object.getWord());
        contentValues.put(denition, object.getDifinition());
        database.insert(tableWordFacorites, null, contentValues);
//        Log.d("succesf", "add cussecc");
    }
    public ArrayList<FavoritesModel> selecterFavorites() {
        database = this.getWritableDatabase();
        ArrayList<FavoritesModel> array = new ArrayList<>();
        String sql = "select * from " + DataBaseSqliteCreate.tableWordFacorites;
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String definition = cursor.getString(cursor.getColumnIndex("denition"));
            String word = cursor.getString(cursor.getColumnIndex("word"));
            array.add(new FavoritesModel(word, definition, id));
            cursor.moveToNext();
        }
//        Log.d("succesf", );
        return array;
    }

    public Boolean DeleteFavorites(int id){
        database=this.getWritableDatabase();
        int check=database.delete(tableWordFacorites,"id"+"="+id,null);
        if(check>0){
            return true;
        }
        return false;
    }

}
