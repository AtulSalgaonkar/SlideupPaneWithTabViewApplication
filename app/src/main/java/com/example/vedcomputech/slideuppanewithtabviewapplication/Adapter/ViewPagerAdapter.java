package com.example.vedcomputech.slideuppanewithtabviewapplication.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 01-07-2018.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragments = new ArrayList<>();
    private String tabTitles[] = null;

    public ViewPagerAdapter(FragmentManager fragmentManager, String[] tabTitles) {
        super(fragmentManager);
        this.tabTitles = tabTitles;
    }

    // Our custom method that populates this Adapter with Fragments
    public void addFragments(Fragment fragment) {
        mFragments.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
