package com.example.bustracker;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class spinner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_spinner);
        Spinner l = findViewById(R.id.spinner);
        ArrayList<String> arrNames = new ArrayList<>();
        arrNames.add("Harish");
        arrNames.add("Raghu");
        arrNames.add("rishyendra");
        arrNames.add("vignesh");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_spinner_dropdown_item,arrNames);
        l.setAdapter(adapter);
    }
}