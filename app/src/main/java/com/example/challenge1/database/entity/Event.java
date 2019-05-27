package com.example.challenge1.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "event_table")
public class Event {

    @ColumnInfo(name = "category")
    private String eventCategory;

    @ColumnInfo(name = "date")
    private String date;

    public Event(String eventCategory, String date) {
        this.eventCategory = eventCategory;
        this.date = date;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public String getDate() {
        return date;
    }

}
