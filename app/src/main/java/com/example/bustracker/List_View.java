package com.example.bustracker;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class List_View extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_view);
        ListView l = findViewById(R.id.listview_1);
        ArrayList<String> arrNames = new ArrayList<>();
        arrNames.add("Harish");
        arrNames.add("Raghu");
        arrNames.add("rishyendra");
        arrNames.add("vignesh");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1,arrNames);
        l.setAdapter(adapter);
    }
}