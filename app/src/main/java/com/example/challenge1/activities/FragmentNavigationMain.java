package com.example.challenge1.activities;

import android.os.Bundle;

import com.example.challenge1.fragments.EventsFragment;
import com.example.challenge1.fragments.MasterFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.view.MenuItem;

import com.example.challenge1.R;

public class FragmentNavigationMain extends AppCompatActivity implements MasterFragment.Callbacks {

    private static final String TAG_MASTER_FRAGMENT = "TAG_MASTER_FRAGMENT";
    private static final String TAG_DETAIL_FRAGMENT = "TAG_DETAIL_FRAGMENT";
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_navigation_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // setup drawer view
        drawerLayout = findViewById(R.id.drawer_layout);
        if (drawerLayout != null) {
            NavigationView navigationView = findViewById(R.id.master_fragment_container);
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    return true;
                }
            });

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            toggle.syncState();
            // setup menu icon
//            final ActionBar actionBar = getSupportActionBar();
//            if (actionBar != null) {
//                actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
//                actionBar.setDisplayHomeAsUpEnabled(true);
//            }
        }

        // insert event fragment into detail container
        EventsFragment eventsFragment = EventsFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.frame_layout, eventsFragment, TAG_DETAIL_FRAGMENT)
                .commit();

        // insert master fragment into master container (i.e. nav view)
        MasterFragment masterFragment = new MasterFragment();
        fragmentManager.beginTransaction()
                .add(R.id.master_fragment_container, masterFragment, TAG_MASTER_FRAGMENT)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMasterItemClicked(int masterItemId) {
        EventsFragment eventsFragment = (EventsFragment) getSupportFragmentManager()
                .findFragmentByTag(TAG_DETAIL_FRAGMENT);
        eventsFragment.onMasterItemClicked(masterItemId);

        // Close the navigation drawer
        if (drawerLayout != null) {
            drawerLayout.closeDrawers();
        }
    }
}
