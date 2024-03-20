package com.example.fitnesstracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class PushWorkout extends AppCompatActivity {
    EditText benchW1, benchW2, benchW3, benchR1, benchR2, benchR3;
//    int maxWeightBench = 0, maxWeightIncline = 0, maxWeightTricep = 0;
//    int maxWeightBenchPos = 0, maxWeightInclinePos = 0, maxWeightTricepPos = 0;
//    int benchPB, inclinePB, tricepPB;
//    int benchPBRep, inclinePBRep, tricepPBRep;
    EditText inclineW1, inclineW2, inclineW3, inclineR1, inclineR2, inclineR3;
    EditText tricepW1, tricepW2, tricepW3, tricepR1, tricepR2, tricepR3;
    Button save_workout, cancel_workout;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference databaseUsers;
    TextView workoutType, exercise1, exercise2, exercise3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_workout);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();


        save_workout = findViewById(R.id.save_workout_btn);
        cancel_workout = findViewById(R.id.cancel_workout_btn);

        workoutType = findViewById(R.id.push_workout_title);

        exercise1 = findViewById(R.id.push_workout_1);
        exercise2 = findViewById(R.id.push_workout_2);
        exercise3 = findViewById(R.id.push_workout_3);

        benchW1 = findViewById(R.id.BenchPress_Set1_Weight);
        benchW2 = findViewById(R.id.BenchPress_Set2_Weight);
        benchW3 = findViewById(R.id.BenchPress_Set3_Weight);
        benchR1 = findViewById(R.id.BenchPress_Set1_Reps);
        benchR2 = findViewById(R.id.BenchPress_Set2_Reps);
        benchR3 = findViewById(R.id.BenchPress_Set3_Reps);

//        int[] benchWeight = new int[]{Integer.parseInt(benchW1.toString()),Integer.parseInt(benchW2.toString()), Integer.parseInt(benchW3.toString())};
//        int[] benchReps = new int[]{Integer.parseInt(benchR1.toString()),Integer.parseInt(benchR2.toString()), Integer.parseInt(benchR3.toString())};
//        for (int i = 0; i < benchWeight.length; i++){
//            if (benchWeight[i] > maxWeightBench){
//                maxWeightBench = benchWeight[i];
//                maxWeightBenchPos = i;
//            }
//        }
//        benchPB = maxWeightBench;
//        benchPBRep = benchReps[maxWeightBenchPos];

        inclineW1 = findViewById(R.id.InclineBenchPress_Set1_Weight);
        inclineW2 = findViewById(R.id.InclineBenchPress_Set2_Weight);
        inclineW3 = findViewById(R.id.InclineBenchPress_Set3_Weight);
        inclineR1 = findViewById(R.id.InclineBenchPress_Set1_Reps);
        inclineR2 = findViewById(R.id.InclineBenchPress_Set2_Reps);
        inclineR3 = findViewById(R.id.InclineBenchPress_Set3_Reps);

//        int[] inclineWeight = new int[]{Integer.parseInt(inclineW1.toString()),Integer.parseInt(inclineW2.toString()), Integer.parseInt(inclineW3.toString())};
//        int[] inclineReps = new int[]{Integer.parseInt(inclineR1.toString()),Integer.parseInt(inclineR2.toString()), Integer.parseInt(inclineR3.toString())};
//        for (int i = 0; i < inclineWeight.length; i++){
//            if (inclineWeight[i] > maxWeightIncline){
//                maxWeightIncline = inclineWeight[i];
//                maxWeightInclinePos = i;
//            }
//        }
//        inclinePB = maxWeightIncline;
//        inclinePBRep = inclineReps[maxWeightInclinePos];

        tricepW1 = findViewById(R.id.tricepExtension_Set1_Weight);
        tricepW2 = findViewById(R.id.tricepExtension_Set2_Weight);
        tricepW3 = findViewById(R.id.tricepExtension_Set3_Weight);
        tricepR1 = findViewById(R.id.tricepExtension_Set1_Reps);
        tricepR2 = findViewById(R.id.tricepExtension_Set2_Reps);
        tricepR3 = findViewById(R.id.tricepExtension_Set3_Reps);

