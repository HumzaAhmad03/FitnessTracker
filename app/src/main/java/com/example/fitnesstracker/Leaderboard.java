package com.example.fitnesstracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private TextView incline1stemail, incline1stweight, incline2ndemail, incline2ndweight, incline3rdemail, incline3rdweight;
    private TextView tricep1stemail, tricep1stweight, tricep2ndemail, tricep2ndweight, tricep3rdemail, tricep3rdweight;
    private TextView lat1stemail, lat1stweight, lat2ndemail, lat2ndweight, lat3rdemail, lat3rdweight;
    private TextView row1stemail, row1stweight, row2ndemail, row2ndweight, row3rdemail, row3rdweight;
    private TextView bicep1stemail, bicep1stweight, bicep2ndemail, bicep2ndweight, bicep3rdemail, bicep3rdweight;
    private TextView squat1stemail, squat1stweight, squat2ndemail, squat2ndweight, squat3rdemail, squat3rdweight;
    private TextView extension1stemail, extension1stweight, extension2ndemail, extension2ndweight, extension3rdemail, extension3rdweight;
    private TextView calf1stemail, calf1stweight, calf2ndemail, calf2ndweight, calf3rdemail, calf3rdweight;

    Button home;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Leaderboard.this, MainActivity.class));
        finish();
    }
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

        incline1stemail = findViewById(R.id.incline1stemail);
        incline1stweight = findViewById(R.id.incline1stweight);
        incline2ndemail = findViewById(R.id.incline2ndemail);
        incline2ndweight = findViewById(R.id.incline2ndweight);
        incline3rdemail = findViewById(R.id.incline3rdemail);
        incline3rdweight = findViewById(R.id.incline3rdweight);

        tricep1stemail = findViewById(R.id.tricep1stemail);
        tricep1stweight = findViewById(R.id.tricep1stweight);
        tricep2ndemail = findViewById(R.id.tricep2ndemail);
        tricep2ndweight = findViewById(R.id.tricep2ndweight);
        tricep3rdemail = findViewById(R.id.tricep3rdemail);
        tricep3rdweight = findViewById(R.id.tricep3rdweight);

        // Initialize TextViews for lat pull-down
        lat1stemail = findViewById(R.id.lat1stemail);
        lat1stweight = findViewById(R.id.lat1stweight);
        lat2ndemail = findViewById(R.id.lat2ndemail);
        lat2ndweight = findViewById(R.id.lat2ndweight);
        lat3rdemail = findViewById(R.id.lat3rdemail);
        lat3rdweight = findViewById(R.id.lat3rdweight);

        // Initialize TextViews for row
        row1stemail = findViewById(R.id.row1stemail);
        row1stweight = findViewById(R.id.row1stweight);
        row2ndemail = findViewById(R.id.row2ndemail);
        row2ndweight = findViewById(R.id.row2ndweight);
        row3rdemail = findViewById(R.id.row3rdemail);
        row3rdweight = findViewById(R.id.row3rdweight);

        // Initialize TextViews for bicep curl
        bicep1stemail = findViewById(R.id.bicep1stemail);
        bicep1stweight = findViewById(R.id.bicep1stweight);
        bicep2ndemail = findViewById(R.id.bicep2ndemail);
        bicep2ndweight = findViewById(R.id.bicep2ndweight);
        bicep3rdemail = findViewById(R.id.bicep3rdemail);
        bicep3rdweight = findViewById(R.id.bicep3rdweight);

        // Initialize TextViews for squat
        squat1stemail = findViewById(R.id.squat1stemail);
        squat1stweight = findViewById(R.id.squat1stweight);
        squat2ndemail = findViewById(R.id.squat2ndemail);
        squat2ndweight = findViewById(R.id.squat2ndweight);
        squat3rdemail = findViewById(R.id.squat3rdemail);
        squat3rdweight = findViewById(R.id.squat3rdweight);

        // Initialize TextViews for leg extension
        extension1stemail = findViewById(R.id.extension1stemail);
        extension1stweight = findViewById(R.id.extension1stweight);
        extension2ndemail = findViewById(R.id.extension2ndemail);
        extension2ndweight = findViewById(R.id.extension2ndweight);
        extension3rdemail = findViewById(R.id.extension3rdemail);
        extension3rdweight = findViewById(R.id.extension3rdweight);

        // Initialize TextViews for calf exercise
        calf1stemail = findViewById(R.id.calf1stemail);
        calf1stweight = findViewById(R.id.calf1stweight);
        calf2ndemail = findViewById(R.id.calf2ndemail);
        calf2ndweight = findViewById(R.id.calf2ndweight);
        calf3rdemail = findViewById(R.id.calf3rdemail);
        calf3rdweight = findViewById(R.id.calf3rdweight);

        home = findViewById(R.id.home_btn);

        String kg = getString(R.string.leaderboard);

        // Get reference to users node in the database
        usersReference = FirebaseDatabase.getInstance().getReference("users");
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Retrieve data for all users
        usersReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<UserData> benchPressList = new ArrayList<>();
                List<UserData> inclineBenchPressList = new ArrayList<>();
                List<UserData> tricepExtensionList = new ArrayList<>();
                List<UserData> latPullDownList = new ArrayList<>();
                List<UserData> rowList = new ArrayList<>();
                List<UserData> bicepCurlList = new ArrayList<>();
                List<UserData> squatsList = new ArrayList<>();
                List<UserData> legExtensionList = new ArrayList<>();
                List<UserData> calfRaiseList = new ArrayList<>();

                // Iterate through each user
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    UserData userData = userSnapshot.getValue(UserData.class);
                    if (userData != null) {
                        // Add user data to the respective lists based on exercise type
                        benchPressList.add(userData);
                        inclineBenchPressList.add(userData);
                        tricepExtensionList.add(userData);
                        latPullDownList.add(userData);
                        rowList.add(userData);
                        bicepCurlList.add(userData);
                        squatsList.add(userData);
                        legExtensionList.add(userData);
                        calfRaiseList.add(userData);
                    }
                }

                // Sort each list individually

                // Comparator for Bench Press
                Collections.sort(benchPressList, new Comparator<UserData>() {
                    @Override
                    public int compare(UserData user1, UserData user2) {
                        ExerciseData benchPress1 = user1.getPersonal_bests().get("Bench Press");
                        ExerciseData benchPress2 = user2.getPersonal_bests().get("Bench Press");
                        return compareExerciseData(benchPress1, benchPress2);
                    }
                });

                // Comparator for Incline Bench Press
                Collections.sort(inclineBenchPressList, new Comparator<UserData>() {
                    @Override
                    public int compare(UserData user1, UserData user2) {
                        ExerciseData inclineBenchPress1 = user1.getPersonal_bests().get("Incline Bench Press");
                        ExerciseData inclineBenchPress2 = user2.getPersonal_bests().get("Incline Bench Press");
                        return compareExerciseData(inclineBenchPress1, inclineBenchPress2);
                    }
                });

                // Comparator for Tricep Extension
                Collections.sort(tricepExtensionList, new Comparator<UserData>() {
                    @Override
                    public int compare(UserData user1, UserData user2) {
                        ExerciseData tricepExtension1 = user1.getPersonal_bests().get("Tricep Extension");
                        ExerciseData tricepExtension2 = user2.getPersonal_bests().get("Tricep Extension");
                        return compareExerciseData(tricepExtension1, tricepExtension2);
                    }
                });

                // Comparator for Lat Pull-down
                Collections.sort(latPullDownList, new Comparator<UserData>() {
                    @Override
                    public int compare(UserData user1, UserData user2) {
                        ExerciseData latPullDown1 = user1.getPersonal_bests().get("Lat Pulldown");
                        ExerciseData latPullDown2 = user2.getPersonal_bests().get("Lat Pulldown");
                        return compareExerciseData(latPullDown1, latPullDown2);
                    }
                });

                // Comparator for Row
                Collections.sort(rowList, new Comparator<UserData>() {
                    @Override
                    public int compare(UserData user1, UserData user2) {
                        ExerciseData row1 = user1.getPersonal_bests().get("Seated Row");
                        ExerciseData row2 = user2.getPersonal_bests().get("Seated Row");
                        return compareExerciseData(row1, row2);
                    }
                });

                // Comparator for Bicep Curl
                Collections.sort(bicepCurlList, new Comparator<UserData>() {
                    @Override
                    public int compare(UserData user1, UserData user2) {
                        ExerciseData bicepCurl1 = user1.getPersonal_bests().get("Bicep Curl");
                        ExerciseData bicepCurl2 = user2.getPersonal_bests().get("Bicep Curl");
                        return compareExerciseData(bicepCurl1, bicepCurl2);
                    }
                });
                // Comparator for Squats
                Collections.sort(squatsList, new Comparator<UserData>() {
                    @Override
                    public int compare(UserData user1, UserData user2) {
                        ExerciseData squats1 = user1.getPersonal_bests().get("Squats");
                        ExerciseData squats2 = user2.getPersonal_bests().get("Squats");
                        return compareExerciseData(squats1, squats2);
                    }
                });

                // Comparator for Leg Extension
                Collections.sort(legExtensionList, new Comparator<UserData>() {
                    @Override
                    public int compare(UserData user1, UserData user2) {
                        ExerciseData legExtension1 = user1.getPersonal_bests().get("Leg Extensions");
                        ExerciseData legExtension2 = user2.getPersonal_bests().get("Leg Extensions");
                        return compareExerciseData(legExtension1, legExtension2);
                    }
                });

                // Comparator for Calf Raise
                Collections.sort(calfRaiseList, new Comparator<UserData>() {
                    @Override
                    public int compare(UserData user1, UserData user2) {
                        ExerciseData calfRaise1 = user1.getPersonal_bests().get("Calf Raises");
                        ExerciseData calfRaise2 = user2.getPersonal_bests().get("Calf Raises");
                        return compareExerciseData(calfRaise1, calfRaise2);
                    }
                });


                // Update TextViews with sorted data for each exercise type
                updateLeaderboardUI(benchPressList);
                updateInclineLeaderboardUI(inclineBenchPressList);
                updateTricepLeaderboardUI(tricepExtensionList);
                updateLatLeaderboardUI(latPullDownList);
                updateRowLeaderboardUI(rowList);
                updateBicepLeaderboardUI(bicepCurlList);
                updateSquatLeaderboardUI(squatsList);
                updateExtensionLeaderboardUI(legExtensionList);
                updateCalfLeaderboardUI(calfRaiseList);
            }
            private int compareExerciseData(ExerciseData data1, ExerciseData data2) {
                if (data1 != null && data2 != null) {
                    return Integer.compare(data2.getWeight(), data1.getWeight());
                } else {
                    return 0; // or any other appropriate value
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });
    }

    private void updateLeaderboardUI(List<UserData> userDataList) {
        // Update 1st place if available for bench press
        if (!userDataList.isEmpty()) {
            UserData firstPlace = userDataList.get(0);
            bench1stemail.setText(firstPlace.getUserEmail());
            //bench1stweight.setText(String.valueOf(getExerciseWeight(firstPlace, "Bench Press")));
            bench1stweight.setText(String.format("%s kg", getExerciseWeight(firstPlace, "Bench Press")));
        } else {
            // If no users, display N/A
            bench1stemail.setText("N/A");
            bench1stweight.setText("N/A");
        }

        // Update 2nd place if available for bench press
        if (userDataList.size() >= 2) {
            UserData secondPlace = userDataList.get(1);
            bench2ndemail.setText(secondPlace.getUserEmail());
            //bench2ndweight.setText(String.valueOf(getExerciseWeight(secondPlace, "Bench Press")));
            bench2ndweight.setText(String.format("%s kg", getExerciseWeight(secondPlace, "Bench Press")));
        } else {
            // If no second user, display N/A
            bench2ndemail.setText("N/A");
            bench2ndweight.setText("N/A");
        }

        // Update 3rd place if available for bench press
        if (userDataList.size() >= 3) {
            UserData thirdPlace = userDataList.get(2);
            bench3rdemail.setText(thirdPlace.getUserEmail());
            //bench3rdweight.setText(String.valueOf(getExerciseWeight(thirdPlace, "Bench Press")));
            bench3rdweight.setText(String.format("%s kg", getExerciseWeight(thirdPlace, "Bench Press")));
        } else {
            // If no third user, display N/A
            bench3rdemail.setText("N/A");
            bench3rdweight.setText("N/A");
        }
    }

    private void updateInclineLeaderboardUI(List<UserData> userDataList) {
        // Update 1st place if available for incline bench press
        if (!userDataList.isEmpty()) {
            UserData firstPlace = userDataList.get(0);
            incline1stemail.setText(firstPlace.getUserEmail());
            incline1stweight.setText(String.format("%s kg", getExerciseWeight(firstPlace, "Incline Bench Press")));
        } else {
            // If no users, display N/A
            incline1stemail.setText("N/A");
            incline1stweight.setText("N/A");
        }

        // Update 2nd place if available for incline bench press
        if (userDataList.size() >= 2) {
            UserData secondPlace = userDataList.get(1);
            incline2ndemail.setText(secondPlace.getUserEmail());
            incline2ndweight.setText(String.format("%s kg", getExerciseWeight(secondPlace, "Incline Bench Press")));
        } else {
            // If no second user, display N/A
            incline2ndemail.setText("N/A");
            incline2ndweight.setText("N/A");
        }

        // Update 3rd place if available for incline bench press
        if (userDataList.size() >= 3) {
            UserData thirdPlace = userDataList.get(2);
            incline3rdemail.setText(thirdPlace.getUserEmail());
            incline3rdweight.setText(String.format("%s kg", getExerciseWeight(thirdPlace, "Incline Bench Press")));
        } else {
            // If no third user, display N/A
            incline3rdemail.setText("N/A");
            incline3rdweight.setText("N/A");
        }
    }

    private void updateTricepLeaderboardUI(List<UserData> userDataList) {
        // Update 1st place if available for tricep extension
        if (!userDataList.isEmpty()) {
            UserData firstPlace = userDataList.get(0);
            tricep1stemail.setText(firstPlace.getUserEmail());
            tricep1stweight.setText(String.format("%s kg", getExerciseWeight(firstPlace, "Tricep Extension")));
        } else {
            // If no users, display N/A
            tricep1stemail.setText("N/A");
            tricep1stweight.setText("N/A");
        }

        // Update 2nd place if available for tricep extension
        if (userDataList.size() >= 2) {
            UserData secondPlace = userDataList.get(1);
            tricep2ndemail.setText(secondPlace.getUserEmail());
            tricep2ndweight.setText(String.format("%s kg", getExerciseWeight(secondPlace, "Tricep Extension")));
        } else {
            // If no second user, display N/A
            tricep2ndemail.setText("N/A");
            tricep2ndweight.setText("N/A");
        }

        // Update 3rd place if available for tricep extension
        if (userDataList.size() >= 3) {
            UserData thirdPlace = userDataList.get(2);
            tricep3rdemail.setText(thirdPlace.getUserEmail());
            tricep3rdweight.setText(String.format("%s kg", getExerciseWeight(thirdPlace, "Tricep Extension")));
        } else {
            // If no third user, display N/A
            tricep3rdemail.setText("N/A");
            tricep3rdweight.setText("N/A");
        }
    }

    private void updateLatLeaderboardUI(List<UserData> userDataList) {
        // Update 1st place if available for lat pull-down
        if (!userDataList.isEmpty()) {
            UserData firstPlace = userDataList.get(0);
            lat1stemail.setText(firstPlace.getUserEmail());
            lat1stweight.setText(String.format("%s kg", getExerciseWeight(firstPlace, "Lat Pulldown")));
        } else {
            // If no users, display N/A
            lat1stemail.setText("N/A");
            lat1stweight.setText("N/A");
        }

        // Update 2nd place if available for lat pull-down
        if (userDataList.size() >= 2) {
            UserData secondPlace = userDataList.get(1);
            lat2ndemail.setText(secondPlace.getUserEmail());
            lat2ndweight.setText(String.format("%s kg", getExerciseWeight(secondPlace, "Lat Pulldown")));
        } else {
            // If no second user, display N/A
            lat2ndemail.setText("N/A");
            lat2ndweight.setText("N/A");
        }

        // Update 3rd place if available for lat pull-down
        if (userDataList.size() >= 3) {
            UserData thirdPlace = userDataList.get(2);
            lat3rdemail.setText(thirdPlace.getUserEmail());
            lat3rdweight.setText(String.format("%s kg", getExerciseWeight(thirdPlace, "Lat Pulldown")));
        } else {
            // If no third user, display N/A
            lat3rdemail.setText("N/A");
            lat3rdweight.setText("N/A");
        }
    }

    private void updateRowLeaderboardUI(List<UserData> userDataList) {
        // Update 1st place if available for row
        if (!userDataList.isEmpty()) {
            UserData firstPlace = userDataList.get(0);
            row1stemail.setText(firstPlace.getUserEmail());
            row1stweight.setText(String.format("%s kg", getExerciseWeight(firstPlace, "Seated Row")));
        } else {
            // If no users, display N/A
            row1stemail.setText("N/A");
            row1stweight.setText("N/A");
        }

        // Update 2nd place if available for row
        if (userDataList.size() >= 2) {
            UserData secondPlace = userDataList.get(1);
            row2ndemail.setText(secondPlace.getUserEmail());
            row2ndweight.setText(String.format("%s kg", getExerciseWeight(secondPlace, "Seated Row")));
        } else {
            // If no second user, display N/A
            row2ndemail.setText("N/A");
            row2ndweight.setText("N/A");
        }

        // Update 3rd place if available for row
        if (userDataList.size() >= 3) {
            UserData thirdPlace = userDataList.get(2);
            row3rdemail.setText(thirdPlace.getUserEmail());
            row3rdweight.setText(String.format("%s kg", getExerciseWeight(thirdPlace, "Seated Row")));
        } else {
            // If no third user, display N/A
            row3rdemail.setText("N/A");
            row3rdweight.setText("N/A");
        }
    }

    private void updateBicepLeaderboardUI(List<UserData> userDataList) {
        // Update 1st place if available for bicep curl
        if (!userDataList.isEmpty()) {
            UserData firstPlace = userDataList.get(0);
            bicep1stemail.setText(firstPlace.getUserEmail());
            bicep1stweight.setText(String.format("%s kg", getExerciseWeight(firstPlace, "Bicep Curl")));
        } else {
            // If no users, display N/A
            bicep1stemail.setText("N/A");
            bicep1stweight.setText("N/A");
        }

        // Update 2nd place if available for bicep curl
        if (userDataList.size() >= 2) {
            UserData secondPlace = userDataList.get(1);
            bicep2ndemail.setText(secondPlace.getUserEmail());
            bicep2ndweight.setText(String.format("%s kg", getExerciseWeight(secondPlace, "Bicep Curl")));
        } else {
            // If no second user, display N/A
            bicep2ndemail.setText("N/A");
            bicep2ndweight.setText("N/A");
        }

        // Update 3rd place if available for bicep curl
        if (userDataList.size() >= 3) {
            UserData thirdPlace = userDataList.get(2);
            bicep3rdemail.setText(thirdPlace.getUserEmail());
            bicep3rdweight.setText(String.format("%s kg", getExerciseWeight(thirdPlace, "Bicep Curl")));
        } else {
            // If no third user, display N/A
            bicep3rdemail.setText("N/A");
            bicep3rdweight.setText("N/A");
        }
    }
    private void updateSquatLeaderboardUI(List<UserData> userDataList) {
        // Update 1st place if available for squats
        if (!userDataList.isEmpty()) {
            UserData firstPlace = userDataList.get(0);
            squat1stemail.setText(firstPlace.getUserEmail());
            squat1stweight.setText(String.format("%s kg", getExerciseWeight(firstPlace, "Squats")));
        } else {
            // If no users, display N/A
            squat1stemail.setText("N/A");
            squat1stweight.setText("N/A");
        }

        // Update 2nd place if available for squats
        if (userDataList.size() >= 2) {
            UserData secondPlace = userDataList.get(1);
            squat2ndemail.setText(secondPlace.getUserEmail());
            squat2ndweight.setText(String.format("%s kg", getExerciseWeight(secondPlace, "Squats")));
        } else {
            // If no second user, display N/A
            squat2ndemail.setText("N/A");
            squat2ndweight.setText("N/A");
        }

        // Update 3rd place if available for squats
        if (userDataList.size() >= 3) {
            UserData thirdPlace = userDataList.get(2);
            squat3rdemail.setText(thirdPlace.getUserEmail());
            squat3rdweight.setText(String.format("%s kg", getExerciseWeight(thirdPlace, "Squats")));
        } else {
            // If no third user, display N/A
            squat3rdemail.setText("N/A");
            squat3rdweight.setText("N/A");
        }
    }
    private void updateExtensionLeaderboardUI(List<UserData> userDataList) {
        // Update 1st place if available for leg extensions
        if (!userDataList.isEmpty()) {
            UserData firstPlace = userDataList.get(0);
            extension1stemail.setText(firstPlace.getUserEmail());
            extension1stweight.setText(String.format("%s kg", getExerciseWeight(firstPlace, "Leg Extensions")));
        } else {
            // If no users, display N/A
            extension1stemail.setText("N/A");
            extension1stweight.setText("N/A");
        }

        // Update 2nd place if available for leg extensions
        if (userDataList.size() >= 2) {
            UserData secondPlace = userDataList.get(1);
            extension2ndemail.setText(secondPlace.getUserEmail());
            extension2ndweight.setText(String.format("%s kg", getExerciseWeight(secondPlace, "Leg Extensions")));
        } else {
            // If no second user, display N/A
            extension2ndemail.setText("N/A");
            extension2ndweight.setText("N/A");
        }

        // Update 3rd place if available for leg extensions
        if (userDataList.size() >= 3) {
            UserData thirdPlace = userDataList.get(2);
            extension3rdemail.setText(thirdPlace.getUserEmail());
            extension3rdweight.setText(String.format("%s kg", getExerciseWeight(thirdPlace, "Leg Extensions")));
        } else {
            // If no third user, display N/A
            extension3rdemail.setText("N/A");
            extension3rdweight.setText("N/A");
        }
    }
    private void updateCalfLeaderboardUI(List<UserData> userDataList) {
        // Update 1st place if available for calf raises
        if (!userDataList.isEmpty()) {
            UserData firstPlace = userDataList.get(0);
            calf1stemail.setText(firstPlace.getUserEmail());
            calf1stweight.setText(String.format("%s kg", getExerciseWeight(firstPlace, "Calf Raises")));
        } else {
            // If no users, display N/A
            calf1stemail.setText("N/A");
            calf1stweight.setText("N/A");
        }

        // Update 2nd place if available for calf raises
        if (userDataList.size() >= 2) {
            UserData secondPlace = userDataList.get(1);
            calf2ndemail.setText(secondPlace.getUserEmail());
            calf2ndweight.setText(String.format("%s kg", getExerciseWeight(secondPlace, "Calf Raises")));
        } else {
            // If no second user, display N/A
            calf2ndemail.setText("N/A");
            calf2ndweight.setText("N/A");
        }

        // Update 3rd place if available for calf raises
        if (userDataList.size() >= 3) {
            UserData thirdPlace = userDataList.get(2);
            calf3rdemail.setText(thirdPlace.getUserEmail());
            calf3rdweight.setText(String.format("%s kg", getExerciseWeight(thirdPlace, "Calf Raises")));
        } else {
            // If no third user, display N/A
            calf3rdemail.setText("N/A");
            calf3rdweight.setText("N/A");
        }
    }


    private int getExerciseWeight(UserData userData, String exerciseName) {
        Map<String, ExerciseData> personalBests = userData.getPersonal_bests();
        if (personalBests != null && personalBests.containsKey(exerciseName)) {
            ExerciseData exerciseData = personalBests.get(exerciseName);
            return exerciseData.getWeight();
        }
        return 0;
    }
}


