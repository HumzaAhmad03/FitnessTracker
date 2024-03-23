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

public class LegWorkout extends AppCompatActivity {
    EditText squatW1, squatW2, squatW3, squatR1, squatR2, squatR3;
    EditText extensionW1, extensionW2, extensionW3, extensionR1, extensionR2, extensionR3;
    EditText calfW1, calfW2, calfW3, calfR1, calfR2, calfR3;
    Button save_workout, cancel_workout;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference databaseUsers;
    TextView workoutType, exercise1, exercise2, exercise3;
    int squatBestSetWeight, extensionBestSetWeight, calfBestSetWeight;
    int squatBestSetRep, extensionBestSetRep, calfBestSetRep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leg_workout);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        save_workout = findViewById(R.id.save_workout_btn_leg);
        cancel_workout = findViewById(R.id.cancel_workout_btn_leg);

        workoutType = findViewById(R.id.leg_workout_title);

        exercise1 = findViewById(R.id.leg_workout_1);
        exercise2 = findViewById(R.id.leg_workout_2);
        exercise3 = findViewById(R.id.leg_workout_3);

        squatW1 = findViewById(R.id.Squat_Set1_Weight);
        squatW2 = findViewById(R.id.Squat_Set2_Weight);
        squatW3 = findViewById(R.id.Squat_Set3_Weight);
        squatR1 = findViewById(R.id.Squat_Set1_Reps);
        squatR2 = findViewById(R.id.Squat_Set2_Reps);
        squatR3 = findViewById(R.id.Squat_Set3_Reps);

        extensionW1 = findViewById(R.id.Extension_Set1_Weight);
        extensionW2 = findViewById(R.id.Extension_Set2_Weight);
        extensionW3 = findViewById(R.id.Extension_Set3_Weight);
        extensionR1 = findViewById(R.id.Extension_Set1_Reps);
        extensionR2 = findViewById(R.id.Extension_Set2_Reps);
        extensionR3 = findViewById(R.id.Extension_Set3_Reps);

        calfW1 = findViewById(R.id.Calf_Set1_Weight);
        calfW2 = findViewById(R.id.Calf_Set2_Weight);
        calfW3 = findViewById(R.id.Calf_Set3_Weight);
        calfR1 = findViewById(R.id.Calf_Set1_Reps);
        calfR2 = findViewById(R.id.Calf_Set2_Reps);
        calfR3 = findViewById(R.id.Calf_Set3_Reps);

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
                    Toast.makeText(LegWorkout.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(LegWorkout.this, "Saving workout...", Toast.LENGTH_SHORT).show();
                    int maxWeightSquat = findMaxWeight(new EditText[]{squatW1, squatW2, squatW3});
                    int[] latReps = new int[]{
                            Integer.parseInt(squatR1.getText().toString()),
                            Integer.parseInt(squatR2.getText().toString()),
                            Integer.parseInt(squatR3.getText().toString())
                    };
                    int maxWeightSquatPos = findMaxWeightPosition(new EditText[]{squatW1, squatW2, squatW3});
                    squatBestSetWeight = maxWeightSquat;
                    squatBestSetRep = latReps[maxWeightSquatPos];

                    int maxWeightExtension = findMaxWeight(new EditText[]{extensionW1, extensionW2, extensionW3});
                    int[] extensionReps = new int[]{
                            Integer.parseInt(extensionR1.getText().toString()),
                            Integer.parseInt(extensionR2.getText().toString()),
                            Integer.parseInt(extensionR3.getText().toString())
                    };
                    int maxWeightExtensionPos = findMaxWeightPosition(new EditText[]{extensionW1, extensionW2, extensionW3});
                    extensionBestSetWeight = maxWeightExtension;
                    extensionBestSetRep = extensionReps[maxWeightExtensionPos];

                    int maxWeightCalf = findMaxWeight(new EditText[]{calfW1, calfW2, calfW3});
                    int[] calfReps = new int[]{
                            Integer.parseInt(calfR1.getText().toString()),
                            Integer.parseInt(calfR2.getText().toString()),
                            Integer.parseInt(calfR3.getText().toString())
                    };
                    int maxWeightCalfPos = findMaxWeightPosition(new EditText[]{calfW1, calfW2, calfW3});
                    calfBestSetWeight = maxWeightCalf;
                    calfBestSetRep = calfReps[maxWeightCalfPos];
                    //Toast.makeText(PushWorkout.this, "max bench"+benchPB, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(PushWorkout.this, "max bench reps"+benchPBRep, Toast.LENGTH_SHORT).show();
                    Toast.makeText(LegWorkout.this, "Saving workout...", Toast.LENGTH_SHORT).show();
                    System.out.println("Bench best set weight = " + squatBestSetWeight);
                    System.out.println("Bench best set reps = " + squatBestSetRep);
                    System.out.println("Incline best set weight = " + extensionBestSetWeight);
                    System.out.println("Incline best set reps = " + extensionBestSetRep);
                    System.out.println("Tricep best set weight = " + calfBestSetWeight);
                    System.out.println("Tricep best set reps = " + calfBestSetRep);
                    InsertData();
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(LegWorkout.this, "Workout not saved", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(LegWorkout.this, "Cancelling workout...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(LegWorkout.this, "Workout not cancelled", Toast.LENGTH_SHORT).show();
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
            exercise1.addSet("set1", new Set(Integer.parseInt(squatR1.getText().toString()), Integer.parseInt(squatW1.getText().toString())));
            exercise1.addSet("set2", new Set(Integer.parseInt(squatR2.getText().toString()), Integer.parseInt(squatW2.getText().toString())));
            exercise1.addSet("set3", new Set(Integer.parseInt(squatR3.getText().toString()), Integer.parseInt(squatW3.getText().toString())));
            exercises.put(exercise1.getName(),exercise1);

            // Add exercise 2
            Exercise exercise2 = new Exercise(this.exercise2.getText().toString());
            exercise2.addSet("set1", new Set(Integer.parseInt(extensionR1.getText().toString()), Integer.parseInt(extensionW1.getText().toString())));
            exercise2.addSet("set2", new Set(Integer.parseInt(extensionR2.getText().toString()), Integer.parseInt(extensionW2.getText().toString())));
            exercise2.addSet("set3", new Set(Integer.parseInt(extensionR3.getText().toString()), Integer.parseInt(extensionW3.getText().toString())));
            exercises.put(exercise2.getName(), exercise2);

            // Add exercise 3
            Exercise exercise3 = new Exercise(this.exercise3.getText().toString());
            exercise3.addSet("set1", new Set(Integer.parseInt(calfR1.getText().toString()), Integer.parseInt(calfW1.getText().toString())));
            exercise3.addSet("set2", new Set(Integer.parseInt(calfR2.getText().toString()), Integer.parseInt(calfW2.getText().toString())));
            exercise3.addSet("set3", new Set(Integer.parseInt(calfR3.getText().toString()), Integer.parseInt(calfW3.getText().toString())));
            exercises.put(exercise3.getName(),exercise3);

            // Create a new Workout object with the list of exercises
            Workout workout = new Workout(workoutTypeText, exercises);
            // Push workout data to Firebase
            //databaseUsers.child("users").child(user.getUid()).child("workouts").push().setValue(workout);
            DatabaseReference workoutReference = databaseUsers.child("users").child(user.getUid()).child("workouts").push();
            workoutReference.setValue(workout);

            ExerciseBestSet squatBestSet = new ExerciseBestSet(squatBestSetWeight,squatBestSetRep);
            ExerciseBestSet extensionBestSet = new ExerciseBestSet(extensionBestSetWeight, extensionBestSetRep);
            ExerciseBestSet calfBestSet = new ExerciseBestSet(calfBestSetWeight,calfBestSetRep);

            workoutReference.child("Best set Squats").setValue(squatBestSet);
            workoutReference.child("Best set Leg Extension").setValue(extensionBestSet);
            workoutReference.child("Best set Calf Raise").setValue(calfBestSet);

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
        return isEmpty(squatW1) || isEmpty(squatW2) || isEmpty(squatW3) ||
                isEmpty(squatR1) || isEmpty(squatR2) || isEmpty(squatR3) ||
                isEmpty(extensionW1) || isEmpty(extensionW2) || isEmpty(extensionW3) ||
                isEmpty(extensionR1) || isEmpty(extensionR2) || isEmpty(extensionR3) ||
                isEmpty(calfW1) || isEmpty(calfW2) || isEmpty(calfW3) ||
                isEmpty(calfR1) || isEmpty(calfR2) || isEmpty(calfR3);
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