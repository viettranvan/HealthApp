package com.example.health.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.example.health.R;
import com.example.health.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private static AHBottomNavigation ahBottomNavigation;
    private AHBottomNavigationViewPager ahBottomNavigationViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        setUpViewPager();
    }

    private void anhXa() {
        ahBottomNavigation = findViewById(R.id.AHBottomNavigation);
        ahBottomNavigationViewPager = findViewById(R.id.AHBottomNavigationViewPager);
    }

    private void setUpViewPager() {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        ahBottomNavigationViewPager.setAdapter(adapter);
        ahBottomNavigationViewPager.setPagingEnabled(true);

        // Create items
        AHBottomNavigationItem tab_repmax = new AHBottomNavigationItem(R.string.tab_repmax, R.drawable.icon_repmax, R.color.tab_home);
        AHBottomNavigationItem tab_bmi = new AHBottomNavigationItem(R.string.tab_bmi, R.drawable.icon_bmi, R.color.tab_product);
        AHBottomNavigationItem tab_tdee = new AHBottomNavigationItem(R.string.tab_tdee, R.drawable.icon_tdee, R.color.tab_product);

        // Add items
        ahBottomNavigation.addItem(tab_repmax);
        ahBottomNavigation.addItem(tab_bmi);
        ahBottomNavigation.addItem(tab_tdee);


        ahBottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                ahBottomNavigationViewPager.setCurrentItem(position);
                return true;
            }
        });

        // chuyen fragment bang cach vuot
        ahBottomNavigationViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                ahBottomNavigation.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}