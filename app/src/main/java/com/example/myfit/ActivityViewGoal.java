package com.example.myfit;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class ActivityViewGoal extends AppCompatActivity {

    TextView viewTDate, viewTstart , viewTEnd , viewTStatus, viewTNote ;
    DatabaseReference dbRef;
   // EditText selectDate;
    Button btnDelete , btnSelectDate ;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_goal);

     //   btnSelectDate = findViewById(R.id.btnSelectDate);
        btnDelete = findViewById(R.id.btnDeleteGoal);

        //selectDate = findViewById(R.id.selectDate);
        viewTDate = findViewById(R.id.viewStartTime);
        viewTstart = findViewById(R.id.viewTstart);
        viewTEnd = findViewById(R.id.viewTEnd);
        viewTStatus = findViewById(R.id.viewTStatus);
        viewTNote = findViewById(R.id.viewTNote);

        initDatePicker();


           // final String enteredDate = selectDate.getText().toString().trim();

            DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Goal").child("goal1");
            readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChildren()){
                        viewTDate.setText(snapshot.child("date").getValue().toString());
                        viewTstart.setText(snapshot.child("startTime").getValue().toString());
                        viewTEnd.setText(snapshot.child("endTime").getValue().toString());
                        viewTStatus.setText(snapshot.child("status").getValue().toString());
                        viewTNote.setText(snapshot.child("note").getValue().toString());


                    }
                    else
                        Toast.makeText(getApplicationContext(),"No Source to display", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        btnDelete.setOnClickListener((view) ->{

            AlertDialog.Builder altdial = new AlertDialog.Builder(ActivityViewGoal.this);
            altdial.setMessage("Do you want to Delete this goal ???").setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Goal");
                            delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(snapshot.hasChild("goal1")){
                                        dbRef = FirebaseDatabase.getInstance().getReference().child("Goal").child("goal1");

                                        dbRef.removeValue();
                                        Toast.makeText(getApplicationContext(),"Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), ActivityGoal.class));
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
            alert.setTitle("Delete Goal");
            alert.show();

        });
    }

    private void clearControls(){
        viewTDate.setText("");
        viewTstart.setText("");
        viewTEnd.setText("");
        viewTStatus.setText("");
        viewTNote.setText("");
    }


    public void onClBack(View view) {
        startActivity(new Intent(this, ActivityGoal.class));
    }

    public void openDatePickers(View view) {
        datePickerDialog.show();
    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " / " + day + " / " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                // txtFrom.setText(date);
                //selectDate.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }
}