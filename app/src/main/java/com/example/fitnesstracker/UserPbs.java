package com.example.fitnesstracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class UserPbs extends AppCompatActivity {

    TextView benchPb,inclinePb,tricepPb;
    TextView benchPbReps,inclinePbReps,tricepPbReps;
    TextView latPb,rowPb,bicepPb;
    TextView latPbReps,rowPbReps,bicepPbReps;
    TextView squatPb,extensionPb,calfPb;
    TextView squatPbReps,extensionPbReps,calfPbReps;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(UserPbs.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpbs);

        auth = FirebaseAuth.getInstance();
        benchPb = findViewById(R.id.textbenchpb);
        inclinePb = findViewById(R.id.textinclinebenchpb);
        tricepPb = findViewById(R.id.texttriceppb);
        benchPbReps = findViewById(R.id.textbenchpbreps);
        inclinePbReps = findViewById(R.id.textinclinebenchpbreps);
        tricepPbReps = findViewById(R.id.texttriceppbreps);

        latPb = findViewById(R.id.textlatpb);
        rowPb = findViewById(R.id.textrowpb);
        bicepPb = findViewById(R.id.textbiceppb);
        latPbReps = findViewById(R.id.textlatpbreps);
        rowPbReps = findViewById(R.id.textrowpbreps);
        bicepPbReps = findViewById(R.id.textbiceppbreps);

        squatPb = findViewById(R.id.textsquatpb);
        extensionPb = findViewById(R.id.textextensionpb);
        calfPb = findViewById(R.id.textcalfpb);
        squatPbReps = findViewById(R.id.textsquatpbreps);
        extensionPbReps = findViewById(R.id.textextensionpbreps);
        calfPbReps = findViewById(R.id.textcalfpbreps);

        user = auth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(user.getUid()).child("workouts");

//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                HashMap<String, Integer> personalBests = new HashMap<>();
//                HashMap<String, Integer> personalBestReps = new HashMap<>();
//
//                // Retrieve all workouts
//                for (DataSnapshot workoutSnapshot : snapshot.getChildren()) {
//                    // Iterate through exercises in each workout
//                    for (DataSnapshot exerciseSnapshot : workoutSnapshot.child("exercises").getChildren()) {
//                        String exerciseName = exerciseSnapshot.child("name").getValue(String.class);
//                        if (exerciseName != null) {
//                            // Iterate through all sets of the exercise
//                            for (DataSnapshot setSnapshot : exerciseSnapshot.child("sets").getChildren()) {
//                                // Retrieve set weight and reps
//                                Integer setWeight = setSnapshot.child("weight").getValue(Integer.class);
//                                Integer setReps = setSnapshot.child("reps").getValue(Integer.class);
//                                if (setWeight != null && setReps != null) {
//                                    // Check if a personal best exists for this exercise
//                                    if (personalBests.containsKey(exerciseName)) {
//                                        // Update personal best if necessary
//                                        int currentBest = personalBests.get(exerciseName);
//                                        if (setWeight > currentBest) {
//                                            personalBests.put(exerciseName, setWeight);
//                                            personalBestReps.put(exerciseName, setReps);
//                                        }
//                                    } else {
//                                        // First personal best for this exercise
//                                        personalBests.put(exerciseName, setWeight);
//                                        personalBestReps.put(exerciseName, setReps);
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//
//                if (personalBests.containsKey("Bench Press")) {
//                    String weightTemplate = getString(R.string.weight_and_reps_template);
//                    benchPb.setText(String.format("%s %s ", personalBests.get("Bench Press"), weightTemplate));
//                    benchPbReps.setText(String.valueOf(personalBestReps.get("Bench Press")));
//                }
//                if (personalBests.containsKey("Incline Bench Press")) {
//                    String weightTemplate = getString(R.string.weight_and_reps_template);
//                    inclinePb.setText(String.format("%s %s ", personalBests.get("Incline Bench Press"), weightTemplate));
//                    inclinePbReps.setText(String.valueOf(personalBestReps.get("Incline Bench Press")));
//                }
//                if (personalBests.containsKey("Tricep Extension")) {
//                    String weightTemplate = getString(R.string.weight_and_reps_template);
//                    tricepPb.setText(String.format("%s %s ", personalBests.get("Tricep Extension"), weightTemplate));
//                    tricepPbReps.setText(String.valueOf(personalBestReps.get("Tricep Extension")));
//                }
//                if (personalBests.containsKey("Lat Pulldown")) {
//                    String weightTemplate = getString(R.string.weight_and_reps_template);
//                    latPb.setText(String.format("%s %s ", personalBests.get("Lat Pulldown"), weightTemplate));
//                    latPbReps.setText(String.valueOf(personalBestReps.get("Lat Pulldown")));
//                }
//                if (personalBests.containsKey("Seated Row")) {
//                    String weightTemplate = getString(R.string.weight_and_reps_template);
//                    rowPb.setText(String.format("%s %s ", personalBests.get("Seated Row"), weightTemplate));
//                    rowPbReps.setText(String.valueOf(personalBestReps.get("Seated Row")));
//                }
//                if (personalBests.containsKey("Bicep Curl")) {
//                    String weightTemplate = getString(R.string.weight_and_reps_template);
//                    bicepPb.setText(String.format("%s %s ", personalBests.get("Bicep Curl"), weightTemplate));
//                    bicepPbReps.setText(String.valueOf(personalBestReps.get("Bicep Curl")));
//                }
//                if (personalBests.containsKey("Squats")) {
//                    String weightTemplate = getString(R.string.weight_and_reps_template);
//                    squatPb.setText(String.format("%s %s ", personalBests.get("Squats"), weightTemplate));
//                    squatPbReps.setText(String.valueOf(personalBestReps.get("Squats")));
//                }
//                if (personalBests.containsKey("Leg Extensions")) {
//                    String weightTemplate = getString(R.string.weight_and_reps_template);
//                    extensionPb.setText(String.format("%s %s ", personalBests.get("Leg Extensions"), weightTemplate));
//                    extensionPbReps.setText(String.valueOf(personalBestReps.get("Leg Extensions")));
//                }
//                if (personalBests.containsKey("Calf Raises")) {
//                    String weightTemplate = getString(R.string.weight_and_reps_template);
//                    calfPb.setText(String.format("%s %s ", personalBests.get("Calf Raises"), weightTemplate));
//                    calfPbReps.setText(String.valueOf(personalBestReps.get("Calf Raises")));
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // Handle database error
//            }
//        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, Integer> personalBests = new HashMap<>();
                HashMap<String, Integer> personalBestReps = new HashMap<>();

                // Retrieve all workouts
                for (DataSnapshot workoutSnapshot : snapshot.getChildren()) {
                    // Iterate through exercises in each workout
                    for (DataSnapshot exerciseSnapshot : workoutSnapshot.child("exercises").getChildren()) {
                        String exerciseName = exerciseSnapshot.child("name").getValue(String.class);
                        if (exerciseName != null) {
                            // Iterate through all sets of the exercise
                            for (DataSnapshot setSnapshot : exerciseSnapshot.child("sets").getChildren()) {
                                // Retrieve set weight and reps
                                Integer setWeight = setSnapshot.child("weight").getValue(Integer.class);
                                Integer setReps = setSnapshot.child("reps").getValue(Integer.class);
                                if (setWeight != null && setReps != null) {
                                    // Check if a personal best exists for this exercise
                                    if (personalBests.containsKey(exerciseName)) {
                                        // Update personal best if necessary
                                        int currentBest = personalBests.get(exerciseName);
                                        if (setWeight > currentBest) {
                                            personalBests.put(exerciseName, setWeight);
                                            personalBestReps.put(exerciseName, setReps);
                                        }
                                    } else {
                                        // First personal best for this exercise
                                        personalBests.put(exerciseName, setWeight);
                                        personalBestReps.put(exerciseName, setReps);
                                    }
                                }
                            }
                        }
                    }
                }

                // Update EditText fields with personal bests
                updateEditTextFields(personalBests, personalBestReps);

                // Push personal bests to the database
                pushPersonalBestsToDatabase(personalBests, personalBestReps);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
            }
        });
    }
    private void updateEditTextFields(HashMap<String, Integer> personalBests, HashMap<String, Integer> personalBestReps) {
        if (personalBests.containsKey("Bench Press")) {
            String weightTemplate = getString(R.string.weight_and_reps_template);
            benchPb.setText(String.format("%s %s ", personalBests.get("Bench Press"), weightTemplate));
            benchPbReps.setText(String.valueOf(personalBestReps.get("Bench Press")));
        }
        if (personalBests.containsKey("Incline Bench Press")) {
            String weightTemplate = getString(R.string.weight_and_reps_template);
            inclinePb.setText(String.format("%s %s ", personalBests.get("Incline Bench Press"), weightTemplate));
            inclinePbReps.setText(String.valueOf(personalBestReps.get("Incline Bench Press")));
        }
        if (personalBests.containsKey("Tricep Extension")) {
            String weightTemplate = getString(R.string.weight_and_reps_template);
            tricepPb.setText(String.format("%s %s ", personalBests.get("Tricep Extension"), weightTemplate));
            tricepPbReps.setText(String.valueOf(personalBestReps.get("Tricep Extension")));
        }
        if (personalBests.containsKey("Lat Pulldown")) {
            String weightTemplate = getString(R.string.weight_and_reps_template);
            latPb.setText(String.format("%s %s ", personalBests.get("Lat Pulldown"), weightTemplate));
            latPbReps.setText(String.valueOf(personalBestReps.get("Lat Pulldown")));
        }
        if (personalBests.containsKey("Seated Row")) {
            String weightTemplate = getString(R.string.weight_and_reps_template);
            rowPb.setText(String.format("%s %s ", personalBests.get("Seated Row"), weightTemplate));
            rowPbReps.setText(String.valueOf(personalBestReps.get("Seated Row")));
        }
        if (personalBests.containsKey("Bicep Curl")) {
            String weightTemplate = getString(R.string.weight_and_reps_template);
            bicepPb.setText(String.format("%s %s ", personalBests.get("Bicep Curl"), weightTemplate));
            bicepPbReps.setText(String.valueOf(personalBestReps.get("Bicep Curl")));
        }
        if (personalBests.containsKey("Squats")) {
            String weightTemplate = getString(R.string.weight_and_reps_template);
            squatPb.setText(String.format("%s %s ", personalBests.get("Squats"), weightTemplate));
            squatPbReps.setText(String.valueOf(personalBestReps.get("Squats")));
        }
        if (personalBests.containsKey("Leg Extensions")) {
            String weightTemplate = getString(R.string.weight_and_reps_template);
            extensionPb.setText(String.format("%s %s ", personalBests.get("Leg Extensions"), weightTemplate));
            extensionPbReps.setText(String.valueOf(personalBestReps.get("Leg Extensions")));
        }
        if (personalBests.containsKey("Calf Raises")) {
            String weightTemplate = getString(R.string.weight_and_reps_template);
            calfPb.setText(String.format("%s %s ", personalBests.get("Calf Raises"), weightTemplate));
            calfPbReps.setText(String.valueOf(personalBestReps.get("Calf Raises")));
        }
    }

    private void pushPersonalBestsToDatabase(HashMap<String, Integer> personalBests, HashMap<String, Integer> personalBestReps) {
        DatabaseReference userPbRef = FirebaseDatabase.getInstance().getReference("users").child(user.getUid()).child("personal_bests");
        for (String exercise : personalBests.keySet()) {
            int newPb = personalBests.get(exercise);
            int newPbReps = personalBestReps.get(exercise);
            DatabaseReference exercisePbRef = userPbRef.child(exercise);
            exercisePbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (!dataSnapshot.exists()) {
                        // No existing personal best for this exercise, set it
                        exercisePbRef.child("weight").setValue(newPb);
                        exercisePbRef.child("reps").setValue(newPbReps);
                    } else {
                        // Update personal best only if the new one is greater
                        int existingPb = dataSnapshot.child("weight").getValue(Integer.class);
                        if (newPb > existingPb) {
                            exercisePbRef.child("weight").setValue(newPb);
                            exercisePbRef.child("reps").setValue(newPbReps);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle database error
                }
            });
        }
    }
}


