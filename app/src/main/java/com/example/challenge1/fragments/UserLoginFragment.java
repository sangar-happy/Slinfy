package com.example.challenge1.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.challenge1.R;
import com.example.challenge1.activities.ActivityForSignup;
import com.example.challenge1.activities.MainActivity;
import com.google.android.material.textfield.TextInputEditText;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserLoginFragment extends Fragment {

	Button login, signup;
	TextInputEditText username, password;

	public UserLoginFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_user_login, container, false);
		login = view.findViewById(R.id.login);
		signup = view.findViewById(R.id.signup);
		password = view.findViewById(R.id.password);

		login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();
			}
		});

		signup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity activity = (MainActivity) getActivity();
				Intent intent = new Intent(activity, ActivityForSignup.class);
				startActivity(intent);
			}
		});

		return view;
	}

}
