package com.example.challenge1.activities;

import android.net.Uri;
import android.os.Bundle;

import com.example.challenge1.fragments.EventsFragment;
import com.example.challenge1.fragments.LoginSignupFragment;
import com.example.challenge1.fragments.MasterFragment;
import com.example.challenge1.fragments.NewEventFragment;
import com.example.challenge1.fragments.RealTimeDatabse;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FragmentNavigationMain extends AppCompatActivity implements
        MasterFragment.Callbacks,
        RealTimeDatabse.OnFragmentInteractionListener,
        EventsFragment.EventsFragmentInteraction {

    private static final String TAG_MASTER_FRAGMENT = "TAG_MASTER_FRAGMENT";
    private static final String TAG_DETAIL_FRAGMENT = "TAG_DETAIL_FRAGMENT";
    private DrawerLayout drawerLayout;
    private MasterFragment masterFragment;

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
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        EventsFragment eventsFragment = new EventsFragment();
        fragmentManager.beginTransaction()
                .add(R.id.frame_layout, eventsFragment)
                .commit();

        masterFragment = new MasterFragment();
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

        masterFragment.updateUi(masterItemId);

        switch (masterItemId) {
            case 1:
            EventsFragment eventsFragment = new EventsFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, eventsFragment)
                    .addToBackStack(null)
                    .commit();
            break;
            case 2:
                LoginSignupFragment loginSignupFragment = new LoginSignupFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, loginSignupFragment, TAG_DETAIL_FRAGMENT)
                        .addToBackStack(null)
                        .commit();
                break;
            case 3:
                RealTimeDatabse realTimeDatabse = new RealTimeDatabse();
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, realTimeDatabse, TAG_DETAIL_FRAGMENT)
                        .addToBackStack(null)
                        .commit();
                break;
        }

        // Close the navigation drawer
        if (drawerLayout != null) {
            drawerLayout.closeDrawers();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}

    @Override
    public void onFloatingButtonClicked() {
        NewEventFragment newEventFragment = new NewEventFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_layout, newEventFragment)
                .addToBackStack(null)
                .commit();
    }
}