//looks at the first set
//                for (DataSnapshot workoutSnapshot : snapshot.getChildren()) {
//                    // Iterate through exercises in each workout
//                    for (DataSnapshot exerciseSnapshot : workoutSnapshot.child("exercises").getChildren()) {
//                        String exerciseName = exerciseSnapshot.child("name").getValue(String.class);
//                        if (exerciseName != null) {
//                            // Retrieve best set weight and reps for the exercise
//                            Integer bestSetWeight = exerciseSnapshot.child("sets").child("set1").child("weight").getValue(Integer.class);
//                            Integer bestSetReps = exerciseSnapshot.child("sets").child("set1").child("reps").getValue(Integer.class);
//                            if (bestSetWeight != null) {
//                                // Check if a personal best exists for this exercise
//                                if (personalBests.containsKey(exerciseName)) {
//                                    // Update personal best if necessary
//                                    int currentBest = personalBests.get(exerciseName);
//                                    if (bestSetWeight > currentBest) {
//                                        personalBests.put(exerciseName, bestSetWeight);
//                                        personalBestReps.put(exerciseName, bestSetReps);
//                                    }
//                                } else {
//                                    // First personal best for this exercise
//                                    personalBests.put(exerciseName, bestSetWeight);
//                                    personalBestReps.put(exerciseName, bestSetReps);
//                                }
//                            }
//                        }
//                    }
//                }

