package com.example.ass1;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.allyants.notifyme.NotifyMe;

import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    TextView Title1,monnth1,day1,hour1,min1,yeear1;
    EditText Title,detais;
    Button Pickbutton, saveBtn,lowImportance, highImportance;
    int day, month, year, hour, minute;
    int myday, myMonth, myYear, myHour, myMinute;
    String enteredTitle;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";
    NotificationManager manager;
    static String importence;


    private Calendar calendar;//for notify
    private PendingIntent pendingIntent;//for the notify
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNotificationChannels();
        calendar = Calendar.getInstance();
        setContentView(R.layout.activity_main);
        Title1 = findViewById(R.id.Title);
        Pickbutton = findViewById(R.id.btnPick);
        saveBtn = findViewById(R.id.saveBtn);
        lowImportance = findViewById(R.id.lowImportance);
        highImportance = findViewById(R.id.highImportance);
        Title=findViewById(R.id.textE);
        detais=findViewById(R.id.detail);
        monnth1=findViewById(R.id.monnth);
        day1=findViewById(R.id.day);
        hour1=findViewById(R.id.hour);
        min1=findViewById(R.id.min);
        yeear1=findViewById(R.id.yeear);
        enteredTitle = Title.getText().toString();
        importence="";

        setData();
        Pickbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(MainActivity.this,MainActivity.this,year, month,day);
                datePickerDialog.show();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Title.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Empty Title, please fill title", Toast.LENGTH_LONG).show();
                    return;
                }
                if(importence.equals("")){
                    Toast.makeText(getApplicationContext(), "choose importance", Toast.LENGTH_LONG).show();
                    return;
                }
                if(yeear1.getText().toString().equals("")||
                        monnth1.getText().toString().equals("")||
                        day1.getText().toString().equals("")||
                        hour1.getText().toString().equals("")||
                        min1.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "fill date & time", Toast.LENGTH_LONG).show();
                    return;
                }
                setAlarmNotify();
                Toast.makeText(getApplicationContext(), "Notification saved", Toast.LENGTH_LONG).show();
                yeear1.setText("");
                monnth1.setText("");
                day1.setText("");
                hour1.setText("");
                min1.setText("");
                Title.setText("");
                detais.setText("");


                lowImportance.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                highImportance.setBackgroundColor(Color.parseColor("#00FFFFFF"));

            }

        });


        highImportance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                importence= "high";
                highImportance.setBackgroundColor(Color.parseColor("#FF9795"));
                lowImportance.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            }
        });
        lowImportance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                importence= "low";
                lowImportance.setBackgroundColor(Color.parseColor("#FFE00D"));
                highImportance.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            }
        });

    }

    @Override
    protected void onResume() {

        super.onResume();
        setData();

    }


    @Override
    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        setIntent(intent);

    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        myYear = year;
        myday = dayOfMonth;
        myMonth = month+1;

        //to use in notify
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        //end

        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(MainActivity.this, MainActivity.this, hour, minute, DateFormat.is24HourFormat(this));
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
        Title1.setText(enteredTitle);


        //to use in notify
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);//HOUR_OF_DAY
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        //end


    }


    public int getRandom(){
        Random random = new Random();
        int m = random.nextInt(9999 - 1000) + 1000;
        return m;
    }

    public void setAlarmNotify(){

        int random = getRandom();

        Intent intent=new Intent(MainActivity.this, Alarm.class);
        String title = Title.getText().toString();
        String msg = detais.getText().toString();
        intent.putExtra("title", title);
        intent.putExtra("msg", msg);
        intent.putExtra("importence", importence);
        intent.putExtra("random", random);
        intent.putExtra("myYear", myYear);// year
        intent.putExtra("myMonth", myMonth);// month
        intent.putExtra("myday", myday);// day
        intent.putExtra("myHour", myHour);// houer
        intent.putExtra("myMinute", myMinute);// mint
        PendingIntent p1=PendingIntent.getBroadcast(MainActivity.this,random, intent,PendingIntent.FLAG_UPDATE_CURRENT);





        AlarmManager a=(AlarmManager)getSystemService(ALARM_SERVICE);
        a.set(AlarmManager.RTC,calendar.getTimeInMillis(),p1);


    }
    private void createNotificationChannels(){

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "My Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is Channel 1");

            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "My Channel 2",
                    NotificationManager.IMPORTANCE_LOW
            );
            channel2.setDescription("This is Channel 2");

            manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }
    }

    public void setData(){
        String title = "", msg = "", imp = "";

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            title = extras.getString("title");
            msg = extras.getString("msg");
            imp = extras.getString("importence");
            myYear = extras.getInt("myYear");
            myMonth = extras.getInt("myMonth");
            myday = extras.getInt("myday");
            myHour = extras.getInt("myHour");
            myMinute = extras.getInt("myMinute");

            //The key argument here must match that used in the other activity
            yeear1.setText(myYear+ "\n" );
            monnth1.setText( myMonth + "\n") ;
            day1.setText( myday + "\n") ;
            hour1.setText( myHour + "\n") ;
            min1.setText( myMinute + "\n") ;
            Title.setText(title);
            detais.setText(msg);

            if (imp.equals("high")) {
                highImportance.setBackgroundColor(Color.parseColor("#FF9795"));
                lowImportance.setBackgroundColor(Color.parseColor("#00FFFFFF"));

            }else{
                lowImportance.setBackgroundColor(Color.parseColor("#FFE00D"));
                highImportance.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            }

        }



    }


}
