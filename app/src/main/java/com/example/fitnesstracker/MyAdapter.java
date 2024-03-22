package com.example.fitnesstracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<Workout> list;

    public MyAdapter(Context context, ArrayList<Workout> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_testing,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView userNameDisplay, exerciseType1,exerciseType2,exerciseType3, workoutTypeDisplay;
        TextView exercise1Weight1,exercise1Weight2,exercise1Weight3;
        TextView exercise1Reps1,exercise1Reps2,exercise1Reps3;
        TextView exercise2Weight1,exercise2Weight2,exercise2Weight3;
        TextView exercise2Reps1,exercise2Reps2,exercise2Reps3;
        TextView exercise3Weight1,exercise3Weight2,exercise3Weight3;
        TextView exercise3Reps1,exercise3Reps2,exercise3Reps3;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userNameDisplay = itemView.findViewById(R.id.textUsername);
            workoutTypeDisplay = itemView.findViewById(R.id.textWorkoutType);
            exerciseType1 = itemView.findViewById(R.id.textExerciseType1);
            exerciseType2 = itemView.findViewById(R.id.textExerciseType2);
            exerciseType3 = itemView.findViewById(R.id.textExerciseType3);
            exercise1Weight1 = itemView.findViewById(R.id.textE1W1);
            exercise1Weight2 = itemView.findViewById(R.id.textE1W2);
            exercise1Weight3 = itemView.findViewById(R.id.textE1W3);
            exercise2Weight1 = itemView.findViewById(R.id.textE2W1);
            exercise2Weight2 = itemView.findViewById(R.id.textE2W2);
            exercise2Weight3 = itemView.findViewById(R.id.textE2W3);
            exercise3Weight1 = itemView.findViewById(R.id.textE3W1);
            exercise3Weight2 = itemView.findViewById(R.id.textE3W2);
            exercise3Weight3 = itemView.findViewById(R.id.textE3W3);
            exercise1Reps1 = itemView.findViewById(R.id.textE1R1);
            exercise1Reps2 = itemView.findViewById(R.id.textE1R2);
            exercise1Reps3 = itemView.findViewById(R.id.textE1R3);
            exercise2Reps1 = itemView.findViewById(R.id.textE2R1);
            exercise2Reps2 = itemView.findViewById(R.id.textE2R2);
            exercise2Reps3 = itemView.findViewById(R.id.textE2R3);
            exercise3Reps1 = itemView.findViewById(R.id.textE3R1);
            exercise3Reps2 = itemView.findViewById(R.id.textE3R2);
            exercise3Reps3 = itemView.findViewById(R.id.textE3R3);
        }
    }
}
