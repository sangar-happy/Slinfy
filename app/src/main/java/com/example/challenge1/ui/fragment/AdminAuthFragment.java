package com.example.challenge1.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.challenge1.R;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminAuthFragment extends Fragment {


	Button submit;

	public AdminAuthFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, final ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_admin_login, container, false);
		submit = view.findViewById(R.id.login);

		submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();
			}
		});
		return view;
	}

}