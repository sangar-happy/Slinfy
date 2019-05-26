package com.example.challenge1.ui.activity;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.challenge1.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private NavigationView navigationView;
    private DrawerLayout drawer;

    private FirebaseAuth auth;
    private final String TAG = "Main Activity";
    private final int SIGNIN_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tabLayout = findViewById(R.id.tab);
        frameLayout = findViewById(R.id.fragment_container);
        fragmentManager = getSupportFragmentManager();

        drawer = findViewById(R.id.main_drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button findUser = findViewById(R.id.find_user);
        findUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getApplicationContext().checkCallingOrSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(new Intent(MainActivity.this, FindUserActivity.class));
                } else {
                    Toast.makeText(MainActivity.this, "Grant Permission", Toast.LENGTH_SHORT).show();
                }
            }
        });

        auth = FirebaseAuth.getInstance();
        getPermissions();

    }

    private void getPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS}, 1);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
        updateUi(user);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), SignIn.class);
                intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_contactUs:
                Toast.makeText(this, "YOLO", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_events:

                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.add(R.id.fragment_container, new BlankFragment());
                fragmentTransaction.commit();
                break;

            case R.id.nav_more:
                Intent intent = new Intent(MainActivity.this, SignIn.class);
                startActivity(intent);
                break;
                
            case R.id.nav_signin:
                Intent intent_signin = new Intent(getApplicationContext(), SignIn.class);
                startActivityForResult(intent_signin, SIGNIN_REQUEST_CODE);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void updateUi(FirebaseUser user) {

        if (user != null) {

            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.drawer_menu_user);

//            View nav_header = LayoutInflater.from(this).inflate(R.layout.nav_header, (ViewGroup) findViewById(R.id.nav_view), false);
            View nav_header = LayoutInflater.from(this).inflate(R.layout.nav_header_google_user, (ViewGroup) findViewById(R.id.nav_view), false);
//            ((TextView) nav_header.findViewById(R.id.name)).setText("UserName");
            navigationView.removeView(findViewById(R.id.nav_view));
            navigationView.addHeaderView(nav_header);

            for (UserInfo userInfo: FirebaseAuth.getInstance().getCurrentUser().getProviderData()) {
                if (userInfo.getProviderId().equals("google.com")) {
                    Log.d(TAG, "provider is google");
                    View headerLayout = LayoutInflater.from(this).inflate(R.layout.nav_header_google_user,null);
                    CircleImageView circleImageView = headerLayout.findViewById(R.id.nav_profile_pic);
                    Picasso.get().load(user.getPhotoUrl()).into(circleImageView);


                    TextView name = headerLayout.findViewById(R.id.nav_editProfile);
                    TextView identifier = headerLayout.findViewById(R.id.nav_identifier);

//                    name.setText(user.getDisplayName());
//                    identifier.setText(user.getEmail());
                }
                if(userInfo.getProviderId().equals("phone")) {
                    //TODO
                }
            }


            navigationView.setNavigationItemSelectedListener(this);

        } else {
            navigationView.setNavigationItemSelectedListener(this);
        }

    }

    public void setToolbar(Toolbar toolbar) {
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();
        } else {
            drawer.addDrawerListener(null);
        }
    }

}
