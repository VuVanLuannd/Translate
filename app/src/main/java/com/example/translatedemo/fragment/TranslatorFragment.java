package com.example.translatedemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.translatedemo.R;
import com.example.translatedemo.database.MySqlQuery;
import com.example.translatedemo.utils.Utils;

import java.util.Locale;

public class TranslatorFragment extends Fragment implements TextToSpeech.OnInitListener, View.OnClickListener {

    private ImageView mImgLeft, mImgRight, mImgChange, mImgSpeak;
    private TextView mTxtLeft, mTxtRight, mTxtDistPlay;
    private EditText edInput;
    private View view;
    private MySqlQuery sqlQuery;
    private TextToSpeech mTextToSpeech;
    private int MY_CHECK_DATA = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_translator, container, false);
        initView();
        sqlQuery=new MySqlQuery(getContext());
        Utils.textToSpeak(getContext(),mTxtLeft, edInput, mImgSpeak,mTxtDistPlay,sqlQuery);
        return view;
    }

    View.OnClickListener onclik = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.imgchang:
                    Utils.changeLenguage(mTxtLeft, mTxtRight, mImgLeft, mImgRight,mTextToSpeech);
                    break;
            }

        }
    };

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == MY_CHECK_DATA){
            if(resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS){
                mTextToSpeech = new TextToSpeech(getContext(),this);
            }
            else
            {
                Intent m_installTTSIntent = new Intent();
                m_installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(m_installTTSIntent);
            }
        }
    }
    private void initView() {
        Intent m_checkTTSIntent = new Intent();
        m_checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(m_checkTTSIntent, MY_CHECK_DATA);

        mImgLeft = view.findViewById(R.id.imgimgLeft);
        mImgRight = view.findViewById(R.id.imgRight);
        mImgChange = view.findViewById(R.id.imgchang);
        mImgChange.setOnClickListener(onclik);
        mImgSpeak = view.findViewById(R.id.imgSpeak);
        mTxtLeft = view.findViewById(R.id.txtLeft);
        mTxtRight = view.findViewById(R.id.txtRight);
        edInput = view.findViewById(R.id.edInputTranslator);
        mTxtDistPlay = view.findViewById(R.id.txtTranslator);

        mImgSpeak.setOnClickListener(this);
    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS)
        {
            mTextToSpeech.setLanguage(Locale.US);
        }
        else if(status == TextToSpeech.ERROR)
        {
            Log.e("TTS INIT", "TTS ERROR");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgSpeak:
                mTextToSpeech.speak(edInput.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                    Log.d("Text",edInput.getText().toString()+"");
                    break;


        }
    }
}
