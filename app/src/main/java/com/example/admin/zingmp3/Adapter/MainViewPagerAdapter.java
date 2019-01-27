package com.example.admin.zingmp3.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Admin on 26/9/2018.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter{
    private ArrayList<Fragment> arrayFragment = new ArrayList<>();
    //moi khi su dung den ViewPager nao thi sse cho no hien thi title tuong tung
   //cua fragment tuong ung
    private ArrayList<String>arrayTitle = new ArrayList<>();
    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        return arrayFragment.get(position);
    }

    @Override
    public int getCount() {
        return arrayFragment.size();
    }
    public void addFragment(Fragment fragment, String title){
        arrayFragment.add(fragment);
        arrayTitle.add(title);
    }
//ten cua moi viewPager hien thi tren tab cua minh
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayTitle.get(position);
    }
}
