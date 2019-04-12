package com.example.translatedemo.ui;

import android.app.Application;

import java.util.ArrayList;

public class MyApplication extends Application {
    // nó chạy từ khi bắt đầu mở app, đến khi tắt app
    public ArrayList<String> mArrMenu;
    @Override
    public void onCreate() {
        super.onCreate();
        
    }
}
