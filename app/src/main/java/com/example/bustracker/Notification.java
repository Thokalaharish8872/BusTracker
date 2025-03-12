package com.example.bustracker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Notification extends AppCompatActivity {
    private static final String CHANNEL_ID = "CHANNEL";
//    private static final int NOTIFICATION_ID = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notification);
        Button getNotification = findViewById(R.id.GetNotification);
        getNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                NotificationChannel nc = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    nc = new NotificationChannel(CHANNEL_ID,"My Notification", NotificationManager.IMPORTANCE_HIGH);
                    nm.createNotificationChannel(nc);
                    android.app.Notification nf = new android.app.Notification.Builder(Notification.this)
                            .setSmallIcon(R.drawable.bus_logo)
                            .setChannelId(CHANNEL_ID)
                            .setContentTitle("Chats")
                            .setContentText("Hey how are You")
                            .build();
                    nm.notify(100,nf);
                }



            }
        });

    }
}