package com.iayon.retrofit20tutorial;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by ayush on 8/1/16.
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    int mNumberOfTabs;

    public MainPagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.mNumberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new FirstFragment();
            case 2:
                return new SecondFragment();
            default:
                return new SecondFragment();
        }

    }

    @Override
    public int getCount() {
        return mNumberOfTabs;
    }
}
