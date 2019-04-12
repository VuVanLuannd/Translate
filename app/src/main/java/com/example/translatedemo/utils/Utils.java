package com.example.translatedemo.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.translatedemo.R;
import com.example.translatedemo.database.SqlQuery;
import com.example.translatedemo.model.SearchModel;

import java.util.List;
import java.util.Locale;


public class Utils {
    public static void showKeyBoard(EditText edInPut, Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(edInPut, InputMethodManager.SHOW_IMPLICIT);
    }

    public static void textToSpeak(final Context context, final TextView txtLeft, final EditText editTextInput, final ImageView imageViewSpeak, final TextView textView,final SqlQuery sqlQuery) {
        editTextInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (TextUtils.isEmpty(s)) {
                    imageViewSpeak.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    if (txtLeft.getText().toString().equals("English")) {
                        List<SearchModel> arrListWord = sqlQuery.Selecter(s + "");

                        if (arrListWord.size() > 0) {
                            textView.setTextColor(Color.BLACK);
                            textView.setText(arrListWord.get(0).getDifinition());
                        }
                    } else {
                        List<SearchModel> arrListDef = sqlQuery.SelecterDefinition(s + "");

                        if (arrListDef.size() > 0) {
                            textView.setTextColor(Color.BLACK);
                            textView.setText(arrListDef.get(0).getWord());
                        }
                    }
                    imageViewSpeak.setImageDrawable(imageViewSpeak.getResources().getDrawable(R.drawable.icon_speak));

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

    public static void changeLenguage(TextView txtLeft, TextView txtRight, ImageView imgLeft, ImageView imgRight, final TextToSpeech textToSpeech) {
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
        }
    }

    public static void FeedBack(Context context) {
        Intent Email = new Intent(Intent.ACTION_SEND);
        Email.setType("text/email");
        Email.putExtra(Intent.EXTRA_EMAIL, new String[]{"vanluannd95@gmail.com"});
        Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
        Email.putExtra(Intent.EXTRA_TEXT, "Dear ...," + "");
        context.startActivity(Intent.createChooser(Email, context.getResources().getString(R.string.StringFeedback)));
    }

    public static void Share(Context context) {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        share.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post");
        share.putExtra(Intent.EXTRA_TEXT, "http://www.codeofaninja.com");

        context.startActivity(Intent.createChooser(share, context.getResources().getString(R.string.TiteleShare)));
    }

    public static void Rate(Context context) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.movinapp.dict.enru.free"));
        context.startActivity(intent);
    }
}
