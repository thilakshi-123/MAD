package com.example.myfit;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class ActivityAddGoal extends AppCompatActivity {

    EditText txtDate, txtStartTime , txtEndTime , txtNote;
    Spinner txtStatus;
    Button btnAdd;
    DatabaseReference dbRef;
    Goal goal;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);

        txtDate = findViewById(R.id.viewDate);
        txtStartTime = findViewById(R.id.viewStartTime);
        txtEndTime = findViewById(R.id.viewEndTime);
        txtStatus = findViewById(R.id.viewStatus);
        txtNote = findViewById(R.id.viewNote);

        btnAdd = findViewById(R.id.btnAddGoal);

        goal = new Goal();

        txtDate.setText(getTodaysDate());

        initDatePicker();

        //Add
        btnAdd.setOnClickListener((view) ->{
            dbRef = FirebaseDatabase.getInstance().getReference().child("Goal");

            if(TextUtils.isEmpty(txtDate.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please pick the date..", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(txtStartTime.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter the Starting time..", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(txtEndTime.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter the Ended time..", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(txtStatus.getSelectedItem().toString()))
                Toast.makeText(getApplicationContext(), "Please enter the status..", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(txtNote.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter a note..", Toast.LENGTH_SHORT).show();
            else {
                goal.setDate(txtDate.getText().toString().trim());
                goal.setStartTime(txtStartTime.getText().toString().trim());
                goal.setEndTime(txtEndTime.getText().toString().trim());
                goal.setStatus(txtStatus.getSelectedItem().toString().trim());
                goal.setNote(txtNote.getText().toString().trim());

                //dbRef.push().setValue(goal);
               dbRef.child("goal1").setValue(goal);

                Toast.makeText(getApplicationContext(),"The Goal successfully Added..", Toast.LENGTH_LONG).show();
                //clearControls();
                startActivity(new Intent(getApplicationContext(), ActivityGoal.class));

            }
        });


    }

    public void onCl(View view) {
        startActivity(new Intent(this, ActivityGoal.class));
    }

    private void clearControls(){
        txtDate.setText("");
        txtStartTime.setText("");
        txtEndTime.setText("");
        txtStatus.setSelection(1);
        txtNote.setText("");
    }

    public void onCl3(View view) {
        clearControls();
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
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
                txtDate.setText(date);
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

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }
}