package com.example.myfit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class ActivityAddChart extends AppCompatActivity {

    EditText txtSchedName , txtFrom , txtTo ,txtChart;
    Button btnAdd;
    DatabaseReference dbRef;
    Chart chart;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chart);


        txtSchedName = findViewById(R.id.viewSchName);
        txtFrom = findViewById(R.id.viewFrDate);
        txtTo = findViewById(R.id.viewTDate);
        txtChart = findViewById(R.id.viewChrt);

        btnAdd = findViewById(R.id.btnUpdtChart);

        chart = new Chart();
        txtFrom.setText(getTodaysDate());

        initDatePicker();

        //Add
        btnAdd.setOnClickListener((view) ->{
            dbRef = FirebaseDatabase.getInstance().getReference().child("Chart");

            if(TextUtils.isEmpty(txtSchedName.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please enter a Schedule Name..", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(txtFrom.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please enter the Starting Date..", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(txtTo.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please enter the Ending Date..", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(txtChart.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please enter a Chart..", Toast.LENGTH_SHORT).show();
                else {
                    chart.setSchedName(txtSchedName.getText().toString().trim());
                    chart.setFrom(txtFrom.getText().toString().trim());
                    chart.setTo(txtTo.getText().toString().trim());
                    chart.setChart(txtChart.getText().toString().trim());

                    //dbRef.push().setValue(std);
                    dbRef.child("chart1").setValue(chart);

                    Toast.makeText(getApplicationContext(),"The Chart successfully Added..", Toast.LENGTH_LONG).show();
                    //clearControls();
                    startActivity(new Intent(getApplicationContext(), ActivityWorkout.class));

                }
        });


    }

    public void onCl(View view) {
        startActivity(new Intent(this, ActivityWorkout.class));
    }

    private void clearControls(){
        txtSchedName.setText("");
        txtFrom.setText("");
        txtTo.setText("");
        txtChart.setText("");
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
                txtTo.setText(date);
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