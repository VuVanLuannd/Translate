package com.example.translatedemo.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.translatedemo.R;
import com.example.translatedemo.adpter.NavigtionAdpter;
import com.example.translatedemo.adpter.TablayoutMainAdapter;
import com.example.translatedemo.database.DataBaseSqliteCopy;
import com.example.translatedemo.fragment.FavoritesFragment;
import com.example.translatedemo.fragment.HistoryFragment;
import com.example.translatedemo.fragment.SearchFragment;
import com.example.translatedemo.fragment.TranslatorFragment;
import com.example.translatedemo.fragment.WebFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mVpMain;
    private TabLayout mTlMain;
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private LinearLayout lnHome, lnNousn, lnPronoun, lnNumberal, lnAdiective, lnPhraseBook1, lnAverb, lnVerb, lnPhraseBook, lnPreposition, lnConjunction, lninterjection, lnParticles, lnFastKey, lnShare, lnFeedback, lnRate;
    private TextView mtxtNouns;
    private ImageView mimgNouns;

    private DrawerLayout drawerlayout;
    private TablayoutMainAdapter mAdrapter;
    private NavigtionAdpter mNavigtion_custom;
    private Intent intent;
    private DataBaseSqliteCopy myDatabase;

    public static final int SYSTEM_ALERT_WINDOW_PERMISSION = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        addTabLayout();
        initializeMenu();
        navigation();
        myDatabase = new DataBaseSqliteCopy(this);
        myDatabase.createDB();
        FloatViewServeice();
    }
    private void FloatViewServeice(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            RuntimePermissionForUser();
        }
    }
    public void RuntimePermissionForUser() {

        Intent PermissionIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + getPackageName()));

        startActivityForResult(PermissionIntent, SYSTEM_ALERT_WINDOW_PERMISSION);
    }

    private void FloatViewServeiceClick(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            startService(new Intent(MainActivity.this, FloatViewServeceActivity.class));
        } else if (Settings.canDrawOverlays(MainActivity.this)) {
            startService(new Intent(MainActivity.this, FloatViewServeceActivity.class));
        } else {
            RuntimePermissionForUser();

            Toast.makeText(MainActivity.this, "System Alert Window Permission Is Required For Floating Widget.", Toast.LENGTH_LONG).show();
        }
    }

    private void navigation() {
        mToolbar.setNavigationIcon(R.drawable.icon_menu);
        mNavigtion_custom.setUp(R.id.drawer_fragment, mDrawerLayout, mToolbar);

    }
    private void initializeMenu() {
        mNavigtion_custom = (NavigtionAdpter) getSupportFragmentManager().findFragmentById(R.id.drawer_fragment);
    }

    private void addTabLayout() {
        mAdrapter = new TablayoutMainAdapter(getSupportFragmentManager());
        mAdrapter.addFragmentVIew(new SearchFragment(), "Search");
        mAdrapter.addFragmentVIew(new HistoryFragment(), "HistoryFragment");
        mAdrapter.addFragmentVIew(new FavoritesFragment(), "FavoritesFragment");
        mAdrapter.addFragmentVIew(new TranslatorFragment(), "TranslatorFragment");
        mAdrapter.addFragmentVIew(new WebFragment(), "WebFragment");
        mVpMain.setAdapter(mAdrapter);
        mTlMain.setupWithViewPager(mVpMain);

        mTlMain.getTabAt(0).setIcon(R.drawable.icon_search).setText("Search");
        mTlMain.getTabAt(1).setIcon(R.drawable.icon_histoty).setText("HistoryFragment");
        mTlMain.getTabAt(2).setIcon(R.drawable.icon_star).setText("FavoritesFragment");
        mTlMain.getTabAt(3).setIcon(R.drawable.icon_translate).setText("TranslatorFragment");
        mTlMain.getTabAt(4).setIcon(R.drawable.icon_web).setText("WebFragment");

        selecteTablayoutIcon(0);
        mTlMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selecteTablayoutIcon(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initView() {
        intent = new Intent(MainActivity.this, NavigationInformation.class);
        mVpMain = findViewById(R.id.vpMain);
        mTlMain = findViewById(R.id.tblMain);
        mDrawerLayout = findViewById(R.id.drawerlayout);
        mToolbar = findViewById(R.id.tbHome);
        mtxtNouns=findViewById(R.id.txtNouns);
        mimgNouns=findViewById(R.id.imgNouns);
        drawerlayout = findViewById(R.id.drawerlayout);
        lnHome = findViewById(R.id.lnHome);
        lnHome.setOnClickListener(this);
        lnNousn = findViewById(R.id.lnNouns);
        lnNousn.setOnClickListener(this);
        lnPronoun = findViewById(R.id.lnPronoun);
        lnPronoun.setOnClickListener(this);
        lnNumberal = findViewById(R.id.lnNumneral);
        lnNumberal.setOnClickListener(this);
        lnAdiective = findViewById(R.id.lnAdiective);
        lnAdiective.setOnClickListener(this);
        lnAverb = findViewById(R.id.lnAdverb);
        lnAverb.setOnClickListener(this);
        lnVerb = findViewById(R.id.lnVerb);
        lnVerb.setOnClickListener(this);
        lnPreposition = findViewById(R.id.lnPrepostion);
        lnPreposition.setOnClickListener(this);
        lnConjunction = findViewById(R.id.lnConjunction);
        lnConjunction.setOnClickListener(this);
        lnParticles = findViewById(R.id.lnParticles);
        lnParticles.setOnClickListener(this);
        lninterjection = findViewById(R.id.lnInterJection);
        lninterjection.setOnClickListener(this);
        lnPhraseBook = findViewById(R.id.lnPhraseBook);
        lnPhraseBook.setOnClickListener(this);
        lnPhraseBook1 = findViewById(R.id.lnPhraseBook1);
        lnPhraseBook1.setOnClickListener(this);
        lnFastKey = findViewById(R.id.lnFast);
        lnFastKey.setOnClickListener(this);
        lnShare = findViewById(R.id.lnShare);
        lnShare.setOnClickListener(this);
        lnFeedback = findViewById(R.id.lnFeedback);
        lnFeedback.setOnClickListener(this);
        lnRate = findViewById(R.id.lnRate);
        lnRate.setOnClickListener(this);
    }
    private void selecteTablayoutIcon(int position) {

        mTlMain.getTabAt(0).setIcon(R.drawable.icon_search);
        mTlMain.getTabAt(1).setIcon(R.drawable.icon_histoty);
        mTlMain.getTabAt(2).setIcon(R.drawable.icon_star);
        mTlMain.getTabAt(3).setIcon(R.drawable.icon_translate);
        mTlMain.getTabAt(4).setIcon(R.drawable.icon_web);
        switch (position) {
            case 0:
                mTlMain.getTabAt(0).setIcon(R.drawable.icon_yellow_search);
                break;
            case 1:
                mTlMain.getTabAt(1).setIcon(R.drawable.icon_yellow_history);
                break;
            case 2:
                mTlMain.getTabAt(2).setIcon(R.drawable.icon_yellow_favorites);
                break;
            case 3:
                mTlMain.getTabAt(3).setIcon(R.drawable.icon_yellow_translate);
                break;
            case 4:
                mTlMain.getTabAt(4).setIcon(R.drawable.icon_yellow_web);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lnHome:
                drawerlayout.closeDrawers();
                break;
            case R.id.lnNouns:
                intent.putExtra("Name", "Nouns");
//                setColor(lnNousn,mtxtNouns,mimgNouns);
                startActivityForResult(intent, 9);
                break;
            case R.id.lnPronoun:
                intent.putExtra("Name", "Pronoun");
                startActivityForResult(intent, 10);
                break;
            case R.id.lnNumneral:
                intent.putExtra("Name", "Numneral");
                startActivityForResult(intent, 11);
                break;
            case R.id.lnAdiective:
                intent.putExtra("Name", "Adiective");
                startActivityForResult(intent, 12);
                break;
            case R.id.lnAdverb:
                intent.putExtra("Name", "Adverb");
                startActivityForResult(intent, 13);
                break;
            case R.id.lnVerb:
                intent.putExtra("Name", "Verb");
                startActivityForResult(intent, 14);
                break;
            case R.id.lnConjunction:
                intent.putExtra("Name", "Conjunction");
                startActivityForResult(intent, 15);
                break;
            case R.id.lnParticles:
                intent.putExtra("Name", "Particles");
                startActivityForResult(intent, 16);
                break;
            case R.id.lnInterJection:
                intent.putExtra("Name", "Interjection");
                startActivityForResult(intent, 17);
                break;
            case R.id.lnPhraseBook:
                intent.putExtra("Name", "PhraseBook");
                startActivityForResult(intent, 18);
                break;
            case R.id.lnPhraseBook1:
                intent.putExtra("Name", "PhraseBook1");
                startActivityForResult(intent, 19);
                break;
            case R.id.lnPrepostion:
                intent.putExtra("Name", "Prepostion");
                startActivityForResult(intent, 20);
                break;
            case R.id.lnFast:
                FloatViewServeiceClick();
                break;
            case R.id.lnShare:
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                share.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post");
                share.putExtra(Intent.EXTRA_TEXT, "http://www.codeofaninja.com");

                startActivity(Intent.createChooser(share, getResources().getString(R.string.TiteleShare)));
                break;
            case R.id.lnFeedback:
                Intent Email = new Intent(Intent.ACTION_SEND);
                Email.setType("text/email");
                Email.putExtra(Intent.EXTRA_EMAIL, new String[]{"vanluannd95@gmail.com"});
                Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                Email.putExtra(Intent.EXTRA_TEXT, "Dear ...," + "");
                startActivity(Intent.createChooser(Email, getResources().getString(R.string.StringFeedback)));
                break;
            case R.id.lnRate:
                intent = new Intent(android.content.Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.movinapp.dict.enru.free"));
                startActivity(intent);
                break;
        }
    }
}

