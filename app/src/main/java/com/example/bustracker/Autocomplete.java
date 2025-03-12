package com.example.bustracker;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Autocomplete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_autocomplete);
        AutoCompleteTextView l = findViewById(R.id.autoComplete);
        ArrayList<String> arrNames = new ArrayList<>();
        arrNames.add("Harish");
        arrNames.add("Raghu");
        arrNames.add("rishyendra");
        arrNames.add("vignesh");
        arrNames.add("Harishith");
        arrNames.add("Raghava");
        arrNames.add("rakesh");
        arrNames.add("vinay");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1,arrNames);
        l.setAdapter(adapter);
        l.setThreshold(1);
    }
}