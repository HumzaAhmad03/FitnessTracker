package com.example.fitnesstracker;

import com.google.firebase.database.PropertyName;
import java.util.HashMap;
import java.util.Map;

public class Exercise {
    @PropertyName("name")
    private String name;

    @PropertyName("sets")
    private Map<String, Set> sets;

    public Exercise() {
        // Default constructor required for Firebase
    }

    public Exercise(String name) {
        this.name = name;
        //this.sets = sets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Set> getSets() {
        return sets;
    }

    public void setSets(Map<String, Set> sets) {
        this.sets = sets;
    }

    public void addSet(String setId, Set set) {
        if (sets == null) {
            sets = new HashMap<>();
        }
        sets.put(setId, set);
    }
}
