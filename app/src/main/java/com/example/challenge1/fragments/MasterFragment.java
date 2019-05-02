package com.example.challenge1.fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.challenge1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MasterFragment extends Fragment {

    private Callbacks callbacks;
    private View lastItemSelected;

    public MasterFragment() {
        // Required empty public constructor
    }

    private void deselect() {

    }

    public interface Callbacks {
        void onMasterItemClicked(int masterItemId);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof Callbacks)) {
            throw new RuntimeException("Context must implement callbacks");
        }
        callbacks = (Callbacks) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_master_user_null, container, false);
        
        view.findViewById(R.id.appLogo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {}
        });

        view.findViewById(R.id.events).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deselect();
                TextView textView = v.findViewById(R.id.events_text);
                textView.setTextColor(getResources().getColor(R.color.warning));
                callbacks.onMasterItemClicked(1);
            }
        });

        view.findViewById(R.id.sigin_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        view.findViewById(R.id.contact_us).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        view.findViewById(R.id.more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

//        view.findViewById(R.id.my_chats).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

//        view.findViewById(R.id.edit_profile).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }

}
