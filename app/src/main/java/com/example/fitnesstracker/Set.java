package com.example.fitnesstracker;

import com.google.firebase.database.PropertyName;
import java.util.HashMap;
import java.util.Map;

public class Set {
    @PropertyName("reps")
    private int reps;

    @PropertyName("weight")
    private int weight;

    public Set() {
        // Default constructor required for Firebase
    }

    public Set(int reps, int weight) {
        this.reps = reps;
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
