package com.example.bustracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SpashScreen extends AppCompatActivity {
    Class nextActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash_screen);

        ImageView bus = findViewById(R.id.bus);

        SharedPreferences sharedPreferences = getSharedPreferences("Users",MODE_PRIVATE);
        if(sharedPreferences.getBoolean("IsLoggedIn", false)){
            switch (sharedPreferences.getInt("UserType",0)){
                case 0 : nextActivity = UserTypeActivity.class; break;
                case 1 : nextActivity = DriverMainPage.class; break;
                case 2 : nextActivity = ActivityForFragments.class; break;
                case 4 : nextActivity = ActivityForAdministrator.class; break;
                default: nextActivity = SpashScreen.class;
            }
        }
        else nextActivity = UserTypeActivity.class;


        android.view.animation.Animation translate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate);
        bus.startAnimation(translate);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                android.view.animation.Animation alpha = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate2);
                bus.startAnimation(alpha);
            }
        },1520);
    h.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SpashScreen.this,nextActivity));
                finish();
            }
        },2000);
    }
}