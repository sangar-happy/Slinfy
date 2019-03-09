package com.example.challenge1.LoginPageWithTabLayout;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.challenge1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserLoginFragment extends Fragment {

	RegisterFragment registerFragment;
	Button button, newUser;

	public UserLoginFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_user_login, container, false);
		button = view.findViewById(R.id.submit);
		newUser = view.findViewById(R.id.newUser);

		registerFragment = new RegisterFragment();

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();
			}
		});

		newUser.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivityForLogin activity = (MainActivityForLogin) getActivity();
				activity.fragmentTransaction = activity.fragmentManager.beginTransaction();
				activity.fragmentTransaction.replace(R.id.fr, registerFragment);
				activity.fragmentTransaction.commit();
			}
		});
		return view;
	}

}
