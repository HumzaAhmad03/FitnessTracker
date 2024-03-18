package com.example.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class PushWorkout extends AppCompatActivity {
    EditText benchW1, benchW2, benchW3, benchR1, benchR2, benchR3;
    EditText inclineW1, inclineW2, inclineW3, inclineR1, inclineR2, inclineR3;
    EditText tricepW1, tricepW2, tricepW3, tricepR1, tricepR2, tricepR3;
    Button save_workout, cancel_workout;

//    TextInputEditText BenchPress_Set1_Weight, BenchPress_Set2_Weight, BenchPress_Set3_Weight, BenchPress_Set1_Reps, BenchPress_Set2_Reps, BenchPress_Set3_Reps;
//    TextInputEditText InclineBenchPress_Set1_Weight, InclineBenchPress_Set2_Weight, InclineBenchPress_Set3_Weight, InclineBenchPress_Set1_Reps, InclineBenchPress_Set2_Reps, InclineBenchPress_Set3_Reps;
//    TextInputEditText tricepExtension_Set1_Weight, tricepExtension_Set2_Weight, tricepExtension_Set3_Weight, tricepExtension_Set1_Reps, tricepExtension_Set2_Reps, tricepExtension_Set3_Reps;
//    Button save_workout_btn, cancel_workout_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_workout);
        save_workout = findViewById(R.id.save_workout_btn);
        cancel_workout = findViewById(R.id.cancel_workout_btn);

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

        cancel_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PushWorkout.this, "Cancelling workout test test test", Toast.LENGTH_SHORT).show();
            }
        });
        save_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PushWorkout.this, "Saving workout test test test", Toast.LENGTH_SHORT).show();
            }
        });
    }
}