package com.example.health.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.example.health.R;
import com.example.health.adapter.ViewPagerAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static AHBottomNavigation ahBottomNavigation;
    private AHBottomNavigationViewPager ahBottomNavigationViewPager;
    private DrawerLayout drawerLayout;
    private NavigationView menuNavigationView;
    private Toolbar toolbar;
    private TextView toolBarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        setUpViewPager();
        actionToolBar();
        menuNavigationView.setNavigationItemSelectedListener(this);
    }

    private void anhXa() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolBarTitle = findViewById(R.id.tv_toolbar_title);
        ahBottomNavigation = findViewById(R.id.AHBottomNavigation);
        ahBottomNavigationViewPager = findViewById(R.id.AHBottomNavigationViewPager);
        drawerLayout = findViewById(R.id.drawer_layout);
        menuNavigationView = findViewById(R.id.navigation_view);
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
                setToolbarTitle(position);
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

    // su kien khi item trong drawer menu dc click
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                drawerLayout.closeDrawer(GravityCompat.START);
                ahBottomNavigation.setCurrentItem(0);
                break;
            case R.id.nav_contact:
//                Intent intent = new Intent(MainActivity.this,ContactActivity.class);
//                startActivity(intent);

                startActivity(new Intent(MainActivity.this,ContactActivity.class));
                break;
            case R.id.nav_guide:
                startActivity(new Intent(MainActivity.this, GuideActivity.class));
                break;
        }
        return true;
    }

    private void actionToolBar() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolBarTitle.setText(getString(R.string.tab_repmax)); // trang chủ
        toolbar.setNavigationIcon(R.drawable.ic_action_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void setToolbarTitle(int position) {
        switch (position) {
            case 0:
                setSupportActionBar(toolbar);
                toolBarTitle.setText(getString(R.string.tab_repmax)); // REPMAX
                break;
            case 1:
                setSupportActionBar(toolbar);
                toolBarTitle.setText(getString(R.string.tab_bmi)); // BMI
                break;
            case 2:
                setSupportActionBar(toolbar);
                toolBarTitle.setText(getString(R.string.tab_tdee)); // TDEE

                break;
        }
        toolbar.setNavigationIcon(R.drawable.ic_action_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
}