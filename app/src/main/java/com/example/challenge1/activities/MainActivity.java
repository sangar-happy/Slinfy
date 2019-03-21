package com.example.challenge1.activities;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.FrameLayout;

import com.example.challenge1.R;
import com.example.challenge1.fragments.AdminLoginFragment;
import com.example.challenge1.fragments.UserLoginFragment;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

	AdminLoginFragment adminLoginFragment;
	UserLoginFragment userLoginFragment;
	Fragment fragment;
	TabLayout tabLayout;
	FrameLayout frameLayout;
	FragmentManager fragmentManager;
	FragmentTransaction fragmentTransaction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tabLayout=findViewById(R.id.tab);
		frameLayout = findViewById(R.id.fr);

		tabLayout.addTab(tabLayout.newTab().setText("User Login"));
		tabLayout.addTab(tabLayout.newTab().setText("Admin Login"));

		adminLoginFragment = new AdminLoginFragment();
		userLoginFragment = new UserLoginFragment();

		fragmentManager = getSupportFragmentManager();
		fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.fr, userLoginFragment);
		fragmentTransaction.commit();

		tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {

				fragmentTransaction = fragmentManager.beginTransaction();

				switch (tab.getPosition())
				{
					case 0:
						fragment = userLoginFragment;
						break;
					case 1:
						fragment = adminLoginFragment;
						break;
				}

				fragmentTransaction.replace(R.id.fr, fragment);
				fragmentTransaction.commit();
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {
			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {
			}
		});

	}

	public void googleSignin(View view) {
		Intent intent = new Intent(MainActivity.this, GoogleSigninActivity.class);
		startActivity(intent);
	}
}
