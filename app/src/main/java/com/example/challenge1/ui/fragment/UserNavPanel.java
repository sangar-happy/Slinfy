package com.example.challenge1.ui.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.challenge1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
    private TextView userName;
    private String getName;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof Callbacks)) {
            throw new RuntimeException("Context must implement callbacks");
        }
        callbacks = (Callbacks) context;
        getAllUsersFromFirebase();
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

        userName = view.findViewById(R.id.user_name);

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

    public void getAllUsersFromFirebase() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");

        Query query = reference.child("users").equalTo(FirebaseAuth.getInstance().getUid());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                         getName = issue.getValue().toString();
                         userName.setText(getName);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
