package com.example.challenge1;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.challenge1.database.dao.EventDao;
import com.example.challenge1.database.entity.Event;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import com.example.challenge1.database.Database;

public class DataRepository {
    private EventDao eventDao;
    private LiveData<List<Event>> allEvents;


    public DataRepository(Application application) {
        Database database = Database.getInstance(application);
        eventDao = database.eventDao();
        allEvents = eventDao.getAllEvents();
    }

    public void insertEvent(Event event) {
        new InsertEventAsyncTask(eventDao).execute(event);
    }

    public void updateEvent(Event event) {
        new UpdateEventAsyncTask(eventDao).execute(event);
    }

    public void deleteEvent(Event event) {
        new DeleteEventAsyncTask(eventDao).execute(event);
    }

    public void deleteAllEvents() {
        new DeleteAllEventsAsyncTask(eventDao).execute();
    }

    public LiveData<List<Event>> getAllEvents() {
        return allEvents;
    }

    private static class InsertEventAsyncTask extends AsyncTask<Event, Void, Void> {
        private EventDao eventDao;

        public InsertEventAsyncTask(EventDao eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Event... events) {
            eventDao.insertEvent(events[0]);


            DatabaseReference usersDbRef = FirebaseDatabase.getInstance().getReference().child("users");

            usersDbRef.child("name").setValue("harpreet").addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    //if(task.isSuccessful()) Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT).show();
                    //else Toast.makeText(getContext(), "Unccessful", Toast.LENGTH_SHORT).show();
                }
            });


            return null;
        }
    }

    private static class UpdateEventAsyncTask extends AsyncTask<Event, Void, Void> {
        private EventDao eventDao;

        public UpdateEventAsyncTask(EventDao eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Event... events) {
            eventDao.updateEvent(events[0]);
            return null;
        }
    }
    private static class DeleteEventAsyncTask extends AsyncTask<Event, Void, Void> {
        private EventDao eventDao;

        public DeleteEventAsyncTask(EventDao eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Event... events) {
            eventDao.deleteEvent(events[0]);
            return null;
        }
    }

    private static class DeleteAllEventsAsyncTask extends AsyncTask<Void, Void, Void> {
        private EventDao eventDao;

        public DeleteAllEventsAsyncTask(EventDao eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            eventDao.deleteAllEvents();
            return null;
        }
    }
}
