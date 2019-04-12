package com.example.translatedemo.ui;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.translatedemo.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class NavigationInfWebviewActivity extends AppCompatActivity {
    private WebView webView;
    private ImageView mimgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_inf_webview);
        intview();
        Intent intent=getIntent();
        String url=intent.getStringExtra("URL");
        GoUrl(url);
    }
    private void GoUrl(String url){
        AssetManager mgr = getBaseContext().getAssets();
        try {
            InputStream in = mgr.open(url, AssetManager.ACCESS_BUFFER);
            String htmlContentInStringFormat = StreamToString(in);
            in.close();
            webView.loadDataWithBaseURL(null, htmlContentInStringFormat, "text/html", "utf-8", null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String StreamToString(InputStream in) throws IOException {
        if(in == null) {
            return "";
        }
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
        }
        return writer.toString();
    }

    View.OnClickListener onclik=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imgBack:
                    finish();
//                    startActivity(new Intent(NavigationInfWebviewActivity.this,NavigationInformationActivity.class));
//                    Log.d("textd","sdf");
            }
        }
    };
    private void intview() {
        webView=findViewById(R.id.wbDistplay);
        mimgBack=findViewById(R.id.imgBack);
        mimgBack.setOnClickListener(onclik);
    }
}
