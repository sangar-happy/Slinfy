package com.example.challenge1.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "event_table")
public class Event {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int eventId;

    @ColumnInfo(name = "category")
    private String eventCategory;

    @ColumnInfo(name = "title")
    private String eventTitle;

    public Event(String eventCategory, String eventTitle) {
        this.eventCategory = eventCategory;
        this.eventTitle = eventTitle;
    }

    public int getEventId() {
        return eventId;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
