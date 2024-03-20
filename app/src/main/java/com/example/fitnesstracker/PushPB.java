package com.example.fitnesstracker;
import com.google.firebase.database.IgnoreExtraProperties;
@IgnoreExtraProperties
public class PushPB {
    private String benchPBWeight;
    private String inclinePBWeight;
    private String tricepPBWeight;
    private String benchPBReps;
    private String inclinePBReps;
    private String tricepPBReps;

    public PushPB() {
    }
    public PushPB(String benchPBWeight, String inclinePBWeight, String tricepPBWeight, String benchPBReps, String inclinePBReps, String tricepPBReps) {
        this.benchPBWeight = benchPBWeight;
        this.inclinePBWeight = inclinePBWeight;
        this.tricepPBWeight = tricepPBWeight;
        this.benchPBReps = benchPBReps;
        this.inclinePBReps = inclinePBReps;
        this.tricepPBReps = tricepPBReps;
    }

    public String getBenchPBWeight() {
        return benchPBWeight;
    }

    public String getInclinePBWeight() {
        return inclinePBWeight;
    }

    public String getTricepPBWeight() {
        return tricepPBWeight;
    }

    public String getBenchPBReps() {
        return benchPBReps;
    }

    public String getInclinePBReps() {
        return inclinePBReps;
    }

    public String getTricepPBReps() {
        return tricepPBReps;
    }
}
