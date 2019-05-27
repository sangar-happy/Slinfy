package com.example.challenge1.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.challenge1.database.entity.Event;
import com.example.challenge1.DataRepository;

import java.util.List;

public class EventViewModel extends AndroidViewModel {
    private DataRepository dataRepository;
    private LiveData<List<Event>> allEvents;

    public EventViewModel(@NonNull Application application) {
        super(application);
        dataRepository = new DataRepository(application);
        allEvents = dataRepository.getAllEvents();
    }

    public void insertEvent(Event event) {
        dataRepository.insertEvent(event);
    }

    public LiveData<List<Event>> getAllEvents() {
        return allEvents;
    }
}
