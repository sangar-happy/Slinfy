package com.example.challenge1.ui.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.challenge1.R;
import com.example.challenge1.ViewModifier;

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
            , graduation
            , valentineday
            , specialday;
    private Callbacks callbacks;
    private ViewModifier viewModifier;

    public CreateEventFragment() {
        // Required empty public constructor
    }

    public interface Callbacks {
        void onEventItemClicked(String Event);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof AnonymousNavPanel.Callbacks)) {
            throw new RuntimeException("Context must implement Callbacks");
        }

        callbacks = (CreateEventFragment.Callbacks) context;
        viewModifier = (ViewModifier) context;
        viewModifier.setTitleInterface(getString(R.string.event_type));
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

        valentineday = view.findViewById(R.id.valentineday);
        valentineday.setOnClickListener(this);

        specialday = view.findViewById(R.id.specialday);
        specialday.setOnClickListener(this);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.birthday:
                callbacks.onEventItemClicked("Birthday");
                break;

            case R.id.marriage:
                callbacks.onEventItemClicked("Marriage");
                break;

            case R.id.anniversary:
                callbacks.onEventItemClicked("Anniversary");
                break;

            case R.id.home_warming:
                callbacks.onEventItemClicked("Home Warming");
                break;

            case R.id.engagement:
                callbacks.onEventItemClicked("Engagement");
                break;

            case R.id.graduation:
                callbacks.onEventItemClicked("Graduation");
                break;

            case R.id.valentineday:
                callbacks.onEventItemClicked("Valentine Day");
                break;

            case R.id.specialday:
                callbacks.onEventItemClicked("Special Day");
                break;
        }
    }
}