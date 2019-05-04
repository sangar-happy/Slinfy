package com.example.challenge1.fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.challenge1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MasterFragment extends Fragment {

    private Callbacks callbacks;
    private View events;
    private SparseIntArray mapId;

    public MasterFragment() {
        // Required empty public constructor
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
            public void onClick(View v) {
            }
        });

        mapId = new SparseIntArray();
        mapId.put(1, R.id.events);
        mapId.put(2, R.id.sigin);
        mapId.put(3, R.id.contact_us);
        mapId.put(4, R.id.more);

        events = view.findViewById(R.id.events);
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbacks.onMasterItemClicked(1);
            }
        });

        view.findViewById(R.id.sigin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbacks.onMasterItemClicked(2);

            }
        });

        view.findViewById(R.id.contact_us).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callbacks.onMasterItemClicked(3);
            }
        });

        view.findViewById(R.id.more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //TODO implement updateUi()
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

    public void updateUi(int elementSelected) {
        ImageView ic = events.findViewById(R.id.events_ic);
        TextView text = events.findViewById(R.id.events_text);

    }
}