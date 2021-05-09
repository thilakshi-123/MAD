package com.example.myfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityGoal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);



    }

    public void onCl(View view) {
        startActivity(new Intent(this, ActivityAddGoal.class));
    }


    public void onCl4(View view) { startActivity(new Intent(this, ActivityViewGoal.class));}

    public void onCl5(View view) {startActivity(new Intent(this, ActivityChronometer.class));  }

    public void onClBack(View view) { startActivity(new Intent(this, ActivityHome.class)); }
}