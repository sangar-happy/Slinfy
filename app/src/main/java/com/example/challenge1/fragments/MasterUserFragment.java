package com.example.challenge1.fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.challenge1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MasterUserFragment extends Fragment implements View.OnClickListener {

    View events,
            myChats,
            editProfile,
            contactUs,
            more;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (!(context instanceof Callbacks)) {
            throw new RuntimeException("Context must implement callbacks");
        }
        callbacks = (Callbacks) context;
    }

    private MasterUserFragment.Callbacks callbacks;

    public MasterUserFragment() {
        // Required empty public constructor
    }

    public interface Callbacks {
        void onMasterUserItemClicked(int masterItemId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_master_user, container, false);
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

                break;
            case R.id.my_chats:

                break;
            case R.id.edit_profile:

                break;
            case R.id.contact_us:

                break;
            case R.id.more:

                break;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }
}
