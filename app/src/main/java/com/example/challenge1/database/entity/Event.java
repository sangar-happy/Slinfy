package com.example.challenge1.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


import java.util.Date;

@Entity(tableName = "event_table")
public class Event {

    @ColumnInfo(name = "category")
    private String eventCategory;

    @ColumnInfo(name = "date")
    private String eventDate;

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String eventId;

    public Event(String eventCategory, String eventDate, @NonNull String eventId) {
        this.eventCategory = eventCategory;
        this.eventDate = eventDate;
        this.eventId = eventId;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public String getEventDate() {
        return eventDate;
    }

    @NonNull
    public String getEventId() {
        return eventId;
    }
}