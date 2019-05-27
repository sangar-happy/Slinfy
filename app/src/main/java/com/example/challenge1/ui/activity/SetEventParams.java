package com.example.challenge1.ui.activity;

import android.app.Application;
import android.os.Bundle;

import com.example.challenge1.DataRepository;
import com.example.challenge1.database.entity.Event;
import com.example.challenge1.viewmodel.EventViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.challenge1.R;

import java.util.Calendar;
import java.util.List;

public class SetEventParams extends AppCompatActivity {

    DatePicker datePicker;

    private DataRepository dataRepository;
    private LiveData<List<Event>> allEvents;

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

        Button button = findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Event event1 = new Event(event.getText().toString(), date.getText().toString());
                insertEvent(event1);
                Toast.makeText(SetEventParams.this, "Successful", Toast.LENGTH_SHORT).show();
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

    public void insertEvent(Event event) {
        dataRepository.insertEvent(event);
    }

    public LiveData<List<Event>> getAllEvents() {
        return allEvents;
    }

}
