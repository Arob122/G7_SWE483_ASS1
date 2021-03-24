package com.example.ass1;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Random;


public class Alarm extends BroadcastReceiver {

    private NotificationManagerCompat notificationManger;
    public int getRandom(){
        Random random = new Random();
        int m = random.nextInt(9999 - 1000) + 1000;
        return m;
    }
    @Override
    public void onReceive(Context context, Intent inten) {
        String title = "", msg = "", imp = "";
        int myYear=0, myMonth=0, myday=0, myHour=0, myMinute=0;
        int rand = 0;

        Log.d("myTag", "This is my message");

        int random=getRandom();


        Bundle extras = inten.getExtras();
        if (extras != null) {
            Log.d("myTag", "in extras");
            title = extras.getString("title");
            Log.d("myTag", "in extras title "+title);
            msg = extras.getString("msg");
            Log.d("myTag", "in extras msg "+msg);
            imp = extras.getString("importence");
            Log.d("myTag", "in extras importence "+imp);
            rand = extras.getInt("random");
            Log.d("myTag", "in extras random "+rand);
            myYear = extras.getInt("myYear");
            Log.d("myTag", "in extras myYear "+myYear);
            myMonth = extras.getInt("myMonth");
            Log.d("myTag", "in extras myMonth "+myMonth);
            myday = extras.getInt("myday");
            Log.d("myTag", "in extras myday "+myday);
            myHour = extras.getInt("myHour");
            Log.d("myTag", "in extras myHour "+myHour);
            myMinute = extras.getInt("myMinute");
            Log.d("myTag", "in extras myMinute "+myMinute);
            
            //The key argument here must match that used in the other activity
        }
        
        notificationManger = NotificationManagerCompat.from(context);
        Intent intent=new Intent(context, MainActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("msg", msg);
        intent.putExtra("importence", imp);
        intent.putExtra("myYear", myYear);// year
        intent.putExtra("myMonth", myMonth);// month
        intent.putExtra("myday", myday);// day
        intent.putExtra("myHour", myHour);// houer
        intent.putExtra("myMinute", myMinute);// mint
        PendingIntent pendingIntent = PendingIntent.getActivity(context, rand+1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        



        if (imp.equals("high")) {
            Log.d("myTag", "in class Alarm high ");
            Notification notification = new NotificationCompat.Builder(context, MainActivity.CHANNEL_1_ID)
                    .setSmallIcon(R.drawable.ic_high_alarm)
                    .setContentTitle(title)
                    .setContentText(msg)
                    .setContentIntent(pendingIntent)
                    .build();



            notificationManger.notify(random,notification );


        }else {
            Log.d("myTag", "in class Alarm low ");
            Notification notification = new NotificationCompat.Builder(context, MainActivity.CHANNEL_2_ID)
                    .setSmallIcon(R.drawable.ic_low_alarm_24)
                    .setContentTitle(title)
                    .setContentText(msg)
                    .setContentIntent(pendingIntent)
                    .build();



            notificationManger.notify(random,notification );

        }
    }

}

//https://www.youtube.com/watch?v=QcF4M2yUpY4
//https://www.youtube.com/watch?v=_WNsjVBlZZg
//https://www.youtube.com/watch?v=qTmtCEw1ydw&t=3s