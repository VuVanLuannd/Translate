package com.example.translatedemo.ui;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.translatedemo.R;
import com.example.translatedemo.database.SqlQuery;
import com.example.translatedemo.model.SearchModel;
import com.example.translatedemo.utils.Utils;

import java.util.List;
import java.util.Locale;

public class FloatViewServeceActivity extends Service implements View.OnClickListener {

    private WindowManager mWindowManager;
    private View view;
    private View mlnIcon;
    private View nlnShowClick;
    private TextToSpeech mTextToSpeech;

    private ImageView mImgSpeak;
    private SqlQuery sqlQuery;
    private ImageView mimgClose;
    private TextView mtxtHome, mtxtLeftFloat, mtxtRightFloat, mTxtDistPlay;
    private WindowManager.LayoutParams params;
    private EditText mEdtInputFloat;
    private ImageView mimgLeftFloat, mingRightFloat, mimgChangFloat, mImgSpeakFloat;

    public FloatViewServeceActivity() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        view = LayoutInflater.from(this).inflate(R.layout.activity_float_view_servece, null);
        initView();
        textToSpeech();
        setLayoutParamterMain();
        touchListenerSmall();
        touchListenNerBig();
        Utils.textToSpeak(this, mtxtLeftFloat, mEdtInputFloat, mImgSpeakFloat, mTxtDistPlay, sqlQuery);
    }

    private void touchListenerSmall() {
        //adding an touchlistener to make drag movement of the floating widget
        view.findViewById(R.id.relativeLayoutParent).setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = params.x;
                        initialY = params.y;
                        //get the touch location
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;

                    case MotionEvent.ACTION_UP:
                        int Xdiff = (int) (event.getRawX() - initialTouchX);
                        int Ydiff = (int) (event.getRawY() - initialTouchY);


                        //The check for Xdiff <10 && YDiff< 10 because sometime elements moves a little while clicking.
                        //So that is click event.
                        if (Xdiff < 10 && Ydiff < 10) {
                            if (isViewCollapsed()) {
                                mlnIcon.setVisibility(View.GONE);
                                nlnShowClick.setVisibility(View.VISIBLE);
                            }
                        }
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        //Calculate the X and Y coordinates of the view.
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);


                        //Update the layout with new X & Y coordinate
                        mWindowManager.updateViewLayout(view, params);
                        return true;
                }
                return false;
            }
        });
    }

    public void touchListenNerBig() {
        view.findViewById(R.id.layoutExpanded).setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = params.x;
                        initialY = params.y;
                        //get the touch location
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;

                    case MotionEvent.ACTION_UP:
                        int Xdiff = (int) (event.getRawX() - initialTouchX);
                        int Ydiff = (int) (event.getRawY() - initialTouchY);

                        if (Xdiff < 10 && Ydiff < 10) {
                            if (isViewCollapsedBig()) {
                            }
                        }
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);
                        mWindowManager.updateViewLayout(view, params);
                        return true;
                }
                return false;
            }
        });
    }

    private boolean isViewCollapsed() {
        return view == null || view.findViewById(R.id.layoutCollapsed).getVisibility() == View.VISIBLE;
    }

    private boolean isViewCollapsedBig() {
        return view == null || view.findViewById(R.id.layoutExpanded).getVisibility() == View.GONE;
    }

    private void setLayoutParamterMain() {
        //setting the layout parameters
        int layoutParams = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParams = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            layoutParams = WindowManager.LayoutParams.TYPE_PHONE;
        }
        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                layoutParams,
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 100;

        //getting windows services and adding the floating view to it
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mWindowManager.addView(view, params);
    }

    private void textToSpeech() {
        mTextToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                mTextToSpeech.setLanguage(Locale.ENGLISH);
            }
        });
    }

    private void initView() {
        mlnIcon = view.findViewById(R.id.layoutCollapsed);
        nlnShowClick = view.findViewById(R.id.layoutExpanded);
        sqlQuery = new SqlQuery(this);

        view.findViewById(R.id.buttonClose).setOnClickListener(this);

        mimgClose = view.findViewById(R.id.imgClose);
        mtxtHome = view.findViewById(R.id.txtHomeFloat);
        mEdtInputFloat = view.findViewById(R.id.edInputTranslatorFloat);
        mingRightFloat = view.findViewById(R.id.imgRightFloat);
        mimgLeftFloat = view.findViewById(R.id.imgLeftFloat);
        mimgChangFloat = view.findViewById(R.id.imgchangFloat);
        mImgSpeakFloat = view.findViewById(R.id.imgSpeakFloat);
        mtxtLeftFloat = view.findViewById(R.id.txtLeftFloat);
        mtxtRightFloat = view.findViewById(R.id.txtRightFloat);
        mTxtDistPlay = view.findViewById(R.id.txtFloatViewTrans);

        mtxtHome.setOnClickListener(this);
        mimgClose.setOnClickListener(this);
        mimgChangFloat.setOnClickListener(this);
        mImgSpeakFloat.setOnClickListener(this);
        nlnShowClick.setOnClickListener(this);
        mEdtInputFloat.setOnClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (view != null) mWindowManager.removeView(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonClose:
                stopSelf();
                break;
            case R.id.imgClose:
                mlnIcon.setVisibility(View.VISIBLE);
                nlnShowClick.setVisibility(View.GONE);
                break;
            case R.id.txtHomeFloat:

                Intent intent = new Intent(FloatViewServeceActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                stopSelf();
                break;
            case R.id.edInputTranslatorFloat:
                mEdtInputFloat.requestFocus();
                break;
            case R.id.imgchangFloat:
                Utils.changeLenguage(mtxtLeftFloat, mtxtRightFloat, mimgLeftFloat, mingRightFloat, mTextToSpeech);
                break;
            case R.id.imgSpeakFloat:
                mTextToSpeech.speak(mEdtInputFloat.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                break;
        }
    }
}
