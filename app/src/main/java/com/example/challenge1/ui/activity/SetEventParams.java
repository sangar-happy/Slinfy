package com.example.challenge1.ui.activity;

import android.app.Application;
import android.os.Bundle;

import com.example.challenge1.DataRepository;
import com.example.challenge1.EventFirebase;
import com.example.challenge1.database.entity.Event;
import com.example.challenge1.viewmodel.EventViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.challenge1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SetEventParams extends AppCompatActivity {

    DatePicker datePicker;

    private DataRepository dataRepository;
    private LiveData<List<Event>> allEvents;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_event_params);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Calendar cal = Calendar.getInstance();
        final TextView date = findViewById(R.id.date);
        final TextView event = findViewById(R.id.event);
        event.setText(getIntent().getExtras().getString("eventType"));

        dataRepository = new DataRepository(getApplication());
        allEvents = dataRepository.getAllEvents();

        button = findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(date.getText().toString().equals("date")) {
                    Toast.makeText(SetEventParams.this, "Set the date.", Toast.LENGTH_SHORT).show();
                } else {
                    writeNewUser(event.getText().toString(), date.getText().toString());
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(1000);
                                finish();
                            } catch (Exception e) {}

                        }
                    }).start();
                }
            }
        });


        datePicker = findViewById(R.id.datePicker);
        datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date.setText(datePicker.getDayOfMonth() + "-" + datePicker.getMonth() + "-" + datePicker.getYear());
            }
        });
    }

    private void writeNewUser(String eventType, String date) {

        EventFirebase newEvent = new EventFirebase(FirebaseAuth.getInstance().getCurrentUser().getUid(),eventType, date);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("events");
        ref.push().setValue(newEvent, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                Toast.makeText(SetEventParams.this, "Event Created", Toast.LENGTH_SHORT).show();
            }
        });
    }
//    public void insertEvent(Event event) {
//        dataRepository.insertEvent(event);
//    }
}
