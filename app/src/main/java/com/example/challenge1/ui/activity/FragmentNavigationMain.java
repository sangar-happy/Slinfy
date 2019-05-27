package com.example.challenge1.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.challenge1.ViewModifier;
import com.example.challenge1.ui.fragment.EventsFragment;
import com.example.challenge1.ui.fragment.PhoneAuthFragment;
import com.example.challenge1.ui.fragment.UserNavPanel;
import com.example.challenge1.ui.fragment.AnonymousNavPanel;
import com.example.challenge1.ui.fragment.CreateEventFragment;
import com.example.challenge1.ui.fragment.RealTimeDatabse;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
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
        UserNavPanel.Callbacks,
        AnonymousNavPanel.Callbacks,
        RealTimeDatabse.OnFragmentInteractionListener,
        PhoneAuthFragment.Callbacks,
        EventsFragment.EventsFragmentInteraction,
        ViewModifier,
        CreateEventFragment.Callbacks{


    private FirebaseUser user;

    private static final String TAG_ANON_NAV_FRAGMENT = "TAG_ANON_NAV_FRAGMENT";
    private static final String TAG_USER_NAV_FRAGMENT = "TAG_USER_NAV_FRAGMENT";
    private static final String TAG_EVENTS_FRAGMENT = "TAG_EVENTS_FRAGMENT";
    private static final String TAG_PHONE_AUTH_FRAGMENT = "TAG_PHONE_AUTH_FRAGMENT";
    private static final String TAG_ADMIN_AUTH_FRAGMENT = "TAG_ADMIN_AUTH_FRAGMENT";
    private static final String TAG_CREATE_EVENT_FRAGMENT = "TAG_CREATE_EVENT_FRAGMENT";

    private DrawerLayout mDrawerLayout;

    private FragmentManager mFragmentManager;
    private AnonymousNavPanel mAnonymousNavPanel = new AnonymousNavPanel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_navigation_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // setup navigation drawer
        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        mFragmentManager = getSupportFragmentManager();

        EventsFragment mEventsFragment = EventsFragment.newInstance();
        mFragmentManager.beginTransaction()
                .add(R.id.frame_layout, mEventsFragment)
                .commit();

        user = FirebaseAuth.getInstance().getCurrentUser();

// TODO: check internet connectivity and show a snackbar to user if no connectivity

//        new InternetCheck(this).isInternetConnectionAvailable(new InternetCheck.InternetCheckListener() {
//            @Override
//            public void onComplete(boolean connected) {
//                Toast.makeText(FragmentNavigationMainsad.this, "Internet Available", Toast.LENGTH_SHORT).show();
//            }
//        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        updateUi(user);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO: hide options menu for anonymous user
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

            case R.id.logout:
                if (user != null) {
                    FirebaseAuth.getInstance().signOut();
                }
                updateUi(user);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        menu.findItem(R.id.logout).setVisible(user != null);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {

        // if drawer was open, close it
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
            return;
        }

        // if this is the last fragment, get user confirmation to leave the app
        if (getSupportFragmentManager().getBackStackEntryCount() < 1) {
            //TODO: try to implement exit with toast synchronization
            new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setTitle("Exiting the app..")
                    .setMessage("Are you sure you want to exit?")
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // if user presses cancel don't do anything
                        }
                    })
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            // if user presses ok then finish the activity
                            finish();
                        }
                    }).create().show();

        } else {
            // else just remove fragment
            super.onBackPressed();
        }
    }

    private void updateUi(FirebaseUser user) {

        invalidateOptionsMenu();

        if (user == null) {
            mFragmentManager.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .replace(R.id.master_fragment_container, mAnonymousNavPanel, TAG_ANON_NAV_FRAGMENT)
                    .commit();
            //TODO: hide the options menu

        } else {

            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                // remove all the fragments from the stack if a user signs in
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }

            // add the EventsFragment and pass the user object to load user's events
            EventsFragment eventsFragment = new EventsFragment();
            mFragmentManager.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .add(R.id.frame_layout, eventsFragment, TAG_EVENTS_FRAGMENT)
                    .commit();


            // update navigation drawer
            UserNavPanel mUserNavPanel = new UserNavPanel();
            mFragmentManager.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .replace(R.id.master_fragment_container, mUserNavPanel, TAG_USER_NAV_FRAGMENT)
                    .commit();
        }
    }

    @Override
    public void onAnonymousNavPanelItemClicked(int panelItemId) {

        switch (panelItemId) {

            case 1:
                EventsFragment eventsFragment = new EventsFragment();
                mFragmentManager.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .replace(R.id.frame_layout, eventsFragment, TAG_EVENTS_FRAGMENT)
                        .addToBackStack(TAG_EVENTS_FRAGMENT)
                        .commit();
                break;

            case 2:
                PhoneAuthFragment phoneAuthFragment = new PhoneAuthFragment();
                mFragmentManager = getSupportFragmentManager();
                mFragmentManager.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .replace(R.id.frame_layout, phoneAuthFragment, TAG_PHONE_AUTH_FRAGMENT)
                        .addToBackStack(TAG_PHONE_AUTH_FRAGMENT)
                        .commit();
                break;

            case 3:
                break;
        }


        // Close the navigation drawer
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawers();
        }
    }

    @Override
    public void onUserNavPanelItemClicked(int panelItemId) {

        switch (panelItemId) {
            case 1:
                EventsFragment eventsFragment = new EventsFragment();
                mFragmentManager.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .replace(R.id.frame_layout, eventsFragment, TAG_EVENTS_FRAGMENT)
                        .addToBackStack(TAG_EVENTS_FRAGMENT)
                        .commit();
                break;

            case 2:

                break;

            case 3:
                RealTimeDatabse realTimeDatabse = new RealTimeDatabse();
                mFragmentManager = getSupportFragmentManager();
                mFragmentManager.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .replace(R.id.frame_layout, realTimeDatabse, "TEST")
                        .addToBackStack("TEST")
                        .commit();
                break;
        }

        // Close the navigation drawer
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawers();
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    public void updateUser(FirebaseUser user) {
        updateUi(user);
    }

    @Override
    public void onFloatingButtonClicked() {

        // add CreateEventFragment only if user is logged in
        // TODO: hide FAB for anonymous user
        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            CreateEventFragment createEventFragment = new CreateEventFragment();
            mFragmentManager = getSupportFragmentManager();
            mFragmentManager.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .replace(R.id.frame_layout, createEventFragment, TAG_CREATE_EVENT_FRAGMENT)
                    .addToBackStack(null)
                    .commit();
        } else {
            Toast.makeText(this, "Must Signin to create Event.", Toast.LENGTH_SHORT).show();
        }
    }

    // TODO: update nav panel according to the current fragment visible
    public Fragment getActiveFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            return null;
        }
        String tag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
        return getSupportFragmentManager().findFragmentByTag(tag);
    }

    @Override
    public void setTitleInterface(String title) {
        setTitle(title);
    }

    @Override
    public void onEventItemClicked(String event) {
        Intent intent = new Intent(this, SetEventParams.class);
        intent.putExtra("eventType", event);
        startActivity(intent);
    }
}
