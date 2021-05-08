package com.example.myfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class ActivityViewChart extends AppCompatActivity {

    TextView viewScheduleName, viewFromDate , viewToDate , viewChart;
    DatabaseReference dbRef;
    Button btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_chart);

        btnDelete = findViewById(R.id.btnDeleteChart);


        viewScheduleName = findViewById(R.id.viewSchName);
        viewFromDate = findViewById(R.id.viewFromDate);
        viewToDate = findViewById(R.id.viewToDate);
        viewChart = findViewById(R.id.viewChart);



            DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Chart").child("chart1");
            readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChildren()){
                        viewScheduleName.setText(snapshot.child("schedName").getValue().toString());
                        viewFromDate.setText(snapshot.child("from").getValue().toString());
                        viewToDate.setText(snapshot.child("to").getValue().toString());
                        viewChart.setText(snapshot.child("chart").getValue().toString());
                    }
                    else
                        Toast.makeText(getApplicationContext(),"No Source to display", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        btnDelete.setOnClickListener((view) ->{

            AlertDialog.Builder altdial = new AlertDialog.Builder(ActivityViewChart.this);
            altdial.setMessage("Do you want to Delete this chart ???").setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Chart");
                            delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(snapshot.hasChild("chart1")){
                                        dbRef = FirebaseDatabase.getInstance().getReference().child("Chart").child("chart1");

                                        dbRef.removeValue();
                                        Toast.makeText(getApplicationContext(),"Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), ActivityWorkout.class));
                                    }
                                    else
                                        Toast.makeText(getApplicationContext(),"No Source to delete", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert = altdial.create();
            alert.setTitle("Dialog Header");
            alert.show();


                /*DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Chart");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild("chart1")){
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Chart").child("chart1");

                            dbRef.removeValue();
                            Toast.makeText(getApplicationContext(),"Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else
                            Toast.makeText(getApplicationContext(),"No Source to delete", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/

            });
        }

    private void clearControls(){
        viewScheduleName.setText("");
        viewFromDate.setText("");
        viewToDate.setText("");
        viewChart.setText("");
    }


    public void onClBack(View view) {
        startActivity(new Intent(this, ActivityWorkout.class));
    }

    public void onClUpdate(View view) {startActivity(new Intent(this,ActivityUpdate.class)); }

   /* public void dialogevent(){
        AlertDialog.Builder altdial = new AlertDialog.Builder(ActivityViewChart.this);
        altdial.setMessage("Do you want to Quit this app ???").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = altdial.create();
        alert.setTitle("Dialog Header");
        alert.show();

    }

*/
}