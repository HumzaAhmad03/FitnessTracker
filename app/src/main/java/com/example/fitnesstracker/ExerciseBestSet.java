package com.example.fitnesstracker;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

@IgnoreExtraProperties
public class ExerciseBestSet {

    @PropertyName("BestSetWeight")
    private int BestSetWeight;
    @PropertyName("BestSetReps")
    private int BestSetReps;

    public int getBestSetWeight() {
        return BestSetWeight;
    }

    public int getBestSetReps() {
        return BestSetReps;
    }

    public ExerciseBestSet(int bestSetWeight, int bestSetReps) {
        BestSetWeight = bestSetWeight;
        BestSetReps = bestSetReps;
    }

    public ExerciseBestSet() {
    }
}
