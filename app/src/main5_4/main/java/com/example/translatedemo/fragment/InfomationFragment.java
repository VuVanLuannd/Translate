package com.example.translatedemo.fragment;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.translatedemo.R;

import java.util.Locale;

public class InfomationFragment extends AppCompatActivity implements View.OnClickListener {
    private TextView mtxtTitle,mtxtWord,mtxtDistplay;
    private ImageView mimgBack,mimgSpeak;
    private TextToSpeech mTextToSpeech;
    private String Temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        initView();
        ListenInten();
    }
    private void ListenInten(){
        Intent intent=getIntent();
        Temp=intent.getStringExtra("word");
        mtxtTitle.setText(intent.getStringExtra("word"));
        mtxtWord.setText(intent.getStringExtra("word"));
        mtxtDistplay.setText(intent.getStringExtra("denition"));
    }
    private void initView() {
        mtxtTitle=findViewById(R.id.txtTitleword);
        mtxtWord=findViewById(R.id.txtword);
        mtxtDistplay=findViewById(R.id.txtDistplay);
        mimgBack=findViewById(R.id.imgBackSearch);
        mimgBack.setOnClickListener(this);
        mimgSpeak=findViewById(R.id.imgInforSpeak);
        mimgSpeak.setOnClickListener(this);
        mTextToSpeech=new TextToSpeech(InfomationFragment.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                mTextToSpeech.setLanguage(Locale.ENGLISH);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgBackSearch:
                finish();
                break;
            case R.id.imgInforSpeak:
                mTextToSpeech.speak(Temp,TextToSpeech.QUEUE_FLUSH,null);
                break;
        }
    }
}
