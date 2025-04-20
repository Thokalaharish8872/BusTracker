package com.example.bustracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class BusMap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bus_map);

        TextView NumberPlate = findViewById(R.id.NumberPlate);
        TextView stops = findViewById(R.id.stops);
        TextView Start = findViewById(R.id.Start);
        TextView BusNumber = findViewById(R.id.BusNumber);
        TextView StartTime =  findViewById(R.id.StartTime);
        TextView EndTime = findViewById(R.id.EndTime);
        TextView ArrivingTime = findViewById(R.id.ArrivingTime);

        Intent i = getIntent();

        if(i!=null){

            NumberPlate.setText(i.getStringExtra("NumberPlate"));
            stops.setText("stops : "+i.getStringExtra("stops"));
            Start.setText(i.getStringExtra("Start"));
            BusNumber.setText(i.getStringExtra("BusNumber"));
            StartTime.setText(i.getStringExtra("StartTime"));
            EndTime.setText(i.getStringExtra("EndTime"));
            ArrivingTime.setText(i.getStringExtra("ArrivingTime"));

        }


    }
}