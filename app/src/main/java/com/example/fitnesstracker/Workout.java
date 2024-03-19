package com.example.fitnesstracker;

import com.google.firebase.database.PropertyName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Workout {
    @PropertyName("workoutType")
    private String workoutType;

    @PropertyName("exercises")
    private Map<String, Exercise> exercises;

    public Workout(String workoutTypeText, List<Exercise> exercises) {
        this.exercises = new HashMap<>();
        this.workoutType = workoutTypeText;
        for (int i = 0; i < exercises.size(); i++){
            this.exercises.put(String.valueOf(i), exercises.get(i));
        }
    }

    public Workout(String workoutType) {
        this.workoutType = workoutType;
        exercises = new HashMap<>();
    }

    public String getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(String workoutType) {
        this.workoutType = workoutType;
    }

    public Map<String, Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Map<String, Exercise> exercises) {
        this.exercises = exercises;
    }

    public void addExercise(String exerciseId, Exercise exercise) {
        exercises.put(exerciseId, exercise);
    }
}

