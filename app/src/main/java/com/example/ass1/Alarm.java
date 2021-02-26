package com.example.ass1;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class Alarm extends BroadcastReceiver {

    private NotificationManagerCompat notificationManger;
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Wake up",Toast.LENGTH_LONG).show();
        Log.d("myTag", "This is my message");

        notificationManger = NotificationManagerCompat.from(context);
        String title = "Hi";
        String msg = "Iam notification";
        Intent i = new Intent(context,MainActivity.class);
        Notification notification = new NotificationCompat.Builder(context, MainActivity.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.button)
                .setContentTitle(title)
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();


        notificationManger.notify(1,notification );

    }

}

//https://www.youtube.com/watch?v=QcF4M2yUpY4
//https://www.youtube.com/watch?v=_WNsjVBlZZg
//https://www.youtube.com/watch?v=qTmtCEw1ydw&t=3s