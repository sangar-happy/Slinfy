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
public class UserNavPanel extends Fragment implements View.OnClickListener {

    private Callbacks callbacks;
    private LinearLayout events,
            myChats,
            editProfile,
            contactUs,
            more;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof Callbacks)) {
            throw new RuntimeException("Context must implement callbacks");
        }
        callbacks = (Callbacks) context;
    }


    public UserNavPanel() {
        // Required empty public constructor
    }

    public interface Callbacks {
        void onUserNavPanelItemClicked(int panelItemId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_nav_panel, container, false);

        events = view.findViewById(R.id.events);
        events.setOnClickListener(this);

        myChats = view.findViewById(R.id.my_chats);
        myChats.setOnClickListener(this);

        editProfile = view.findViewById(R.id.edit_profile);
        editProfile.setOnClickListener(this);

        contactUs = view.findViewById(R.id.contact_us);
        contactUs.setOnClickListener(this);

        more = view.findViewById(R.id.more);
        more.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.events:
                callbacks.onUserNavPanelItemClicked(1);
                break;
            case R.id.my_chats:
                callbacks.onUserNavPanelItemClicked(2);
                break;
            case R.id.edit_profile:
                callbacks.onUserNavPanelItemClicked(3);
                break;
            case R.id.contact_us:
                callbacks.onUserNavPanelItemClicked(4);
                break;
            case R.id.more:
                callbacks.onUserNavPanelItemClicked(5);
                break;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }
}
