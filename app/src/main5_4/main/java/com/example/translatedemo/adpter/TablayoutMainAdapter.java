package com.example.translatedemo.adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TablayoutMainAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragment =new ArrayList<>();
    private List<String> mTitle =new ArrayList<>();
    public TablayoutMainAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragmentVIew(Fragment fragment, String title){
        mFragment.add(fragment);
        mTitle.add(title);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragment.get(i);
    }

    @Override
    public int getCount() {
        return mTitle.size();
    }
}
