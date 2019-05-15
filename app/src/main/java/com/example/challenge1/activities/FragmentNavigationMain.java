package com.example.challenge1.activities;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import com.example.challenge1.fragments.EventsFragment;
import com.example.challenge1.fragments.LoadContactsFragment;
import com.example.challenge1.fragments.LoginSignupFragment;
import com.example.challenge1.fragments.MasterUserFragment;
import com.example.challenge1.fragments.MasterUserNullFragment;
import com.example.challenge1.fragments.RealTimeDatabse;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.challenge1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FragmentNavigationMain extends AppCompatActivity
        implements
        MasterUserFragment.Callbacks,
        MasterUserNullFragment.Callbacks,
        RealTimeDatabse.OnFragmentInteractionListener,
        LoginSignupFragment.Callbacks {


    private FirebaseUser user;
    private static final String TAG_MASTER_FRAGMENT = "TAG_MASTER_FRAGMENT";
    private static final String TAG_DETAIL_FRAGMENT = "TAG_DETAIL_FRAGMENT";
    private DrawerLayout drawerLayout;
    private MasterUserNullFragment masterUserNullFragment;
    private MasterUserFragment masterUserFragment;
    private LoadContactsFragment loadContactsFragment;
    private FragmentManager fragmentManager;
    private Toolbar toolbar;

    private static int previousId = 1,
             backStackCount = 1;

    @Override
    protected void onStart() {
        super.onStart();
        updateUi(user);
    }

    private void updateUi(FirebaseUser user) {
        if(user == null) {
            masterUserNullFragment = new MasterUserNullFragment();
            fragmentManager.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .replace(R.id.master_fragment_container, masterUserNullFragment, TAG_MASTER_FRAGMENT)
                    .commit();
            //TODO: hide the options menu

        } else {
            masterUserFragment = new MasterUserFragment();
            fragmentManager.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .replace(R.id.master_fragment_container, masterUserFragment, TAG_MASTER_FRAGMENT)
                    .commit();

            EventsFragment eventsFragment = new EventsFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .replace(R.id.frame_layout, eventsFragment)
                    .commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_navigation_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // setup drawer view
        drawerLayout = findViewById(R.id.drawer_layout);
        if (drawerLayout != null) {
            NavigationView navigationView = findViewById(R.id.master_fragment_container);

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            toggle.syncState();
        }

        fragmentManager = getSupportFragmentManager();
        EventsFragment eventsFragment = new EventsFragment();
        fragmentManager.beginTransaction()
                .add(R.id.frame_layout, eventsFragment)
                .commit();

        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

            case R.id.logout:
                if(user != null){
                    FirebaseAuth.getInstance().signOut();
                } else {
                    Toast.makeText(this, "Already logged out.", Toast.LENGTH_SHORT).show();
                }
                updateUi(user);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
            return;
        }

        backStackCount--;

        if(backStackCount < 1){
            //Toast.makeText(this, "Press again to exit.", Toast.LENGTH_SHORT).show();
            new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setTitle("Exiting the app..")
                    .setMessage("Are you sure you want to exit?")
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            backStackCount++;
                        }
                    })
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            FragmentNavigationMain.super.onBackPressed();
                        }
                    }).create().show();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onMasterItemClicked(int masterItemId) {

        if(previousId != masterItemId) {
            backStackCount++;
            switch (masterItemId) {
                case 1:
                    EventsFragment eventsFragment = new EventsFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .replace(R.id.frame_layout, eventsFragment)
                            .addToBackStack(null)
                            .commit();
                    break;
                case 2:
                    LoginSignupFragment loginSignupFragment = new LoginSignupFragment();
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .replace(R.id.frame_layout, loginSignupFragment, TAG_DETAIL_FRAGMENT)
                            .addToBackStack(null)
                            .commit();
                    break;
                case 3:
                    RealTimeDatabse realTimeDatabse = new RealTimeDatabse();
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .replace(R.id.frame_layout, realTimeDatabse, TAG_DETAIL_FRAGMENT)
                            .addToBackStack(null)
                            .commit();
                    break;
            }
        }

        previousId = masterItemId;

        // Close the navigation drawer
        if (drawerLayout != null) {
            drawerLayout.closeDrawers();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}

    @Override
    public void updateUser(FirebaseUser user) {
        updateUi(user);
    }

    @Override
    public void onMasterUserItemClicked(int masterItemId) {

    }
}
