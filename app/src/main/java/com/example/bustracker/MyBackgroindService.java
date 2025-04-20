package com.example.bustracker;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.security.Provider;

public class MyBackgroindService extends Service {


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        stopSelf();
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}