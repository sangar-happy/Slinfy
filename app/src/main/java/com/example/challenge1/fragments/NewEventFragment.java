package com.example.challenge1.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.challenge1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewEventFragment extends Fragment {

    private Button submit;

    public NewEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_event, container, false);

        Spinner spinner = view.findViewById(R.id.event_type);
        submit = view.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        String [] events = {"Birthday", "Marriage", "Anniversary", "Home Warming", "Engagement", "Graduation"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, events);
        spinner.setAdapter(adapter);

        // Inflate the layout for this fragment
        return view;
    }

}