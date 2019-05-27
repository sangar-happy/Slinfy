package com.example.challenge1.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.challenge1.database.dao.EventDao;
import com.example.challenge1.database.entity.Event;

@androidx.room.Database(entities = Event.class, version = 1)
public abstract class Database extends RoomDatabase {

    private static Database databaseInstance;

    public abstract EventDao eventDao();

    public static synchronized Database getInstance(Context context) {
        if(databaseInstance == null) {
            databaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                    Database.class, "event_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return databaseInstance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(databaseInstance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        EventDao eventDao;

        public PopulateDbAsyncTask(Database database) {
            this.eventDao = database.eventDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            eventDao.insertEvent(new Event("Birthday", "Harpreet"));
            eventDao.insertEvent(new Event("Marriage", "Vinay"));
            eventDao.insertEvent(new Event("Marriage", "Vishal"));
            eventDao.insertEvent(new Event("Home Warming", "Dawinder"));
            eventDao.insertEvent(new Event("Graduation", "Narinder"));
            eventDao.insertEvent(new Event("Marriage", "Kamal"));
            eventDao.insertEvent(new Event("Marriage", "Vishal"));
            return null;
        }
    }
}
