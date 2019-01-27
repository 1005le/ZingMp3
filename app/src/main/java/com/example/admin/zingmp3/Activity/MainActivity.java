package com.example.admin.zingmp3.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.zingmp3.Adapter.MainViewPagerAdapter;
import com.example.admin.zingmp3.Fragment.TimKiemFragment;
import com.example.admin.zingmp3.Fragment.TrangChuFragment;
import com.example.admin.zingmp3.R;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
   ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        init();
    }
    private void init() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
    mainViewPagerAdapter.addFragment(new TrangChuFragment(), "Trang Chu");
    mainViewPagerAdapter.addFragment(new TimKiemFragment(), "Tim Kiem");
    viewPager.setAdapter(mainViewPagerAdapter);
    tabLayout.setupWithViewPager(viewPager);
    tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
    tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);
    }

    private void anhXa() {
        tabLayout= findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
    }
}
