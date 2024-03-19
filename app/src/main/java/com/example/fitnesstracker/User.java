package com.example.fitnesstracker;
//import com.google.firebase.database.IgnoreExtraProperties;
//
//@IgnoreExtraProperties
//public class User {
//    private String email;
//    private Workout[] workouts;
//
//    public User() {
//        // Default constructor required for Firebase
//    }
//
//    public User(String email, Workout[] workouts) {
//        this.email = email;
//        this.workouts = workouts;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public Workout[] getWorkouts() {
//        return workouts;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setWorkouts(Workout[] workouts) {
//        this.workouts = workouts;
//    }
//}

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    private String email;
    private String workoutType;
    private String exerciseType;
    private String set1Weight;
    private String set2Weight;
    private String set3Weight;
    private String set1Reps;
    private String set2Reps;
    private String set3Reps;
    //private int weightPB;
    //private int repsPB;
    //private int uniqueWorkoutID;

    public User() {
    }

    public User(String set1Weight, String set1Reps) {
    //public User(String email, String workoutType, String exerciseType, String set1Weight, String set2Weight, String set3Weight, String set1Reps, String set2Reps, String set3Reps, int uniqueWorkoutID) {
//        this.email = email;
//        this.workoutType = workoutType;
//        this.exerciseType = exerciseType;
        this.set1Weight = set1Weight;
//        this.set2Weight = set2Weight;
//        this.set3Weight = set3Weight;
        this.set1Reps = set1Reps;
//        this.set2Reps = set2Reps;
//        this.set3Reps = set3Reps;
        //this.weightPB = weightPB;
        //this.repsPB = repsPB;
//        this.uniqueWorkoutID = uniqueWorkoutID;
    }

    public String getEmail() {
        return email;
    }

    public String getWorkoutType() {
        return workoutType;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public String getSet1Weight() {
        return set1Weight;
    }

    public String getSet2Weight() {
        return set2Weight;
    }

    public String getSet3Weight() {
        return set3Weight;
    }

    public String getSet1Reps() {
        return set1Reps;
    }

    public String getSet2Reps() {
        return set2Reps;
    }

    public String getSet3Reps() {
        return set3Reps;
    }

//    public int getWeightPB() {
//        return weightPB;
//    }
//
//    public int getRepsPB() {
//        return repsPB;
//    }
//    public int getUniqueWorkoutID() {
//        return uniqueWorkoutID;
//    }
}
