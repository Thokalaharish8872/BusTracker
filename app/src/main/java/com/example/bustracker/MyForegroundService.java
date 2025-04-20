package com.example.bustracker;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MyForegroundService extends Service {

    @SuppressLint("ForegroundServiceType")
    public void onCreate(){
        super.onCreate();
        startForeground(1,createNotification());
    }

    private Notification createNotification(){
        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("CHANNEL","New Notification", NotificationManager.IMPORTANCE_HIGH);

            NotificationManager manager = getSystemService(NotificationManager.class);

            manager.createNotificationChannel(channel);

            notification = new Notification.Builder(MyForegroundService.this,"CHANNEL")
                    .setSmallIcon(R.drawable.bus_caartoon)
                    .setContentTitle("You got a new Notification")
                    .setContentText("Hello")
                    .build();
        }
        return notification;

    }

    public int onStartCommand(Intent intent, int flags, int startId) {

        stopSelf();
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
