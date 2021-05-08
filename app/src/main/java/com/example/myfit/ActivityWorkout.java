package com.example.myfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityWorkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);



    }

    public void onCl(View view) {
        startActivity(new Intent(this,ActivityAddChart.class));
    }

    public void onCl2(View view) {
        startActivity(new Intent(this,ActivityViewWorkout.class));
    }

    public void onCl4(View view) { startActivity(new Intent(this,ActivityViewChart.class));}
}