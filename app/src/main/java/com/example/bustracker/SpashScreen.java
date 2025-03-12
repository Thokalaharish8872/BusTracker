package com.example.bustracker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SpashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash_screen);
        Handler h = new Handler();
        Intent i = new Intent(getApplicationContext(),SpashScreen2.class);
        MobileDetailsData.getInstance(getApplicationContext());


        android.view.animation.Animation translate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate);
        ImageView bus = findViewById(R.id.bus);

        bus.startAnimation(translate);
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
                startActivity(i);
                finish();
            }
        },2000);
//
    }
}