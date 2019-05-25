package com.example.challenge1.ui.fragment;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.challenge1.R;
import com.example.challenge1.ui.adapter.ContactListAdapter;
import com.example.challenge1.database.entity.User;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class
LoadContactsFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter contactListAdapter;

    private ArrayList<User> contactsArrayList;

    public LoadContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        contactsArrayList = new ArrayList<>();

        recyclerView = container.findViewById(R.id.contactsRecyclerView);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_load_contacts, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        getPermissions();
        initializeRecyclerView(context);
        getContactList();
    }

    private void initializeRecyclerView(Context context) {
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        contactListAdapter = new ContactListAdapter(contactsArrayList);
        recyclerView.setAdapter(contactListAdapter);
    }
    private void getContactList() {

        if (getContext().checkCallingOrSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            Cursor contacts = getActivity().getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null, null
            );

            while (contacts.moveToNext()) {
                String name = contacts.getString(
                        contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                );

                String phoneNumber = contacts.getString(
                        contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                );

                User user = new User(name, phoneNumber);
                contactsArrayList.add(user);

                contactListAdapter.notifyDataSetChanged();
            }
        } else {
            Toast.makeText(getContext(), "Grant Permission", Toast.LENGTH_SHORT).show();
        }
    }
    private void getPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS}, 1);
        }
    }
}
