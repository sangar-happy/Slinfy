package com.example.challenge1.models;

public class User {

    private String name, message, time;
    private int profilePic;

    public User(String name, String message, String time, int profilePic) {
        this.name = name;
        this.message = message;
        this.time = time;
        this.profilePic = profilePic;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public int getProfilePic() {
        return profilePic;
    }
}