//        int[] tricepWeight = new int[]{Integer.parseInt(tricepW1.toString()),Integer.parseInt(tricepW2.toString()), Integer.parseInt(tricepW3.toString())};
//        int[] tricepReps = new int[]{Integer.parseInt(tricepR1.toString()),Integer.parseInt(tricepR2.toString()), Integer.parseInt(tricepR3.toString())};
//        for (int i = 0; i < tricepWeight.length; i++){
//            if (tricepWeight[i] > maxWeightTricep){
//                maxWeightTricep = tricepWeight[i];
//                maxWeightTricepPos = i;
//            }
//        }
//        tricepPB = maxWeightTricep;
//        tricepPBRep = tricepReps[maxWeightTricepPos];

        databaseUsers = FirebaseDatabase.getInstance().getReference();

        cancel_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationCancelDialog();
                //Toast.makeText(PushWorkout.this, "Cancelling workout test test test", Toast.LENGTH_SHORT).show();
            }
        });
        save_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(PushWorkout.this, "Saving workout test test test", Toast.LENGTH_SHORT).show();
                showConfirmationDialog();
                //InsertData();
                //generate uniqueID for workout
                // i check to see if it exists if it does make new unique while loop

                // i push to the database
            }
        });
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to save this workout?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (areFieldsEmpty()){
                    Toast.makeText(PushWorkout.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(PushWorkout.this, "Saving workout...", Toast.LENGTH_SHORT).show();
                    InsertData();
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(PushWorkout.this, "Workout not saved", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    private void showConfirmationCancelDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Cancel this workout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(PushWorkout.this, "Cancelling workout...", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(PushWorkout.this, "Workout not cancelled", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }
    private void InsertData() {
//        String benchPBWeight = String.valueOf(benchPB);
//        String benchPBReps = String.valueOf(benchPBRep);
//        String inclinePBWeight = String.valueOf(inclinePB);
//        String inclinePBReps = String.valueOf(inclinePBRep);
//        String tricepPBWeight = String.valueOf(tricepPB);
//        String tricepPBReps = String.valueOf(tricepPBRep);
//        String id = databaseUsers.push().getKey();
//
//        PushPB pushPB = new PushPB(benchPBWeight,benchPBReps,inclinePBWeight,inclinePBReps,tricepPBWeight,tricepPBReps);
//        databaseUsers.child("userPB").child(id).setValue(pushPB);
//        String id = databaseUsers.push().getKey();
//        String set1Weight = benchW1.getText().toString();
//        String set1Reps = benchR1.getText().toString();
//        User user = new User(set1Weight, set1Reps);
//
//        databaseUsers.child("users").child(id).setValue(user)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful()){
//                            Toast.makeText(PushWorkout.this, "user details entered", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
        try {
            String workoutTypeText = workoutType.getText().toString();

            // Create a list of exercises
            List<Exercise> exercises = new ArrayList<>();

            // Add exercise 1
            Exercise exercise1 = new Exercise(this.exercise1.getText().toString());
            exercise1.addSet("set1", new Set(Integer.parseInt(benchR1.getText().toString()), Integer.parseInt(benchW1.getText().toString())));
            exercise1.addSet("set2", new Set(Integer.parseInt(benchR2.getText().toString()), Integer.parseInt(benchW2.getText().toString())));
            exercise1.addSet("set3", new Set(Integer.parseInt(benchR3.getText().toString()), Integer.parseInt(benchW3.getText().toString())));
            exercises.add(exercise1);

            // Add exercise 2
            Exercise exercise2 = new Exercise(this.exercise2.getText().toString());
            exercise2.addSet("set1", new Set(Integer.parseInt(inclineR1.getText().toString()), Integer.parseInt(inclineW1.getText().toString())));
            exercise2.addSet("set2", new Set(Integer.parseInt(inclineR2.getText().toString()), Integer.parseInt(inclineW2.getText().toString())));
            exercise2.addSet("set3", new Set(Integer.parseInt(inclineR3.getText().toString()), Integer.parseInt(inclineW3.getText().toString())));
            exercises.add(exercise2);

            // Add exercise 3
            Exercise exercise3 = new Exercise(this.exercise3.getText().toString());
            exercise3.addSet("set1", new Set(Integer.parseInt(tricepR1.getText().toString()), Integer.parseInt(tricepW1.getText().toString())));
            exercise3.addSet("set2", new Set(Integer.parseInt(tricepR2.getText().toString()), Integer.parseInt(tricepW2.getText().toString())));
            exercise3.addSet("set3", new Set(Integer.parseInt(tricepR3.getText().toString()), Integer.parseInt(tricepW3.getText().toString())));
            exercises.add(exercise3);



            // Create a new Workout object with the list of exercises
            Workout workout = new Workout(workoutTypeText, exercises);
            // Push workout data to Firebase
            databaseUsers.child("users").child(user.getUid()).child("workouts").push().setValue(workout);
            ;

            Toast.makeText(this, "Workout saved successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            Toast.makeText(this, "Failed to save workout: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


//    private void InsertData() {
//        try {
//            String workoutTypeText = workoutType.getText().toString();
//            String exercise1Text = exercise1.getText().toString();
//            String exercise2Text = exercise2.getText().toString();
//            String exercise3Text = exercise3.getText().toString();
//            int benchW1Value = Integer.parseInt(benchW1.getText().toString());
//            int benchW2Value = Integer.parseInt(benchW2.getText().toString());
//            int benchW3Value = Integer.parseInt(benchW3.getText().toString());
//            int benchR1Value = Integer.parseInt(benchR1.getText().toString());
//            int benchR2Value = Integer.parseInt(benchR2.getText().toString());
//            int benchR3Value = Integer.parseInt(benchR3.getText().toString());
//            int inclineW1Value = Integer.parseInt(inclineW1.getText().toString());
//            int inclineW2Value = Integer.parseInt(inclineW2.getText().toString());
//            int inclineW3Value = Integer.parseInt(inclineW3.getText().toString());
//            int inclineR1Value = Integer.parseInt(inclineR1.getText().toString());
//            int inclineR2Value = Integer.parseInt(inclineR2.getText().toString());
//            int inclineR3Value = Integer.parseInt(inclineR3.getText().toString());
//            int tricepW1Value = Integer.parseInt(tricepW1.getText().toString());
//            int tricepW2Value = Integer.parseInt(tricepW2.getText().toString());
//            int tricepW3Value = Integer.parseInt(tricepW3.getText().toString());
//            int tricepR1Value = Integer.parseInt(tricepR1.getText().toString());
//            int tricepR2Value = Integer.parseInt(tricepR2.getText().toString());
//            int tricepR3Value = Integer.parseInt(tricepR3.getText().toString());
//
//            Workout workout = new Workout(workoutTypeText, new Exercise[]{
//                    new Exercise(exercise1Text, new Set[]{new Set(benchR1Value, benchW1Value), new Set(benchR2Value, benchW2Value), new Set(benchR3Value, benchW3Value)}),
//                    new Exercise(exercise2Text, new Set[]{new Set(inclineR1Value, inclineW1Value), new Set(inclineR2Value, inclineW2Value), new Set(inclineR3Value, inclineW3Value)}),
//                    new Exercise(exercise3Text, new Set[]{new Set(tricepR1Value, tricepW1Value), new Set(tricepR2Value, tricepW2Value), new Set(tricepR3Value, tricepW3Value)})
//            });
//
//            // Push workout data to Firebase
//            databaseUsers.child("users").child(user.getUid()).child("workouts").push().setValue(workout);
//            Toast.makeText(this, "Workout saved successfully", Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            Toast.makeText(this, "Failed to save workout: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    }
    private boolean areFieldsEmpty() {
    // Check if any of the EditText fields are empty
    return isEmpty(benchW1) || isEmpty(benchW2) || isEmpty(benchW3) ||
            isEmpty(benchR1) || isEmpty(benchR2) || isEmpty(benchR3) ||
            isEmpty(inclineW1) || isEmpty(inclineW2) || isEmpty(inclineW3) ||
            isEmpty(inclineR1) || isEmpty(inclineR2) || isEmpty(inclineR3) ||
            isEmpty(tricepW1) || isEmpty(tricepW2) || isEmpty(tricepW3) ||
            isEmpty(tricepR1) || isEmpty(tricepR2) || isEmpty(tricepR3);
    }
    private boolean isEmpty(EditText field){
        return field.getText().toString().isEmpty();
    }
}