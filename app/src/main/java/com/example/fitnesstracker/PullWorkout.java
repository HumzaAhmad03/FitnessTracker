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

public class PullWorkout extends AppCompatActivity {
    EditText latW1, latW2, latW3, latR1, latR2, latR3;
    EditText rowW1, rowW2, rowW3, rowR1, rowR2, rowR3;
    EditText bicepW1, bicepW2, bicepW3, bicepR1, bicepR2, bicepR3;
    Button save_workout, cancel_workout;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference databaseUsers;
    TextView workoutType, exercise1, exercise2, exercise3;
    int latBestSetWeight, rowBestSetWeight, bicepBestSetWeight;
    int latBestSetRep, rowBestSetRep, bicepBestSetRep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_workout);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        save_workout = findViewById(R.id.save_workout_btn_pull);
        cancel_workout = findViewById(R.id.cancel_workout_btn_pull);

        workoutType = findViewById(R.id.pull_workout_title);

        exercise1 = findViewById(R.id.pull_workout_1);
        exercise2 = findViewById(R.id.pull_workout_2);
        exercise3 = findViewById(R.id.pull_workout_3);

        latW1 = findViewById(R.id.Lat_Set1_Weight);
        latW2 = findViewById(R.id.Lat_Set2_Weight);
        latW3 = findViewById(R.id.Lat_Set3_Weight);
        latR1 = findViewById(R.id.Lat_Set1_Reps);
        latR2 = findViewById(R.id.Lat_Set2_Reps);
        latR3 = findViewById(R.id.Lat_Set3_Reps);

        rowW1 = findViewById(R.id.Row_Set1_Weight);
        rowW2 = findViewById(R.id.Row_Set2_Weight);
        rowW3 = findViewById(R.id.Row_Set3_Weight);
        rowR1 = findViewById(R.id.Row_Set1_Reps);
        rowR2 = findViewById(R.id.Row_Set2_Reps);
        rowR3 = findViewById(R.id.Row_Set3_Reps);

        bicepW1 = findViewById(R.id.Bicep_Set1_Weight);
        bicepW2 = findViewById(R.id.Bicep_Set2_Weight);
        bicepW3 = findViewById(R.id.Bicep_Set3_Weight);
        bicepR1 = findViewById(R.id.Bicep_Set1_Reps);
        bicepR2 = findViewById(R.id.Bicep_Set2_Reps);
        bicepR3 = findViewById(R.id.Bicep_Set3_Reps);

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
                    Toast.makeText(PullWorkout.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(PullWorkout.this, "Saving workout...", Toast.LENGTH_SHORT).show();
                    int maxWeightLat = findMaxWeight(new EditText[]{latW1, latW2, latW3});
                    int[] latReps = new int[]{
                            Integer.parseInt(latR1.getText().toString()),
                            Integer.parseInt(latR2.getText().toString()),
                            Integer.parseInt(latR3.getText().toString())
                    };
                    int maxWeightLatPos = findMaxWeightPosition(new EditText[]{latW1, latW2, latW3});
                    latBestSetWeight = maxWeightLat;
                    latBestSetRep = latReps[maxWeightLatPos];

                    int maxWeightRow = findMaxWeight(new EditText[]{rowW1, rowW2, rowW3});
                    int[] rowReps = new int[]{
                            Integer.parseInt(rowR1.getText().toString()),
                            Integer.parseInt(rowR2.getText().toString()),
                            Integer.parseInt(rowR3.getText().toString())
                    };
                    int maxWeightRowPos = findMaxWeightPosition(new EditText[]{rowW1, rowW2, rowW3});
                    rowBestSetWeight = maxWeightRow;
                    rowBestSetRep = rowReps[maxWeightRowPos];

                    int maxWeightBicep = findMaxWeight(new EditText[]{bicepW1, bicepW2, bicepW3});
                    int[] bicepReps = new int[]{
                            Integer.parseInt(bicepR1.getText().toString()),
                            Integer.parseInt(bicepR2.getText().toString()),
                            Integer.parseInt(bicepR3.getText().toString())
                    };
                    int maxWeightBicepPos = findMaxWeightPosition(new EditText[]{bicepW1, bicepW2, bicepW3});
                    bicepBestSetWeight = maxWeightBicep;
                    bicepBestSetRep = bicepReps[maxWeightBicepPos];
                    //Toast.makeText(PushWorkout.this, "max bench"+benchPB, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(PushWorkout.this, "max bench reps"+benchPBRep, Toast.LENGTH_SHORT).show();
                    Toast.makeText(PullWorkout.this, "Saving workout...", Toast.LENGTH_SHORT).show();
                    System.out.println("Bench best set weight = " + latBestSetWeight);
                    System.out.println("Bench best set reps = " + latBestSetRep);
                    System.out.println("Incline best set weight = " + rowBestSetWeight);
                    System.out.println("Incline best set reps = " + rowBestSetRep);
                    System.out.println("Tricep best set weight = " + bicepBestSetWeight);
                    System.out.println("Tricep best set reps = " + bicepBestSetRep);
                    InsertData();
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(PullWorkout.this, "Workout not saved", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(PullWorkout.this, "Cancelling workout...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(PullWorkout.this, "Workout not cancelled", Toast.LENGTH_SHORT).show();
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
            exercise1.addSet("set1", new Set(Integer.parseInt(latR1.getText().toString()), Integer.parseInt(latW1.getText().toString())));
            exercise1.addSet("set2", new Set(Integer.parseInt(latR2.getText().toString()), Integer.parseInt(latW2.getText().toString())));
            exercise1.addSet("set3", new Set(Integer.parseInt(latR3.getText().toString()), Integer.parseInt(latW3.getText().toString())));
            exercises.put(exercise1.getName(),exercise1);

            // Add exercise 2
            Exercise exercise2 = new Exercise(this.exercise2.getText().toString());
            exercise2.addSet("set1", new Set(Integer.parseInt(rowR1.getText().toString()), Integer.parseInt(rowW1.getText().toString())));
            exercise2.addSet("set2", new Set(Integer.parseInt(rowR2.getText().toString()), Integer.parseInt(rowW2.getText().toString())));
            exercise2.addSet("set3", new Set(Integer.parseInt(rowR3.getText().toString()), Integer.parseInt(rowW3.getText().toString())));
            exercises.put(exercise2.getName(), exercise2);

            // Add exercise 3
            Exercise exercise3 = new Exercise(this.exercise3.getText().toString());
            exercise3.addSet("set1", new Set(Integer.parseInt(bicepR1.getText().toString()), Integer.parseInt(bicepW1.getText().toString())));
            exercise3.addSet("set2", new Set(Integer.parseInt(bicepR2.getText().toString()), Integer.parseInt(bicepW2.getText().toString())));
            exercise3.addSet("set3", new Set(Integer.parseInt(bicepR3.getText().toString()), Integer.parseInt(bicepW3.getText().toString())));
            exercises.put(exercise3.getName(),exercise3);

            // Create a new Workout object with the list of exercises
            Workout workout = new Workout(workoutTypeText, exercises);
            // Push workout data to Firebase
            //databaseUsers.child("users").child(user.getUid()).child("workouts").push().setValue(workout);

            DatabaseReference workoutReference = databaseUsers.child("users").child(user.getUid()).child("workouts").push();
            workoutReference.setValue(workout);

            ExerciseBestSet latBestSet = new ExerciseBestSet(latBestSetWeight,latBestSetRep);
            ExerciseBestSet rowBestSet = new ExerciseBestSet(rowBestSetWeight, rowBestSetRep);
            ExerciseBestSet bicepBestSet = new ExerciseBestSet(bicepBestSetWeight,bicepBestSetRep);

            workoutReference.child("Best set Lat Pulldown").setValue(latBestSet);
            workoutReference.child("Best set Seated Row").setValue(rowBestSet);
            workoutReference.child("Best set Bicep Curl").setValue(bicepBestSet);

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
        return isEmpty(latW1) || isEmpty(latW2) || isEmpty(latW3) ||
                isEmpty(latR1) || isEmpty(latR2) || isEmpty(latR3) ||
                isEmpty(rowW1) || isEmpty(rowW2) || isEmpty(rowW3) ||
                isEmpty(rowR1) || isEmpty(rowR2) || isEmpty(rowR3) ||
                isEmpty(bicepW1) || isEmpty(bicepW2) || isEmpty(bicepW3) ||
                isEmpty(bicepR1) || isEmpty(bicepR2) || isEmpty(bicepR3);
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