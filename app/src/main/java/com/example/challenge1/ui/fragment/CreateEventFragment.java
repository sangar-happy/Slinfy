package com.example.challenge1.ui.fragment;


import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.challenge1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEventFragment extends Fragment implements View.OnClickListener {

    private Button submit;
    private CardView birthday
            , marriage
            , anniversary
            , homeWarming
            , engagement
            , graduation;


    public CreateEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_event, container, false);

        birthday = view.findViewById(R.id.birthday);
        birthday.setOnClickListener(this);

        marriage = view.findViewById(R.id.marriage);
        marriage.setOnClickListener(this);

        anniversary = view.findViewById(R.id.anniversary);
        anniversary.setOnClickListener(this);

        homeWarming = view.findViewById(R.id.home_warming);
        homeWarming.setOnClickListener(this);

        engagement = view.findViewById(R.id.engagement);
        engagement.setOnClickListener(this);

        graduation = view.findViewById(R.id.graduation);
        graduation.setOnClickListener(this);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.birthday:

                break;

            case R.id.marriage:

                break;

            case R.id.anniversary:

                break;

            case R.id.home_warming:

                break;

            case R.id.engagement:

                break;

            case R.id.graduation:

                break;
        }
    }
}