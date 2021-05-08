package com.example.myfit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ActivityViewWorkout extends AppCompatActivity {

    ListView listView ;

    String workoutTitle[] = {"Dumbbell Bench Press","Pushups","Close-grip bench press",
            "Dumbbell Flyes","Incline dumbbell bench press","Incline Hammer Curls",
            "EZ-bar spider curl","Dumbbell Bicep Curl","Machine Calf Raise","Standing Calf Raises",
            "Rickshaw Carry","Neck Bridge Prone","Neck-SMR","Dumbbell front raise to lateral raise",
            "Dumbbell Bench Press","Pushups","Close-grip bench press",
            "Dumbbell Flyes","Incline dumbbell bench press","Incline Hammer Curls",
            "EZ-bar spider curl","Dumbbell Bicep Curl","Machine Calf Raise","Standing Calf Raises",
            "Rickshaw Carry","Neck Bridge Prone","Neck-SMR","Dumbbell front raise to lateral raise"};

    String workoutDescription[] = {"Muscle Targeted: Chest","Muscle Targeted: Chest","Muscle Targeted: Chest",
            "Muscle Targeted: Chest","Muscle Targeted: Chest","Muscle Targeted: Biceps","Muscle Targeted: Biceps",
            "Muscle Targeted: Biceps","Muscle Targeted: Calves","Muscle Targeted: Calves","Muscle Targeted: Forearms",
            "Muscle Targeted: Neck","Muscle Targeted: Neck","Muscle Targeted: Shoulders",
            "Muscle Targeted: Chest","Muscle Targeted: Chest","Muscle Targeted: Chest",
            "Muscle Targeted: Chest","Muscle Targeted: Chest","Muscle Targeted: Biceps","Muscle Targeted: Biceps",
            "Muscle Targeted: Biceps","Muscle Targeted: Calves","Muscle Targeted: Calves","Muscle Targeted: Forearms",
            "Muscle Targeted: Neck","Muscle Targeted: Neck","Muscle Targeted: Shoulders"};

    int workoutImage[] = {R.drawable.a, R.drawable.b, R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,
            R.drawable.g,R.drawable.h,R.drawable.i, R.drawable.j,R.drawable.k,R.drawable.l,R.drawable.m,R.drawable.n,
            R.drawable.a, R.drawable.b, R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,
            R.drawable.g,R.drawable.h,R.drawable.i, R.drawable.j,R.drawable.k,R.drawable.l,R.drawable.m,R.drawable.n};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_workout);

        listView = findViewById(R.id.listView);
        CustomAdapter adapter = new CustomAdapter(this,workoutTitle,workoutImage,workoutDescription);
        listView.setAdapter(adapter);
    }
}

class CustomAdapter extends ArrayAdapter<String> {

    Context context;
    int[] workoutImages;
    String[] workoutTitle;
    String[] workoutDescription;

    CustomAdapter(Context context, String[] workoutTitle, int[] workoutImages, String[] workoutDescription){
        super(context, R.layout.singel_row_exercises_view,R.id.workoutTitle,workoutTitle);
        this.context = context;
        this.workoutImages = workoutImages;
        this.workoutTitle = workoutTitle;
        this.workoutDescription = workoutDescription;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = inflater.inflate(R.layout.singel_row_exercises_view, parent,false);

        ImageView workoutImageview = row.findViewById(R.id.workoutImage);
        TextView workoutTitleView = row.findViewById(R.id.workoutTitle);
        TextView workoutDescriptionView = row.findViewById(R.id.workoutDescription);

        workoutImageview.setImageResource(workoutImages[position]);
        workoutTitleView.setText(workoutTitle[position]);
        workoutDescriptionView.setText(workoutDescription[position]);

        return row;
    }
}