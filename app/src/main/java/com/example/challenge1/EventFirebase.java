package com.example.challenge1;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class EventFirebase {

    private String userId;
    private String eventCategory;
    private String eventDate;
    private Boolean isApproved;

    public EventFirebase() {}

    public EventFirebase(String userId, String eventCategory, String eventDate) {
        this.userId = userId;
        this.eventCategory = eventCategory;
        this.eventDate = eventDate;
        isApproved = false;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public String getEventDate() {
        return eventDate;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public String getUserId() {
        return userId;
    }

}
