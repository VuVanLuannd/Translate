package com.example.translatedemo.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.translatedemo.adpter.NavigationInfromtionAdapter;
import com.example.translatedemo.model.InForMationModel;
import com.example.translatedemo.R;
import com.example.translatedemo.utils.AddListNaviGation;
import com.example.translatedemo.utils.Utils;

import java.util.ArrayList;

import static com.example.translatedemo.ui.MainActivity.SYSTEM_ALERT_WINDOW_PERMISSION;

public class NavigationInformationActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView rccInfor;
    private TextView mTitle;
    private Toolbar mToolbar;

    private NavigationView navigationView;
    private ActionBarDrawerToggle ActionBar;
    private DrawerLayout mDrawerLayout;
    private FrameLayout frameLayout;

    private String name="";
    private String Url;
    private NavigationInfromtionAdapter adapter;
    private int menuid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_information_activity);
        setTitle("");
        Intent intent = getIntent();
        name = intent.getStringExtra("Name");
        initView();
        navigation();
        getInten();
    }
    private void setTextColorForMenuItem(MenuItem menuItem, @ColorRes int color) {
        SpannableString spanString = new SpannableString(menuItem.getTitle().toString());
        spanString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, color)), 0, spanString.length(), 0);
        menuItem.setTitle(spanString);
    }
    private void resetAllMenuItemsTextColor(NavigationView navigationView) {
        for (int i = 0; i < navigationView.getMenu().size(); i++)
            setTextColorForMenuItem(navigationView.getMenu().getItem(i), R.color.colorGreen);
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
            startService(new Intent(NavigationInformationActivity.this, FloatViewServiceActivity.class));
        } else if (Settings.canDrawOverlays(NavigationInformationActivity.this)) {
            startService(new Intent(NavigationInformationActivity.this, FloatViewServiceActivity.class));
        } else {
            RuntimePermissionForUser();
            Toast.makeText(NavigationInformationActivity.this, "System Alert Window Permission Is Required For Floating Widget.", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawerlayoutInFo);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private void navigation() {
        setSupportActionBar(mToolbar);
        ActionBar = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(ActionBar);
        frameLayout = findViewById(R.id.frame_containerInFo);
        ActionBar.syncState();

        navigationView = findViewById(R.id.nav_viewInFo);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void getInten() {

        switch (name){
            case "Nouns":
                addRecyclerview(AddListNaviGation.addNous());
                mTitle.setText("Nouns");
                break;
            case "Article":
                addRecyclerview(AddListNaviGation.addArticle());
                mTitle.setText("Adiective");
                break;
            case "Pronoun":
                addRecyclerview(AddListNaviGation.addPronoun());
                mTitle.setText("Pronoun");
                break;
            case "Numneral":
                addRecyclerview(AddListNaviGation.addNumberal());
                mTitle.setText("Numneral");
                break;
            case "Adiective":
                addRecyclerview(AddListNaviGation.addAdjective());
                mTitle.setText("Adiective");
                break;
            case "Adverb":
                addRecyclerview(AddListNaviGation.addAdverb());
                mTitle.setText("Adverb");
                break;
            case "Verb":
                addRecyclerview(AddListNaviGation.addVerb());
                mTitle.setText("Verb");
                break;
            case "Conjunction":
                addRecyclerview(AddListNaviGation.addConjunction());
                mTitle.setText("Conjunction");
                break;
            case "Particles":
                addRecyclerview(AddListNaviGation.addParticles());
                mTitle.setText("Particles");
                break;
            case "Interjection":
                addRecyclerview(AddListNaviGation.addInterJection());
                mTitle.setText("Interjection");
                break;
            case "PhraseBook":
                addRecyclerview(AddListNaviGation.addBook1());
                mTitle.setText("PhraseBook1");
                break;
            case "PhraseBook1":
                addRecyclerview(AddListNaviGation.addBook2());
                mTitle.setText("PhraseBook2");
                break;
            case "Prepostion":
                addRecyclerview(AddListNaviGation.addPrepostion());
                mTitle.setText("Prepostion");
                break;
        }
    }

    private void addRecyclerview(final ArrayList<InForMationModel> Name) {
        adapter = new NavigationInfromtionAdapter(Name, NavigationInformationActivity.this, new NavigationInfromtionAdapter.setOnClickListener() {
            @Override
            public void onclik(int position) {
                Url=Name.get(position).getLink();
                Intent intent=new Intent(NavigationInformationActivity.this, NavigationInfWebviewActivity.class);
                intent.putExtra("URL",Url);
                startActivity(intent);
//                Log.d("tex",Url+"");
            }
        });
        adapter.setHasStableIds(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(NavigationInformationActivity.this, LinearLayoutManager.VERTICAL, false);
        rccInfor.setLayoutManager(layoutManager);
        rccInfor.setNestedScrollingEnabled(false);

        rccInfor.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgBackNavi:
                finish();

                //onBackPressed();
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        resetAllMenuItemsTextColor(navigationView);
        setTextColorForMenuItem(menuItem, R.color.colorGreen);
        switch (menuItem.getItemId()){
            case R.id.itemHome:
                Intent intent=new Intent(new Intent(NavigationInformationActivity.this,MainActivity.class));
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                break;
            case R.id.itemNouns:
                addRecyclerview(AddListNaviGation.addNous());
                mTitle.setText("Nouns");
                break;
            case R.id.itemPronoun:
                addRecyclerview(AddListNaviGation.addPronoun());
                mTitle.setText("Nouns");
                break;
            case R.id.itemNumvernal:
                addRecyclerview(AddListNaviGation.addNumberal());
                mTitle.setText("Nouns");
                break;
            case R.id.itemAdjective:
                addRecyclerview(AddListNaviGation.addAdjective());
                mTitle.setText("Nouns");
                break;
            case R.id.itemAdverb:
                addRecyclerview(AddListNaviGation.addVerb());
                mTitle.setText("Adverb");
                break;
            case R.id.itemVerd:
                addRecyclerview(AddListNaviGation.addVerb());
                mTitle.setText("Verd");
                break;
            case R.id.itemPrepostion:
                addRecyclerview(AddListNaviGation.addPrepostion());
                mTitle.setText("Prepostion");
                break;
            case R.id.itemConjunction:
                addRecyclerview(AddListNaviGation.addConjunction());
                mTitle.setText("Conjunction");
                break;
            case R.id.itemParticles:
                addRecyclerview(AddListNaviGation.addParticles());
                mTitle.setText("Particles");
                break;
            case R.id.itemInterjection:
                addRecyclerview(AddListNaviGation.addInterJection());
                mTitle.setText("Interjection");
                break;
            case R.id.itemBook1:
                addRecyclerview(AddListNaviGation.addBook1());
                mTitle.setText("PhraseBook1");
                break;
            case R.id.itemBook2:
                addRecyclerview(AddListNaviGation.addBook2());
                mTitle.setText("PhraseBook2");
                break;
            case R.id.itemFast:
                mTitle.setText("Fast");
                FloatViewServeice();
                FloatViewServeiceClick();
                break;
            case R.id.itemShare:
                mTitle.setText("Share");
                Utils.Share(this);
                break;
            case R.id.itemFeedBack:
                mTitle.setText("FeedBack");
                Utils.FeedBack(this);
                break;
            case R.id.itemRate:
                mTitle.setText("Rate");
                Utils.Rate(this);
                break;
        }
        DrawerLayout drawer =  findViewById(R.id.drawerlayoutInFo);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void initView() {
        mDrawerLayout = findViewById(R.id.drawerlayoutInFo);
        mToolbar = findViewById(R.id.tbHomeActivity);
        mTitle=findViewById(R.id.txtTitle);
        rccInfor =  findViewById(R.id.rcInfromtion);
        mTitle= findViewById(R.id.txtTitle);
    }
}
