package com.example.health.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.health.fragment.BMIFragment;
import com.example.health.fragment.RepMaxFragment;
import com.example.health.fragment.TDEEFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new RepMaxFragment();
            case 1:
                return new BMIFragment();
            case 2:
                return new TDEEFragment();
            default:
                return new RepMaxFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
