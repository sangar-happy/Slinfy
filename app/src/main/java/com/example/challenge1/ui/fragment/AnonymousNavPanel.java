package com.example.challenge1.ui.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.challenge1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnonymousNavPanel extends Fragment implements View.OnClickListener {

    private Callbacks mCallbacks;
    private int mVisibleFragmentId;

    LinearLayout events, sigin, contact_us;

    public AnonymousNavPanel() {
        // Required empty public constructor
    }

    public interface Callbacks {
        void onAnonymousNavPanelItemClicked(int panelItemId);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof Callbacks)) {
            throw new RuntimeException("Context must implement mCallbacks");
        }

        mCallbacks = (Callbacks) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_anonymous_nav_panel, container, false);


        events = view.findViewById(R.id.events);
        events.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        events.setOnClickListener(this);

        sigin = view.findViewById(R.id.sigin);
        sigin.setOnClickListener(this);

        contact_us = view.findViewById(R.id.contact_us);
        contact_us.setOnClickListener(this);

        mVisibleFragmentId = R.id.events;

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onClick(View v) {
        int clickedItem = v.getId();

        if (mVisibleFragmentId == clickedItem) {
            mCallbacks.onAnonymousNavPanelItemClicked(-1);
            return;
        }

        mVisibleFragmentId = clickedItem;

        switch (clickedItem) {
            case R.id.events:
                mCallbacks.onAnonymousNavPanelItemClicked(1);
                break;
            case R.id.sigin:
                sigin.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                mCallbacks.onAnonymousNavPanelItemClicked(2);
                break;
            case R.id.contact_us:
                contact_us.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                mCallbacks.onAnonymousNavPanelItemClicked(3);
                break;
        }

        updateSelectedNavItem(clickedItem);
    }

    private void updateSelectedNavItem(int id) {
        if(id == events.getId()) {
            events.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        } else {
            events.setBackgroundColor(0x00000000);
        }

        if(id == sigin.getId()) {
            sigin.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        } else {
            sigin.setBackgroundColor(0x00000000);
        }

        if(id == contact_us.getId()) {
            contact_us.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        } else {
            contact_us.setBackgroundColor(0x00000000);
        }
    }
}