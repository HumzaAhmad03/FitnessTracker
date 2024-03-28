package com.example.fitnesstracker;

import java.util.Map;

public class UserData {
    private String username;
    private String userEmail; // New field for user email
    private Map<String, ExerciseData> personal_bests;

    public UserData() {
        // Default constructor required for Firebase
    }

    public UserData(String username, String userEmail, Map<String, ExerciseData> personal_bests) {
        this.username = username;
        this.userEmail = userEmail;
        this.personal_bests = personal_bests;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Map<String, ExerciseData> getPersonal_bests() {
        return personal_bests;
    }

    public void setPersonal_bests(Map<String, ExerciseData> personal_bests) {
        this.personal_bests = personal_bests;
    }
}