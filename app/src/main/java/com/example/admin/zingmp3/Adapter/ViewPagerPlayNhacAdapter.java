package com.example.admin.zingmp3.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Admin on 7/10/2018.
 */

public class ViewPagerPlayNhacAdapter extends FragmentPagerAdapter {
    public ArrayList<Fragment> fragmentArrayList = new ArrayList<Fragment>();
    public ViewPagerPlayNhacAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }
    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }
    public void addFragment(Fragment fragment){
        fragmentArrayList.add(fragment);
    }

}
