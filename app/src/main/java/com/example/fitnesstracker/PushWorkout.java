package com.example.fitnesstracker;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class PushWorkout extends AppCompatActivity {
    EditText benchW1, benchW2, benchW3, benchR1, benchR2, benchR3;
    EditText inclineW1, inclineW2, inclineW3, inclineR1, inclineR2, inclineR3;
    EditText tricepW1, tricepW2, tricepW3, tricepR1, tricepR2, tricepR3;
    Button save_workout, cancel_workout;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference databaseUsers;
    TextView workoutType, exercise1, exercise2, exercise3;
    int benchPB, inclinePB, tricepPB;
    int benchPBRep, inclinePBRep, tricepPBRep;

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

        inclineW1 = findViewById(R.id.InclineBenchPress_Set1_Weight);
        inclineW2 = findViewById(R.id.InclineBenchPress_Set2_Weight);
        inclineW3 = findViewById(R.id.InclineBenchPress_Set3_Weight);
        inclineR1 = findViewById(R.id.InclineBenchPress_Set1_Reps);
        inclineR2 = findViewById(R.id.InclineBenchPress_Set2_Reps);
        inclineR3 = findViewById(R.id.InclineBenchPress_Set3_Reps);

        tricepW1 = findViewById(R.id.tricepExtension_Set1_Weight);
        tricepW2 = findViewById(R.id.tricepExtension_Set2_Weight);
        tricepW3 = findViewById(R.id.tricepExtension_Set3_Weight);
        tricepR1 = findViewById(R.id.tricepExtension_Set1_Reps);
        tricepR2 = findViewById(R.id.tricepExtension_Set2_Reps);
        tricepR3 = findViewById(R.id.tricepExtension_Set3_Reps);

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
                    System.out.println("emptyyy");
                }
                else {
                    System.out.println("full");
// Parse EditText values and find maximum weight for bench press
                    int maxWeightBench = findMaxWeight(new EditText[]{benchW1, benchW2, benchW3});
                    int[] benchReps = new int[]{
                            Integer.parseInt(benchR1.getText().toString()),
                            Integer.parseInt(benchR2.getText().toString()),
                            Integer.parseInt(benchR3.getText().toString())
                    };
                    int maxWeightBenchPos = findMaxWeightPosition(new EditText[]{benchW1, benchW2, benchW3});
                    benchPB = maxWeightBench;
                    benchPBRep = benchReps[maxWeightBenchPos];

                    int maxWeightIncline = findMaxWeight(new EditText[]{inclineW1, inclineW2, inclineW3});
                    int[] inclineReps = new int[]{
                            Integer.parseInt(inclineR1.getText().toString()),
                            Integer.parseInt(inclineR2.getText().toString()),
                            Integer.parseInt(inclineR3.getText().toString())
                    };
                    int maxWeightInclinePos = findMaxWeightPosition(new EditText[]{inclineW1, inclineW2, inclineW3});
                    inclinePB = maxWeightIncline;
                    inclinePBRep = inclineReps[maxWeightInclinePos];

                    int maxWeightTricep = findMaxWeight(new EditText[]{tricepW1, tricepW2, tricepW3});
                    int[] tricepReps = new int[]{
                            Integer.parseInt(tricepR1.getText().toString()),
                            Integer.parseInt(tricepR2.getText().toString()),
                            Integer.parseInt(tricepR3.getText().toString())
                    };
                    int maxWeightTricepPos = findMaxWeightPosition(new EditText[]{tricepW1, tricepW2, tricepW3});
                    tricepPB = maxWeightTricep;
                    tricepPBRep = tricepReps[maxWeightTricepPos];
                    //Toast.makeText(PushWorkout.this, "max bench"+benchPB, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(PushWorkout.this, "max bench reps"+benchPBRep, Toast.LENGTH_SHORT).show();
                    Toast.makeText(PushWorkout.this, "Saving workout...", Toast.LENGTH_SHORT).show();
                    System.out.println("Bench best set weight = " + benchPB);
                    System.out.println("Bench best set reps = " + benchPBRep);
                    System.out.println("Incline best set weight = " + inclinePB);
                    System.out.println("Incline best set reps = " + inclinePBRep);
                    System.out.println("Tricep best set weight = " + tricepPB);
                    System.out.println("Tricep best set reps = " + tricepPBRep);
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
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
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
        try {
            String workoutTypeText = workoutType.getText().toString();

            // Create a list of exercises
            HashMap<String, Exercise> exercises = new HashMap<>();

            // Add exercise 1
            Exercise exercise1 = new Exercise(this.exercise1.getText().toString());
            exercise1.addSet("set1", new Set(Integer.parseInt(benchR1.getText().toString()), Integer.parseInt(benchW1.getText().toString())));
            exercise1.addSet("set2", new Set(Integer.parseInt(benchR2.getText().toString()), Integer.parseInt(benchW2.getText().toString())));
            exercise1.addSet("set3", new Set(Integer.parseInt(benchR3.getText().toString()), Integer.parseInt(benchW3.getText().toString())));
            exercises.put(exercise1.getName(),exercise1);

            // Add exercise 2
            Exercise exercise2 = new Exercise(this.exercise2.getText().toString());
            exercise2.addSet("set1", new Set(Integer.parseInt(inclineR1.getText().toString()), Integer.parseInt(inclineW1.getText().toString())));
            exercise2.addSet("set2", new Set(Integer.parseInt(inclineR2.getText().toString()), Integer.parseInt(inclineW2.getText().toString())));
            exercise2.addSet("set3", new Set(Integer.parseInt(inclineR3.getText().toString()), Integer.parseInt(inclineW3.getText().toString())));
            exercises.put(exercise2.getName(), exercise2);

            // Add exercise 3
            Exercise exercise3 = new Exercise(this.exercise3.getText().toString());
            exercise3.addSet("set1", new Set(Integer.parseInt(tricepR1.getText().toString()), Integer.parseInt(tricepW1.getText().toString())));
            exercise3.addSet("set2", new Set(Integer.parseInt(tricepR2.getText().toString()), Integer.parseInt(tricepW2.getText().toString())));
            exercise3.addSet("set3", new Set(Integer.parseInt(tricepR3.getText().toString()), Integer.parseInt(tricepW3.getText().toString())));
            exercises.put(exercise3.getName(),exercise3);

            // Create a new Workout object with the list of exercises
            Workout workout = new Workout(workoutTypeText, exercises);
            // Push workout data to Firebase
            databaseUsers.child("users").child(user.getUid()).child("workouts").push().setValue(workout);

            Toast.makeText(this, "Workout saved successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            Toast.makeText(this, "Failed to save workout: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
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

    private int findMaxWeight(EditText[] editTexts) {
        int maxWeight = Integer.MIN_VALUE;
        for (EditText editText : editTexts) {
            int weight = Integer.parseInt(editText.getText().toString());
            if (weight > maxWeight) {
                maxWeight = weight;
            }
        }
        return maxWeight;
    }

    private int findMaxWeightPosition(EditText[] editTexts) {
        int maxWeight = Integer.MIN_VALUE;
        int maxWeightPos = -1;
        for (int i = 0; i < editTexts.length; i++) {
            int weight = Integer.parseInt(editTexts[i].getText().toString());
            if (weight > maxWeight) {
                maxWeight = weight;
                maxWeightPos = i;
            }
        }
        return maxWeightPos;
    }
}

//    int maxWeightBench = 0, maxWeightIncline = 0, maxWeightTricep = 0;
//    int maxWeightBenchPos = 0, maxWeightInclinePos = 0, maxWeightTricepPos = 0;
//    int benchPB, inclinePB, tricepPB;
//    int benchPBRep, inclinePBRep, tricepPBRep;

// Parse EditText values and find maximum weight for bench press
//                    int maxWeightBench = findMaxWeight(new EditText[]{benchW1, benchW2, benchW3});
//                    int[] benchReps = new int[]{
//                            Integer.parseInt(benchR1.getText().toString()),
//                            Integer.parseInt(benchR2.getText().toString()),
//                            Integer.parseInt(benchR3.getText().toString())
//                    };
//                    int maxWeightBenchPos = findMaxWeightPosition(new EditText[]{benchW1, benchW2, benchW3});
//                    benchPB = maxWeightBench;
//                    benchPBRep = benchReps[maxWeightBenchPos];

//    private int findMaxWeight(EditText[] editTexts) {
//        int maxWeight = Integer.MIN_VALUE;
//        for (EditText editText : editTexts) {
//            int weight = Integer.parseInt(editText.getText().toString());
//            if (weight > maxWeight) {
//                maxWeight = weight;
//            }
//        }
//        return maxWeight;
//    }
//
//    private int findMaxWeightPosition(EditText[] editTexts) {
//        int maxWeight = Integer.MIN_VALUE;
//        int maxWeightPos = -1;
//        for (int i = 0; i < editTexts.length; i++) {
//            int weight = Integer.parseInt(editTexts[i].getText().toString());
//            if (weight > maxWeight) {
//                maxWeight = weight;
//                maxWeightPos = i;
//            }
//        }
//        return maxWeightPos;
//    }
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

//                    int[] benchWeight = new int[]{Integer.parseInt(benchW1.toString()),Integer.parseInt(benchW2.toString()), Integer.parseInt(benchW3.toString())};
//                    int[] benchReps = new int[]{Integer.parseInt(benchR1.toString()),Integer.parseInt(benchR2.toString()), Integer.parseInt(benchR3.toString())};
//                    for (int i = 0; i < benchWeight.length; i++){
//                        if (benchWeight[i] > maxWeightBench){
//                            maxWeightBench = benchWeight[i];
//                            maxWeightBenchPos = i;
//                        }
//                    }
//                    benchPB = maxWeightBench;
//                    benchPBRep = benchReps[maxWeightBenchPos];
//
//                    int[] inclineWeight = new int[]{Integer.parseInt(inclineW1.toString()),Integer.parseInt(inclineW2.toString()), Integer.parseInt(inclineW3.toString())};
//                    int[] inclineReps = new int[]{Integer.parseInt(inclineR1.toString()),Integer.parseInt(inclineR2.toString()), Integer.parseInt(inclineR3.toString())};
//                    for (int i = 0; i < inclineWeight.length; i++){
//                        if (inclineWeight[i] > maxWeightIncline){
//                            maxWeightIncline = inclineWeight[i];
//                            maxWeightInclinePos = i;
//                        }
//                    }
//                    inclinePB = maxWeightIncline;
//                    inclinePBRep = inclineReps[maxWeightInclinePos];
//
//                    int[] tricepWeight = new int[]{Integer.parseInt(tricepW1.toString()),Integer.parseInt(tricepW2.toString()), Integer.parseInt(tricepW3.toString())};
//                    int[] tricepReps = new int[]{Integer.parseInt(tricepR1.toString()),Integer.parseInt(tricepR2.toString()), Integer.parseInt(tricepR3.toString())};
//                    for (int i = 0; i < tricepWeight.length; i++){
//                        if (tricepWeight[i] > maxWeightTricep){
//                            maxWeightTricep = tricepWeight[i];
//                            maxWeightTricepPos = i;
//                        }
//                    }
//                    tricepPB = maxWeightTricep;
//                    tricepPBRep = tricepReps[maxWeightTricepPos];