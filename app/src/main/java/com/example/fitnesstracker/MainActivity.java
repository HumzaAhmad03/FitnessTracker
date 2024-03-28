package com.example.fitnesstracker;

//import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button button,push,pull,legs, test, leaderboard;
    TextView textView;
    FirebaseAuth auth;
    FirebaseUser user;
//    RecyclerView recyclerView;
//    DatabaseReference databaseReference;
//    MyAdapter myAdapter;
//    HashMap<String, Workout> workoutMap;
    //ArrayList<WorkoutHistoryModel> workoutHistoryModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.logout_btn);
        push = findViewById(R.id.push_workout_type_btn);
        pull = findViewById(R.id.pull_workout_type_btn);
        legs = findViewById(R.id.leg_workout_type_btn);
        test = findViewById(R.id.testing_btn);
        leaderboard = findViewById(R.id.leaderboard_btn);
        textView = findViewById(R.id.user_details);
        user = auth.getCurrentUser();

//        recyclerView = findViewById(R.id.workoutHistory);
//        workoutMap = new HashMap<>();
//        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(user.getUid()).child("workouts");
//        //workoutHistoryModels = new ArrayList<>();
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        myAdapter = new MyAdapter(this, workoutMap);
//        recyclerView.setAdapter(myAdapter);

//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                workoutMap.clear(); // Clear the existing data before adding new data
//                for (DataSnapshot workoutSnapshot : snapshot.getChildren()) {
//                    Workout workout = workoutSnapshot.getValue(Workout.class);
//                    if (workout != null) {
//                        // Add the workout to the map using the workout type as the key
//                        workoutMap.put(workout.getWorkoutType(), workout);
//                    }
//                }
//                myAdapter.notifyDataSetChanged(); // Notify the adapter of data changes
//            }
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot workoutSnapshot : snapshot.getChildren()) {
//                    Workout workout = workoutSnapshot.getValue(Workout.class);
//                    if (workout != null) {
//                        // Assuming you want to add dummy data for exercise types, weights, and reps
//                        String workoutType = workout.getWorkoutType();
//                        String dummyType = "Dummy";
//                        String dummyWeight = "10"; // Dummy weight
//                        String dummyReps = "10"; // Dummy reps
//
//                        // Assuming you want to display exercises from the first workout only
//                        Map<String, Exercise> exercises = workout.getExercises();
//                        if (exercises != null) {
//                            for (Map.Entry<String, Exercise> entry : exercises.entrySet()) {
//                                Exercise exercise = entry.getValue();
//                                String exerciseName = exercise.getName();
//
//                                // Get the first three sets for each exercise
//                                Map<String, Set> sets = exercise.getSets();
//                                if (sets != null) {
//                                    // Get sets for the first three exercises
//                                    Set set1 = sets.get("set1");
//                                    Set set2 = sets.get("set2");
//                                    Set set3 = sets.get("set3");
//
//                                    // Add dummy data for three exercise types
//                                    workoutHistoryModels.add(new WorkoutHistoryModel(
//                                            user.getDisplayName(), // Assuming user name is available
//                                            workoutType,
//                                            exerciseName, exerciseName, exerciseName, // Exercise types
//                                            dummyWeight, dummyWeight, dummyWeight, // Exercise weights
//                                            String.valueOf(set1.getReps()), String.valueOf(set2.getReps()), String.valueOf(set3.getReps()), // Exercise reps
//                                            dummyWeight, dummyWeight, dummyWeight, // Exercise weights
//                                            String.valueOf(set1.getReps()), String.valueOf(set2.getReps()), String.valueOf(set3.getReps()), // Exercise reps
//                                            dummyWeight, dummyWeight, dummyWeight, // Exercise weights
//                                            String.valueOf(set1.getReps()), String.valueOf(set2.getReps()), String.valueOf(set3.getReps()) // Exercise reps
//                                    ));
//                                }
//                            }
//                        }
//                    }
//                }
//                myAdapter.notifyDataSetChanged();
//            }
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot workoutSnapshot : snapshot.getChildren()){
////                    WorkoutHistoryModel workoutHistoryModel = dataSnapshot.getValue(WorkoutHistoryModel.class);
////                    workoutHistoryModels.add(workoutHistoryModel);
//                    Workout workout = workoutSnapshot.getValue(Workout.class);
//                    if(workout != null){
//                        //workoutHistoryModels.add(new WorkoutHistoryModel(workout.getWorkoutType()));
//                        String workoutType = workout.getWorkoutType();
//                        // Assuming you want to add dummy data for exercise types, weights, and reps
//                        String dummyType = "Dummy";
//                        String dummyWeight = "10"; // Dummy weight
//                        String dummyReps = "10"; // Dummy reps
//                        // Add dummy data for three exercise types
//                        workoutHistoryModels.add(new WorkoutHistoryModel(
//                                user.getDisplayName(), // Assuming user name is available
//                                workoutType,
//                                dummyType, dummyType, dummyType, // Exercise types
//                                dummyWeight, dummyWeight, dummyWeight, // Exercise weights
//                                dummyReps, dummyReps, dummyReps, // Exercise reps
//                                dummyWeight, dummyWeight, dummyWeight, // Exercise weights
//                                dummyReps, dummyReps, dummyReps, // Exercise reps
//                                dummyWeight, dummyWeight, dummyWeight, // Exercise weights
//                                dummyReps, dummyReps, dummyReps // Exercise reps
//                        ));
//                    }
//                }
//                myAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        if (user == null){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            textView.setText(user.getEmail());

            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());
            userRef.child("userEmail").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (!dataSnapshot.exists()) {
                        // If "userEmail" node doesn't exist, push the email to the database
                        userRef.child("userEmail").setValue(user.getEmail());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Handle database error
                }
            });

//            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());
//            userRef.child("userEmail").setValue(user.getEmail());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PushWorkout.class);
                startActivity(intent);
                finish();
            }
        });
        pull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PullWorkout.class);
                startActivity(intent);
                finish();
            }
        });
        legs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LegWorkout.class);
                startActivity(intent);
                finish();
            }
        });

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserPbs.class);
                startActivity(intent);
                finish();
            }
        });
        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Leaderboard.class);
                startActivity(intent);
                finish();
            }
        });
   }
}