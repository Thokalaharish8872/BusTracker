package com.example.bustracker;

import android.os.Bundle;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.bustracker.databinding.ActivityForBusBinding;

import java.util.ArrayList;

public class ActivityForBus extends AppCompatActivity {

    GoogleMaps googleMapsFragment;
    BusInfo busInfoFragment;
    static boolean flag = false;

    FragmentTransaction fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_for_bus);

        googleMapsFragment = new GoogleMaps();
        busInfoFragment = new BusInfo();


        LinearLayout fullRoute = findViewById(R.id.fullRoute);
        LinearLayout busInfo = findViewById(R.id.BusInfo);

        fullRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        busInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }
    private void setFragment(Fragment fragment){

        fm = getSupportFragmentManager().beginTransaction();
        if(flag) fm.replace(R.id.frame,fragment);

        else{
            fm.add(R.id.frame,fragment);
            flag = !flag;
        }


        fm.commit();
    }
}