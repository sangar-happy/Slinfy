package com.example.challenge1.LoginPageWithTabLayout;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.Button;
import android.widget.FrameLayout;

import com.example.challenge1.R;

public class MainActivityForLogin extends AppCompatActivity {

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
		setContentView(R.layout.activity_main_login);
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
}
