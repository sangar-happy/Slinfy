package com.example.challenge1.activities;

import android.os.Bundle;

import com.example.challenge1.R;
import com.example.challenge1.adapters.DashboardViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;


public class DashboardActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        toolbar = (Toolbar) findViewById(R.id.dashboardToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = (TabLayout) findViewById(R.id.dashboardTabs);
        viewPager = (ViewPager) findViewById(R.id.dashboardViewPager);

        DashboardViewPagerAdapter dashboardViewPagerAdapter = new DashboardViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(dashboardViewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }
}