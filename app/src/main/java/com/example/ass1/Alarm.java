package com.example.ass1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Alarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Wake up",Toast.LENGTH_LONG).show();
        Log.d("myTag", "This is my message");


        /*PendingIntent notification = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("تذكير")
                .setContentText("قوم بسك نوم");

        builder.setContentIntent(notification);
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        builder.setAutoCancel(true);

        NotificationManager mm =( NotificationManager ) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mm.cancel(1);
        mm.notify(1, builder.build());*/
    }
}

//https://www.youtube.com/watch?v=QcF4M2yUpY4
//https://www.youtube.com/watch?v=_WNsjVBlZZg
//https://www.youtube.com/watch?v=qTmtCEw1ydw&t=3s