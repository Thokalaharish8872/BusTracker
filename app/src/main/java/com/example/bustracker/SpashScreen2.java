package com.example.bustracker;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.L;

public class SpashScreen2 extends AppCompatActivity {

    static TextView text, small;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_spash_screen2);

        ImageView logo = findViewById(R.id.logo);

        ImageView name = findViewById(R.id.name);

//        name.setAlpha(1f);

        ObjectAnimator obj = ObjectAnimator.ofPropertyValuesHolder(logo, PropertyValuesHolder.ofFloat("scaleX",  3f, 1f),PropertyValuesHolder.ofFloat("scaleY",3f,1f));
        obj.setDuration(1000);
        obj.start();
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {

                Animation animation = AnimationUtils.loadAnimation(SpashScreen2.this,R.anim.text);
                logo.startAnimation(animation);

                name.setAlpha(1f);
                Animation animation1 = AnimationUtils.loadAnimation(SpashScreen2.this, android.R.anim.fade_in);
                name.startAnimation(animation1);
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Animation animation = AnimationUtils.loadAnimation(SpashScreen2.this,R.anim.text2);
                        logo.startAnimation(animation);

                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Animation animation = AnimationUtils.loadAnimation(SpashScreen2.this,R.anim.text3);
                                logo.startAnimation(animation);

                                h.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        ObjectAnimator obj2 = ObjectAnimator.ofPropertyValuesHolder(logo, PropertyValuesHolder.ofFloat("scaleX",  1f, 50f),PropertyValuesHolder.ofFloat("scaleY",1f,50f));
                                        obj2.setDuration(600);
                                        obj2.start();
                                        h.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent i = new Intent(SpashScreen2.this,ActivityForFragments.class);
                                                startActivity(i);

                                            }
                                        },400);


                                    }
                                },700);
                            }
                        },900);

                    }
                },700);
            }
        },1500);


    }
}
