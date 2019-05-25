package com.example.challenge1.ui.adapter;

import com.example.challenge1.ui.fragment.AdminAuthFragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class DashboardViewPagerAdapter extends FragmentPagerAdapter {

    private String[] pageTitle = {"Past Internships", "Present Internships"};

    public DashboardViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new AdminAuthFragment();
    }

    @Override
    public int getCount() {
        return pageTitle.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitle[position];
    }
}
