package com.example.ass1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.ass1.R;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    TextView Title1,monnth1,day1,hour1,min1,yeear1;
    EditText Title;
    Button Pickbutton;
    int day, month, year, hour, minute;
    int myday, myMonth, myYear, myHour, myMinute;
    String enteredTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Title1 = findViewById(R.id.Title);
        Pickbutton = findViewById(R.id.btnPick);
        Title=findViewById(R.id.textE);
        monnth1=findViewById(R.id.monnth);
        day1=findViewById(R.id.day);
        hour1=findViewById(R.id.hour);
        min1=findViewById(R.id.min);
        yeear1=findViewById(R.id.yeear);
        enteredTitle = Title.getText().toString();


        Pickbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,MainActivity.this,year, month,day);
                datePickerDialog.show();
            }
        });
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        myYear = year;
        myday = dayOfMonth;
        myMonth = month+1;
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, MainActivity.this, hour, minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        myHour = hourOfDay;
        myMinute = minute;
        yeear1.setText(myYear+ "\n" );
        monnth1.setText( myMonth + "\n") ;
        day1.setText( myday + "\n") ;
        hour1.setText( myHour + "\n") ;
        min1.setText( myMinute + "\n") ;
        // "Day: " + myday + "\n" +
        // "Hour: " + myHour + "\n" +
        // "Minute: " + myMinute);
        /*yeear1.setText(myYear);
        monnth1.setText(myMonth);
        daay1.setText(myday);
        hourrs1.setText(myHour);
        miins1.setText(myMinute);*/
        Title1.setText(enteredTitle);
    }
}