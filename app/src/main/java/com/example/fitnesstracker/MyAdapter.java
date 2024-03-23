//package com.example.fitnesstracker;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
//    Context context;
//    ArrayList<String> keys;
//    HashMap<String, Workout> map;
//
//    public MyAdapter(Context context, HashMap<String, Workout> map) {
//        this.context = context;
//        //this.map = map;
//        if (map != null && !map.isEmpty()) {
//            this.map = map;
//            this.keys = new ArrayList<>(map.keySet());
//        } else {
//            this.map = new HashMap<>();
//            this.keys = new ArrayList<>();
//        }
//
////        if (map != null && !map.isEmpty()) {
////            this.keys = new ArrayList<>(map.keySet());
////        } else {
////            this.keys = new ArrayList<>();
////        }
//        //this.keys = new ArrayList<>(map.keySet());
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.workout_history_card,parent,false);
//        return new MyViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//
//        String key = keys.get(position);
//        Workout workout = map.get(key);
//        holder.workoutTypeDisplay.setText(workout.getWorkoutType());
//        Map<String, Exercise> exercises = workout.getExercises();
//        if (exercises != null) {
//            int i = 1;
//            for (Map.Entry<String, Exercise> entry : exercises.entrySet()) {
//                Exercise exercise = entry.getValue();
//                String exerciseName = exercise.getName();
//                Map<String, Set> sets = exercise.getSets();
//
//                // Get the sets for the first three exercises
//                Set set1 = sets.get("set1");
//                Set set2 = sets.get("set2");
//                Set set3 = sets.get("set3");
//
//                // Display exercise type
//                if (i == 1) {
//                    holder.exerciseType1.setText(exerciseName);
//                    holder.exercise1Weight1.setText(String.valueOf(set1.getWeight()));
//                    holder.exercise1Reps1.setText(String.valueOf(set1.getReps()));
//                    holder.exercise1Weight2.setText(String.valueOf(set2.getWeight()));
//                    holder.exercise1Reps2.setText(String.valueOf(set2.getReps()));
//                    holder.exercise1Weight3.setText(String.valueOf(set3.getWeight()));
//                    holder.exercise1Reps3.setText(String.valueOf(set3.getReps()));
//                } else if (i == 2) {
//                    holder.exerciseType2.setText(exerciseName);
//                    holder.exercise2Weight1.setText(String.valueOf(set1.getWeight()));
//                    holder.exercise2Reps1.setText(String.valueOf(set1.getReps()));
//                    holder.exercise2Weight2.setText(String.valueOf(set2.getWeight()));
//                    holder.exercise2Reps2.setText(String.valueOf(set2.getReps()));
//                    holder.exercise2Weight3.setText(String.valueOf(set3.getWeight()));
//                    holder.exercise2Reps3.setText(String.valueOf(set3.getReps()));
//                } else if (i == 3) {
//                    holder.exerciseType3.setText(exerciseName);
//                    holder.exercise3Weight1.setText(String.valueOf(set1.getWeight()));
//                    holder.exercise3Reps1.setText(String.valueOf(set1.getReps()));
//                    holder.exercise3Weight2.setText(String.valueOf(set2.getWeight()));
//                    holder.exercise3Reps2.setText(String.valueOf(set2.getReps()));
//                    holder.exercise3Weight3.setText(String.valueOf(set3.getWeight()));
//                    holder.exercise3Reps3.setText(String.valueOf(set3.getReps()));
//                }
//                i++;
//            }
//        }
//
//        //WorkoutHistoryModel workoutHistoryModel = list.get(position);
////        holder.userNameDisplay.setText(workoutHistoryModel.getUserName());
////        holder.workoutTypeDisplay.setText(workoutHistoryModel.getWorkoutType());
////        holder.exerciseType1.setText(workoutHistoryModel.getE1Type());
////        holder.exerciseType2.setText(workoutHistoryModel.getE2Type());
////        holder.exerciseType3.setText(workoutHistoryModel.getE3Type());
////        holder.exercise1Weight1.setText(workoutHistoryModel.getE1W1());
////        holder.exercise1Weight2.setText(workoutHistoryModel.getE1W2());
////        holder.exercise1Weight3.setText(workoutHistoryModel.getE1W3());
////        holder.exercise2Weight1.setText(workoutHistoryModel.getE2W1());
////        holder.exercise2Weight2.setText(workoutHistoryModel.getE2W2());
////        holder.exercise2Weight3.setText(workoutHistoryModel.getE2W3());
////        holder.exercise3Weight1.setText(workoutHistoryModel.getE3W1());
////        holder.exercise3Weight2.setText(workoutHistoryModel.getE3W2());
////        holder.exercise3Weight3.setText(workoutHistoryModel.getE3W3());
////        holder.exercise1Reps1.setText(workoutHistoryModel.getE1R1());
////        holder.exercise1Reps2.setText(workoutHistoryModel.getE1R2());
////        holder.exercise1Reps3.setText(workoutHistoryModel.getE1R3());
////        holder.exercise2Reps1.setText(workoutHistoryModel.getE2R1());
////        holder.exercise2Reps2.setText(workoutHistoryModel.getE2R2());
////        holder.exercise2Reps3.setText(workoutHistoryModel.getE2R3());
////        holder.exercise3Reps1.setText(workoutHistoryModel.getE3R1());
////        holder.exercise3Reps2.setText(workoutHistoryModel.getE3R2());
////        holder.exercise3Reps3.setText(workoutHistoryModel.getE3R3());
//
//        //holder.exercise3Reps3.setText(workoutHistoryModel.getE3R3());
//    }
//
//    @Override
//    public int getItemCount() {
//        return map.size();
//    }
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView userNameDisplay, exerciseType1,exerciseType2,exerciseType3, workoutTypeDisplay;
//        TextView exercise1Weight1,exercise1Weight2,exercise1Weight3;
//        TextView exercise1Reps1,exercise1Reps2,exercise1Reps3;
//        TextView exercise2Weight1,exercise2Weight2,exercise2Weight3;
//        TextView exercise2Reps1,exercise2Reps2,exercise2Reps3;
//        TextView exercise3Weight1,exercise3Weight2,exercise3Weight3;
//        TextView exercise3Reps1,exercise3Reps2,exercise3Reps3;
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            userNameDisplay = itemView.findViewById(R.id.textUsername);
//            workoutTypeDisplay = itemView.findViewById(R.id.textWorkoutType);
//            exerciseType1 = itemView.findViewById(R.id.textExerciseType1);
//            exerciseType2 = itemView.findViewById(R.id.textExerciseType2);
//            exerciseType3 = itemView.findViewById(R.id.textExerciseType3);
//            exercise1Weight1 = itemView.findViewById(R.id.textE1W1);
//            exercise1Weight2 = itemView.findViewById(R.id.textE1W2);
//            exercise1Weight3 = itemView.findViewById(R.id.textE1W3);
//            exercise2Weight1 = itemView.findViewById(R.id.textE2W1);
//            exercise2Weight2 = itemView.findViewById(R.id.textE2W2);
//            exercise2Weight3 = itemView.findViewById(R.id.textE2W3);
//            exercise3Weight1 = itemView.findViewById(R.id.textE3W1);
//            exercise3Weight2 = itemView.findViewById(R.id.textE3W2);
//            exercise3Weight3 = itemView.findViewById(R.id.textE3W3);
//            exercise1Reps1 = itemView.findViewById(R.id.textE1R1);
//            exercise1Reps2 = itemView.findViewById(R.id.textE1R2);
//            exercise1Reps3 = itemView.findViewById(R.id.textE1R3);
//            exercise2Reps1 = itemView.findViewById(R.id.textE2R1);
//            exercise2Reps2 = itemView.findViewById(R.id.textE2R2);
//            exercise2Reps3 = itemView.findViewById(R.id.textE2R3);
//            exercise3Reps1 = itemView.findViewById(R.id.textE3R1);
//            exercise3Reps2 = itemView.findViewById(R.id.textE3R2);
//            exercise3Reps3 = itemView.findViewById(R.id.textE3R3);
//        }
//    }
//}
