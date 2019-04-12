package com.example.translatedemo.utils;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.translatedemo.R;
import com.example.translatedemo.database.SqlQuery;
import com.example.translatedemo.model.SearchModel;

import java.util.ArrayList;
import java.util.Locale;

public class Utils {
    public static void showKeyBoard(EditText edInPut,Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(edInPut, InputMethodManager.SHOW_IMPLICIT);
    }
    public static void textToSpeak(final Context context, final TextView txtLeft, final EditText editTextInput, final ImageView imageViewSpeak, final TextView textView, final TextToSpeech textToSpeech){
        editTextInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(TextUtils.isEmpty(s)){
                    imageViewSpeak.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {
                Log.d("data", "on");
                if (!TextUtils.isEmpty(s)&& txtLeft.getText().equals("English")) {
                    imageViewSpeak.setImageDrawable(imageViewSpeak.getResources().getDrawable(R.drawable.icon_speak));

                    ArrayList<SearchModel> arrList = new SqlQuery(context).Selecter(s+"");
                    if(arrList.size()>0) {

                        for (int i = 0; i < arrList.size(); i++) {
                            textView.setText(arrList.get(i).getDifinition());
                        }
                    }
                }
                if (!TextUtils.isEmpty(s)&&txtLeft.getText().equals("Rusian")) {
                    imageViewSpeak.setImageDrawable(imageViewSpeak.getResources().getDrawable(R.drawable.icon_speak));

                    ArrayList<SearchModel> arrList = new SqlQuery(context).SelecterDefinition(editTextInput.getText().toString());
                    Log.d("texxt",txtLeft.getText().toString());
                    if(arrList.size()>0) {
                        for (int i = 0; i < arrList.size(); i++) {
                            textView.setText(arrList.get(i).getWord());
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    imageViewSpeak.setVisibility(View.INVISIBLE);
                    textView.setText("");
                }
            }
        });
    }
    public static void changeLenguage( TextView txtLeft, TextView txtRight, ImageView imgLeft, ImageView imgRight, final TextToSpeech textToSpeech){
        if (txtLeft.getText().equals("English")) {
            imgLeft.setImageDrawable(imgLeft.getResources().getDrawable(R.drawable.ensign_rusian));
            imgRight.setImageDrawable(imgRight.getResources().getDrawable(R.drawable.ensign_english));
            txtLeft.setText("Rusian");
            txtRight.setText("English");
            textToSpeech.setLanguage(Locale.US);
        } else {
            imgLeft.setImageDrawable(imgLeft.getResources().getDrawable(R.drawable.ensign_english));
            imgRight.setImageDrawable(imgRight.getResources().getDrawable(R.drawable.ensign_rusian));
            txtRight.setText("Rusian");
            txtLeft.setText("English");
            textToSpeech.setLanguage(Locale.JAPAN);
        }
    }
}
