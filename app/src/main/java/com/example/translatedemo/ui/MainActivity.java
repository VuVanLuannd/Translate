package com.example.translatedemo.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.translatedemo.R;
import com.example.translatedemo.database.DataBaseSqliteCopy;
import com.example.translatedemo.fragment.FavoritesFragment;
import com.example.translatedemo.fragment.HistoryFragment;
import com.example.translatedemo.fragment.SearchFragment;
import com.example.translatedemo.fragment.TranslatorFragment;
import com.example.translatedemo.fragment.WebFragment;
import com.example.translatedemo.utils.Utils;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private Fragment fragment;

    private FrameLayout frameLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle ActionBar;
    private DrawerLayout mDrawerLayout;

    private Intent intent;
    private DataBaseSqliteCopy myDatabase;

    public static final int SYSTEM_ALERT_WINDOW_PERMISSION = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        navigation();
        myDatabase = new DataBaseSqliteCopy(this);
        myDatabase.createDB();
        fragment = new SearchFragment();
        setTitle("");
        loadFragment(fragment);

    }

    private void navigation() {
        setSupportActionBar(mToolbar);
        ActionBar = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(ActionBar);
        frameLayout = findViewById(R.id.frame_container);
        ActionBar.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private  void FloatViewServeice() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            RuntimePermissionForUser();
        }
    }

    public void RuntimePermissionForUser() {

        Intent PermissionIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + getPackageName()));

        startActivityForResult(PermissionIntent, SYSTEM_ALERT_WINDOW_PERMISSION);
    }

    private void FloatViewServeiceClick() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            startService(new Intent(MainActivity.this, FloatViewServiceActivity.class));
        } else if (Settings.canDrawOverlays(MainActivity.this)) {
            startService(new Intent(MainActivity.this, FloatViewServiceActivity.class));
        } else {
            RuntimePermissionForUser();
            Toast.makeText(MainActivity.this, "System Alert Window Permission Is Required For Floating Widget.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.itemNouns:
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("Name", "Nouns");
                startActivityForResult(intent, 9);
                break;
            case R.id.itemPronoun:
                intent.putExtra("Name", "Pronoun");
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 10);
                break;
            case R.id.itemNumvernal:
                intent.putExtra("Name", "Numneral");
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 11);
                break;
            case R.id.itemAdjective:
                intent.putExtra("Name", "Adiective");
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 12);
                break;
            case R.id.itemAdverb:
                intent.putExtra("Name", "Adverb");
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 13);
                break;
            case R.id.itemVerd:
                intent.putExtra("Name", "Verb");
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 14);
                break;
            case R.id.itemPrepostion:
                intent.putExtra("Name", "Prepostion");
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 20);
                break;
            case R.id.itemConjunction:
                intent.putExtra("Name", "Conjunction");
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 15);
                break;
            case R.id.itemParticles:
                intent.putExtra("Name", "Particles");
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 16);
                break;
            case R.id.itemInterjection:
                intent.putExtra("Name", "Interjection");
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 17);
                break;
            case R.id.itemBook1:
                intent.putExtra("Name", "PhraseBook");
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 18);
                break;
            case R.id.itemBook2:
                intent.putExtra("Name", "PhraseBook1");
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 19);
                break;
            case R.id.itemFast:
                FloatViewServeice();
                FloatViewServeiceClick();
                break;
            case R.id.itemShare:
                Utils.Share(this);
                break;
            case R.id.itemFeedBack:
                Utils.FeedBack(this);
                break;
            case R.id.itemRate:
                Utils.Rate(this);
                break;


        }

        DrawerLayout drawer =  findViewById(R.id.drawerlayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawerlayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_shop:
                    fragment = new SearchFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_gifts:
                    fragment = new HistoryFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_cart:
                    fragment = new FavoritesFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_profile:
                    fragment = new TranslatorFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_web:
                    fragment = new WebFragment();
                    loadFragment(fragment);
                    return true;
            }
            return true;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void initView() {
        intent = new Intent(MainActivity.this, NavigationInformationActivity.class);

        BottomNavigationView navigation = findViewById(R.id.navigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mDrawerLayout = findViewById(R.id.drawerlayout);
        mToolbar = findViewById(R.id.tbHomeActivity);

    }

}
