package com.example.myfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityUpdate extends AppCompatActivity {

    TextView viewSchName, viewFrDate , viewTDate , viewChrt;

    Button btnUpdate;
    DatabaseReference dbRef;
    Chart chart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        btnUpdate = findViewById(R.id.btnUpdtChart);

        viewSchName = findViewById(R.id.viewSchName);
        viewFrDate = findViewById(R.id.viewFrDate);
        viewTDate = findViewById(R.id.viewTDate);
        viewChrt = findViewById(R.id.viewChrt);

        chart = new Chart();

        //Show
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Chart").child("chart1");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()){
                    viewSchName.setText(snapshot.child("schedName").getValue().toString());
                    viewFrDate.setText(snapshot.child("from").getValue().toString());
                    viewTDate.setText(snapshot.child("to").getValue().toString());
                    viewChrt.setText(snapshot.child("chart").getValue().toString());
                }
                else
                    Toast.makeText(getApplicationContext(),"No Source to display", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        //Update
        btnUpdate.setOnClickListener((view) ->{

            DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Chart");
            upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild("chart1")){
                            chart.setSchedName(viewSchName.getText().toString().trim());
                            chart.setFrom(viewFrDate.getText().toString().trim());
                            chart.setTo(viewTDate.getText().toString().trim());
                            chart.setChart(viewChrt.getText().toString().trim());


                            dbRef = FirebaseDatabase.getInstance().getReference().child("Chart").child("chart1");
                            dbRef.setValue(chart);

                            startActivity(new Intent(getApplicationContext(),ActivityViewChart.class));

                            Toast.makeText(getApplicationContext(), "Chart Successfully updated..!", Toast.LENGTH_LONG).show();

                    }
                    else
                        Toast.makeText(getApplicationContext(), "No source to update", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        });


    }

    public void onClBack(View view) { startActivity(new Intent(this,ActivityViewChart.class));}

}