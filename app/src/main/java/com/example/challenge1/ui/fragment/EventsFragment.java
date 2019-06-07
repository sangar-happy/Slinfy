package com.example.challenge1.ui.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.challenge1.EventFirebase;
import com.example.challenge1.R;
import com.example.challenge1.ViewModifier;
import com.example.challenge1.database.entity.Event;
import com.example.challenge1.ui.adapter.EventAdapter;
import com.example.challenge1.viewmodel.EventViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {

    private static final String TAG = EventsFragment.class.getSimpleName();
    private EventsFragmentInteraction eventsFragmentInteraction;
    private ViewModifier viewModifier;

    private static final String EXTRA_NUMBER = "extra_number";

    private EventViewModel eventViewModel;

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public EventsFragment() {
        // Required empty public constructor
    }

    public static final EventsFragment newInstance() {
        EventsFragment eventsFragment = new EventsFragment();
        Bundle bundle = new Bundle(1);
        bundle.putInt(EXTRA_NUMBER, 12);
        eventsFragment.setArguments(bundle);
        return eventsFragment;
    }

    public interface EventsFragmentInteraction {
        // TODO: set visibility of fab
        //void setFabVisibility(boolean visibility);
        void onFloatingButtonClicked();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //String s = getArguments().getString(EXTRA_NUMBER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventsFragmentInteraction.onFloatingButtonClicked();
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.event_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        final EventAdapter adapter = new EventAdapter();
        recyclerView.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("events");

        if (user != null) {
            ref.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    List<EventFirebase> list = new ArrayList<>();
                    // receiving data which was stored as an object
                    for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                        // receiving individual states
                        String userId = (String) messageSnapshot.child("userId").getValue();
                        String eventCategory = (String) messageSnapshot.child("eventCategory").getValue();
                        String eventDate = (String) messageSnapshot.child("eventDate").getValue();

                        if (user.getUid().equals(userId))
                            list.add(new EventFirebase(userId, eventCategory, eventDate));
                        else
                            Log.d("EventsFragment", "Current UserID " + user.getUid() + " != " + userId);

                        // receiving whole object with the built-in JSON-to-POJO serializer/deserializer
                        // Message message = messageSnapshot.getValue(Message.class);

                    }
                    adapter.setEventList(list);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });
        } else {
            Toast.makeText(getContext(), "Signin to view your events.", Toast.LENGTH_SHORT).show();
        }

//        eventViewModel = ViewModelProviders.of(this).get(EventViewModel.class);
//        eventViewModel.getAllEvents().observe(this, new Observer<List<Event>>() {
//            @Override
//            public void onChanged(List<Event> events) {
//                adapter.setEventz(events);
//            }
//        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof EventsFragmentInteraction)) {
            throw new RuntimeException("Context must implement callbacks");
        }
        eventsFragmentInteraction = (EventsFragmentInteraction) context;

        // if activity has implemented ViewModifier
        if (context instanceof ViewModifier) {
            viewModifier = (ViewModifier) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (viewModifier != null) {
            // Set the properties which were changed by this fragment
        }
    }

}
