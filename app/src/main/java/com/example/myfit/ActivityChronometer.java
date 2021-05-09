package com.example.myfit;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityChronometer extends AppCompatActivity {

    private Chronometer mChronometer;
    private Button start, stop, reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronometer);

        mChronometer = findViewById(R.id.chronometer);

        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        reset = findViewById(R.id.reset);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mChronometer.start();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // stop counting up.This does not effect thr best as set fromBase(long) , just view display
                mChronometer.stop();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mChronometer.setBase(SystemClock.elapsedRealtime());
            }
        });

    }
    public void onClBack(View view) {
        startActivity(new Intent(this, ActivityGoal.class));
    }

}
