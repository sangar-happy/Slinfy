package com.example.challenge1.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;

import com.example.challenge1.R;
import com.example.challenge1.adapters.UserListAdapter;
import com.example.challenge1.models.User;
import com.example.challenge1.utils.Iso2Phone;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FindUserActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter userListAdapter;

    ArrayList<User> contactList, userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_user);

        contactList = new ArrayList<>();
        userList = new ArrayList<>();

        initializeRecyclerView();
        getContactList();
    }

    private void getContactList() {

        String isoPrefix = getCountryISO();

        Cursor contacts = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null
        );

        while(contacts.moveToNext()) {
            String name = contacts.getString(
                    contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            );

            String message = contacts.getString(
                    contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            );

            if(!String.valueOf(message.charAt(0)).equals("+")) {
                message = isoPrefix + message;
            }

            User user = new User(name, message, "1:00 PM", R.mipmap.ic_launcher);
            contactList.add(user);

            getUserDetails(user);
        }
    }

    private void getUserDetails(User contactList) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("user");
        Query query = databaseReference.orderByChild("phone").equalTo(contactList.getMessage());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    String message = "",
                            name = "";

                    for(DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        if(childSnapshot.child("phone").getValue() != null) {
                            message = childSnapshot.child("phone").getValue().toString();
                        }
                        if(childSnapshot.child("name").getValue() != null) {
                            name = childSnapshot.child("name").getValue().toString();
                        }

                        User user = new User(name, message, "1:00 PM", R.mipmap.ic_launcher);
                        userList.add(user);

                        userListAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    private String getCountryISO() {
        String iso = null;

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);;
        if(telephonyManager.getNetworkCountryIso() != null) {
                iso = telephonyManager.getNetworkCountryIso();
        }
        return Iso2Phone.getPhone(iso);
    }

    private void initializeRecyclerView() {
        recyclerView = findViewById(R.id.userList);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userListAdapter = new UserListAdapter(userList);
        recyclerView.setAdapter(userListAdapter);
    }
}