// Update TextViews with personal bests
//                if (personalBests.containsKey("Bench Press")) {
//                    benchPb.setText(String.valueOf(personalBests.get("Bench Press")));
//                    benchPbReps.setText(String.valueOf(personalBestReps.get("Bench Press")));
//                }
//                if (personalBests.containsKey("Incline Bench Press")) {
//                    inclinePb.setText(String.valueOf(personalBests.get("Incline Bench Press")));
//                    inclinePbReps.setText(String.valueOf(personalBestReps.get("Incline Bench Press")));
//                }
//                if (personalBests.containsKey("Tricep Extension")) {
//                    tricepPb.setText(String.valueOf(personalBests.get("Tricep Extension")));
//                    tricepPbReps.setText(String.valueOf(personalBestReps.get("Tricep Extension")));
//                }

//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                HashMap<String, Integer> personalBests = new HashMap<>();
//
//                // Retrieve all workouts
//                for (DataSnapshot workoutSnapshot : snapshot.getChildren()) {
//                    // Iterate through exercises in each workout
//                    for (DataSnapshot exerciseSnapshot : workoutSnapshot.child("exercises").getChildren()) {
//                        String exerciseName = exerciseSnapshot.child("name").getValue(String.class);
//                        if (exerciseName != null) {
//                            // Retrieve best set weight for the exercise
//                            Integer bestSetWeight = exerciseSnapshot.child("sets").child("set1").child("weight").getValue(Integer.class);
//                            if (bestSetWeight != null) {
//                                // Check if a personal best exists for this exercise
//                                if (personalBests.containsKey(exerciseName)) {
//                                    // Update personal best if necessary
//                                    int currentBest = personalBests.get(exerciseName);
//                                    if (bestSetWeight > currentBest) {
//                                        personalBests.put(exerciseName, bestSetWeight);
//                                    }
//                                } else {
//                                    // First personal best for this exercise
//                                    personalBests.put(exerciseName, bestSetWeight);
//                                }
//                            }
//                        }
//                    }
//                }
//
//                // Update TextViews with personal bests
//                if (personalBests.containsKey("Bench Press")) {
//                    benchPb.setText(String.valueOf(personalBests.get("Bench Press")));
//                }
//                if (personalBests.containsKey("Incline Bench Press")) {
//                    inclinePb.setText(String.valueOf(personalBests.get("Incline Bench Press")));
//                }
//                if (personalBests.containsKey("Tricep Extension")) {
//                    tricepPb.setText(String.valueOf(personalBests.get("Tricep Extension")));
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // Handle database error
//            }
//        });