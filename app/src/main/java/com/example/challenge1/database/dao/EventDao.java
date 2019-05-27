package com.example.challenge1.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.challenge1.database.entity.Event;

import java.util.List;

@Dao
public interface EventDao {

    @Insert
    void insertEvent(Event event);

//    @Update
//    void updateEvent(Event event);
//
//    @Delete
//    void deleteEvent(Event event);
//
//    @Query("DELETE FROM event_table")
//    void deleteAllEvents();

    @Query("SELECT * FROM event_table")
    LiveData<List<Event>> getAllEvents();
}
