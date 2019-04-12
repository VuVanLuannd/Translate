package com.example.translatedemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.translatedemo.adpter.NavigationInfromtionAdapter;
import com.example.translatedemo.adpter.NavigtionAdpter;
import com.example.translatedemo.model.InForMationModel;
import com.example.translatedemo.R;
import com.example.translatedemo.utils.AddListNaviGation;

import java.util.ArrayList;

public class NavigationInformation extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView rccInfor;
    private TextView mTitle;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigtionAdpter mNavigtion_custom;
    private LinearLayout lnHome, lnNousn, lnPronoun, lnNumberal, lnAdiective, lnPhraseBook1, lnAverb, lnVerb, lnPhraseBook, lnPreposition, lnConjunction, lninterjection, lnParticles;

    private String name="", namePron, nameNum, nameAdiec, nameAdverb, nameVerb, namePreposition, nameConjunc, namePart, nameInter, nameBook1, nameBook2;
    private String Url;
    private NavigationInfromtionAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_information);
        Intent intent = getIntent();
//        if(savedInstanceState!=null){
//            name=savedInstanceState.getString("Name");
//        }
        name = intent.getStringExtra("Name");
        initializeMenu();
        initView();
        getInten();


    }

    private void initializeMenu() {
        mNavigtion_custom = (NavigtionAdpter) getSupportFragmentManager().findFragmentById(R.id.drawer_fragment);
    }

    private void getInten() {

        switch (name){
            case "Nouns":
                addRecyclerview(AddListNaviGation.addNous());
                mTitle.setText("Nouns");
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
        adapter = new NavigationInfromtionAdapter(Name, NavigationInformation.this, new NavigationInfromtionAdapter.setOnClickListener() {
            @Override
            public void onclik(int position) {
                Url=Name.get(position).getLink();
                Intent intent=new Intent(NavigationInformation.this,NavigationInfWebview.class);
                intent.putExtra("URL",Url);
                startActivity(intent);
//                Log.d("tex",Url+"");
            }
        });
        adapter.setHasStableIds(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(NavigationInformation.this, LinearLayoutManager.VERTICAL, false);
        rccInfor.setLayoutManager(layoutManager);
        rccInfor.setNestedScrollingEnabled(false);

        rccInfor.setAdapter(adapter);
    }

    private void initView() {
        rccInfor = (RecyclerView) findViewById(R.id.rcInfromtion);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mToolbar = (Toolbar) findViewById(R.id.tbHome);
        mTitle=(TextView) findViewById(R.id.txtTitle);
    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putString("Name", name);
//
//    }

    @Override
    public void onClick(View v) {

    }
}
