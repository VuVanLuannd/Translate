package com.example.translatedemo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataBaseSqliteCopy extends SQLiteOpenHelper {

    static String DB_PATH = "/data/data/com.example.translatedemo/databases/";
    static String DB_NAME = "dictionary.sqlite";

    private SQLiteDatabase database;
    private final Context mContext;

    public DataBaseSqliteCopy(Context context) {



        super(context, DB_NAME, null, 1);

        String sPackageName = context.getPackageName();


        this.mContext = context;
        Log.d("data","DatabaseSqlite");
    }

    public void createDB() {
        boolean dbExist = checkDB();
        if (dbExist) {
        } else {
            this.getReadableDatabase();
            try {
                copyDB();
                Log.d("TESTDB","OK");
            } catch (Exception e) {
                throw new Error("Error copying DB");

            }
        }
    }

    private void copyDB() throws IOException {
        InputStream dbInput = mContext.getAssets().open(DB_NAME);
        String outFile = DB_PATH + DB_NAME;
        OutputStream dbOutput = new FileOutputStream(outFile);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = dbInput.read(buffer)) > 0) {
            dbOutput.write(buffer, 0, length);
        }
        dbOutput.flush();
        dbOutput.close();
        dbInput.close();
    }

    private boolean checkDB() {
        SQLiteDatabase check = null;
        try {
            String dbPath = DB_PATH + DB_NAME;
            check = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (Exception e) {
// TODO: handle exception
        }
        if (check != null) {
            check.close();
        }

        return check != null ? true : false;
    }

    public SQLiteDatabase openDB() {
        String dbPath = DB_PATH + DB_NAME;
        database = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
        return database;
    }

    public synchronized void close() {
        if (database != null)
            database.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}