//package com.example.fitnesstracker;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.widget.TextView;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//import java.util.Map;
//
//public class Leaderboard extends AppCompatActivity {
//
//    private DatabaseReference usersReference;
//    private TextView bench1stemail, bench1stweight, bench2ndemail, bench2ndweight, bench3rdemail, bench3rdweight;
//    private TextView incline1stemail, incline1stweight, incline2ndemail, incline2ndweight, incline3rdemail, incline3rdweight;
//    private TextView tricep1stemail, tricep1stweight, tricep2ndemail, tricep2ndweight, tricep3rdemail, tricep3rdweight;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_leaderboard);
//
//        // Initialize TextViews
//        bench1stemail = findViewById(R.id.bench1stemail);
//        bench1stweight = findViewById(R.id.bench1stweight);
//        bench2ndemail = findViewById(R.id.bench2ndemail);
//        bench2ndweight = findViewById(R.id.bench2ndweight);
//        bench3rdemail = findViewById(R.id.bench3rdemail);
//        bench3rdweight = findViewById(R.id.bench3rdweight);
//
//        // Initialize TextViews for incline bench press
//        incline1stemail = findViewById(R.id.incline1stemail);
//        incline1stweight = findViewById(R.id.incline1stweight);
//        incline2ndemail = findViewById(R.id.incline2ndemail);
//        incline2ndweight = findViewById(R.id.incline2ndweight);
//        incline3rdemail = findViewById(R.id.incline3rdemail);
//        incline3rdweight = findViewById(R.id.incline3rdweight);
//
//        // Initialize TextViews for tricep extension
//        tricep1stemail = findViewById(R.id.tricep1stemail);
//        tricep1stweight = findViewById(R.id.tricep1stweight);
//        tricep2ndemail = findViewById(R.id.tricep2ndemail);
//        tricep2ndweight = findViewById(R.id.tricep2ndweight);
//        tricep3rdemail = findViewById(R.id.tricep3rdemail);
//        tricep3rdweight = findViewById(R.id.tricep3rdweight);
//
//        // Get reference to users node in the database
//        usersReference = FirebaseDatabase.getInstance().getReference("users");
//
//        // Retrieve data for all users
//        usersReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                List<UserData> userDataList = new ArrayList<>();
//
//                // Iterate through each user
//                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
//                    UserData userData = userSnapshot.getValue(UserData.class);
//                    if (userData != null) {
//                        // Add user data to the list
//                        userDataList.add(userData);
//                    }
//                }
//
//                // Sort the user data list based on bench press weights
//                Collections.sort(userDataList, new Comparator<UserData>() {
//                    @Override
//                    public int compare(UserData user1, UserData user2) {
//                        // Compare bench press weights
//                        return Integer.compare(user2.getPersonal_bests().get("Bench Press").getWeight(), user1.getPersonal_bests().get("Bench Press").getWeight());
//                    }
//                });
//
//                // Update TextViews with sorted data
//                updateLeaderboardUI(userDataList);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Handle database error
//            }
//        });
//    }
//
//    private void updateLeaderboardUI(List<UserData> userDataList) {
//        // Update 1st place if available
//        if (!userDataList.isEmpty()) {
//            UserData firstPlace = userDataList.get(0);
//            bench1stemail.setText(firstPlace.getUserEmail());
//            bench1stweight.setText(String.valueOf(getBenchPressWeight(firstPlace)));
//        } else {
//            // If no users, display N/A
//            bench1stemail.setText("N/A");
//            bench1stweight.setText("N/A");
//        }
//
//        // Update 2nd place if available
//        if (userDataList.size() >= 2) {
//            UserData secondPlace = userDataList.get(1);
//            bench2ndemail.setText(secondPlace.getUserEmail());
//            bench2ndweight.setText(String.valueOf(getBenchPressWeight(secondPlace)));
//        } else {
//            // If no second user, display N/A
//            bench2ndemail.setText("N/A");
//            bench2ndweight.setText("N/A");
//        }
//
//        // Update 3rd place if available
//        if (userDataList.size() >= 3) {
//            UserData thirdPlace = userDataList.get(2);
//            bench3rdemail.setText(thirdPlace.getUserEmail());
//            bench3rdweight.setText(String.valueOf(getBenchPressWeight(thirdPlace)));
//        } else {
//            // If no third user, display N/A
//            bench3rdemail.setText("N/A");
//            bench3rdweight.setText("N/A");
//        }
//    }
//
//    private int getBenchPressWeight(UserData userData) {
//        Map<String, ExerciseData> personalBests = userData.getPersonal_bests();
//        if (personalBests != null && personalBests.containsKey("Bench Press")) {
//            ExerciseData benchPress = personalBests.get("Bench Press");
//            return benchPress.getWeight();
//        }
//        return 0;
//    }
//}