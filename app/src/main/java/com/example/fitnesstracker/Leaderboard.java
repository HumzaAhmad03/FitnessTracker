package com.example.fitnesstracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Leaderboard extends AppCompatActivity {

    private DatabaseReference usersReference;
    private TextView bench1stemail, bench1stweight, bench2ndemail, bench2ndweight, bench3rdemail, bench3rdweight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        // Initialize TextViews
        bench1stemail = findViewById(R.id.bench1stemail);
        bench1stweight = findViewById(R.id.bench1stweight);
        bench2ndemail = findViewById(R.id.bench2ndemail);
        bench2ndweight = findViewById(R.id.bench2ndweight);
        bench3rdemail = findViewById(R.id.bench3rdemail);
        bench3rdweight = findViewById(R.id.bench3rdweight);

        // Get reference to users node in the database
        usersReference = FirebaseDatabase.getInstance().getReference("users");

        // Retrieve data for all users
        usersReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<UserData> userDataList = new ArrayList<>();

                // Iterate through each user
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    UserData userData = userSnapshot.getValue(UserData.class);
                    if (userData != null) {
                        // Add user data to the list
                        userDataList.add(userData);
                    }
                }

                // Sort the user data list based on bench press weights
                Collections.sort(userDataList, new Comparator<UserData>() {
                    @Override
                    public int compare(UserData user1, UserData user2) {
                        // Compare bench press weights
                        return Integer.compare(user2.getPersonal_bests().get("Bench Press").getWeight(), user1.getPersonal_bests().get("Bench Press").getWeight());
                    }
                });

                // Update TextViews with sorted data
                updateLeaderboardUI(userDataList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });
    }

    private void updateLeaderboardUI(List<UserData> userDataList) {
        // Update 1st place if available
        if (!userDataList.isEmpty()) {
            UserData firstPlace = userDataList.get(0);
            bench1stemail.setText(firstPlace.getUserEmail());
            bench1stweight.setText(String.valueOf(getBenchPressWeight(firstPlace)));
        } else {
            // If no users, display N/A
            bench1stemail.setText("N/A");
            bench1stweight.setText("N/A");
        }

        // Update 2nd place if available
        if (userDataList.size() >= 2) {
            UserData secondPlace = userDataList.get(1);
            bench2ndemail.setText(secondPlace.getUserEmail());
            bench2ndweight.setText(String.valueOf(getBenchPressWeight(secondPlace)));
        } else {
            // If no second user, display N/A
            bench2ndemail.setText("N/A");
            bench2ndweight.setText("N/A");
        }

        // Update 3rd place if available
        if (userDataList.size() >= 3) {
            UserData thirdPlace = userDataList.get(2);
            bench3rdemail.setText(thirdPlace.getUserEmail());
            bench3rdweight.setText(String.valueOf(getBenchPressWeight(thirdPlace)));
        } else {
            // If no third user, display N/A
            bench3rdemail.setText("N/A");
            bench3rdweight.setText("N/A");
        }
    }

    private int getBenchPressWeight(UserData userData) {
        Map<String, ExerciseData> personalBests = userData.getPersonal_bests();
        if (personalBests != null && personalBests.containsKey("Bench Press")) {
            ExerciseData benchPress = personalBests.get("Bench Press");
            return benchPress.getWeight();
        }
        return 0;
    }
}