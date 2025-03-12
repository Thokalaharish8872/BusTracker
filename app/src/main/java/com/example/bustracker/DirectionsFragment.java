package com.example.bustracker;

import android.app.Dialog;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class DirectionsFragment extends Fragment {
    ArrayList<data> arr = new ArrayList<>();
    ArrayList<String> arrNames = new ArrayList<>();
    FloatingActionButton refresh;
    ArrayList<data> array = new ArrayList<>();

    public DirectionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_directions, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        arr.add(new data("Ace Engineering College","Uppal","10"));
        arr.add(new data("Ace Engineering College","Swarnagairi","09"));
        arr.add(new data("Ace Engineering College","Ramnagar","12"));
        arr.add(new data("Ace Engineering College","Tarnaka","05"));
        arr.add(new data("Ace Engineering College","Nampally","07"));
        arr.add(new data("Ace Engineering College","Miyapur","13"));
        arr.add(new data("VBIT","Uppal","18"));
        arr.add(new data("VBIT","Miyapur","16"));
        arr.add(new data("VBIT","LB Nagar","02"));
        arr.add(new data("VBIT","Narapally","08"));
        arr.add(new data("VBIT","Tarnaka","01"));
        arr.add(new data("VBIT","Stadium","23"));
        arr.add(new data("VBIT","Cherlapally","21"));
        arr.add(new data("VBIT","Bibi Nagar","20"));
        arr.add(new data("VBIT","Yadagiri","23"));
        arr.add(new data("VBIT","Ghatkesar","17"));
        arr.add(new data("VBIT","Tarnaka","19"));
        arr.add(new data("VBIT","Uppal","25"));
        arr.add(new data("VBIT","Miyapur","27"));
        arr.add(new data("VBIT","Stadium","14"));
        arr.add(new data("VBIT","Yadagiri","07"));
        arr.add(new data("VBIT","Tarnaka","12"));
        arr.add(new data("VBIT","Secundrabad","29"));
        arr.add(new data("VBIT","Nampally","28"));

        arrNames.add("Uppal - Ankushapur    10");
        arrNames.add("Ghatkesar - Ankushapur    09");
        arrNames.add("LB Nagar - Ankushapur     12");
        arrNames.add("JNTUH - Ankushapur    05");
        arrNames.add("ECIL - Ankushapur     07");
        arrNames.add("Bhongir - Ankushapur     13");
        arrNames.add("Yadagiri - Ankushapur     18");
        arrNames.add("Narapally - Ankushapur    16");
        arrNames.add("Cherlapally - Ankushapur  02");
        arrNames.add("Tarnaka - Ankushapur      08");
        arrNames.add("Stadium - Ankushapur      01");
        arrNames.add("Miyapur - Ankushapur      23");
        arrNames.add("Secundrabad - Ankushapur     21");
        arrNames.add("Nagole - Ankushapur       20");
        arrNames.add("KPHP - Ankushapur     11");
        arrNames.add("Madhapur - Ankushapur     17");
        arrNames.add("Amberpet - Ankushapur     19");
        arrNames.add("Ameerpet - Ankushapur     25");
        arrNames.add("OldCity - Ankushapur      27");
        arrNames.add("Habsiguda - Ankushapur        14");
        arrNames.add("Mettuguda - Ankushapur        07");
        arrNames.add("NGRI - Ankushapur     03");
        arrNames.add("Bagumpet - Ankushapur     29");
        arrNames.add("Bharat Nagar - Ankushapur     28");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,arrNames);

        array.add(new data("Uppal","01","TS19A6514","8:25","9:20","08","30"));
        array.add(new data("Tarnaka","02","TS01A9310","7:30","9:15","12","28"));
        array.add(new data("Bhongir","03","AP17A6314","6:50","9:30","16","22"));
        array.add(new data("Stadium","04","TS13A6734","6:55","8:50","18","57"));
        array.add(new data("ECIL","05","TS06A5574","7:20","8:45","20","23"));
        array.add(new data("Yadagiri","06","AP12A6514","7:50","9:30","06","45"));
        array.add(new data("Secundrabad","07","AP02A6274","8:10","9:30","12","12"));
        array.add(new data("JNTU","08","TS14A3467","8:25","9:20","15","15"));
        array.add(new data("Madhapur","09","TS07A1654","7:40","9:05","07","32"));
        array.add(new data("Durgam Cheruvu","10","AP17A9845","6:55","9:25","18","38"));
        array.add(new data("Nagole","11","TS13A1265","7:55","9:15","15","34"));
        array.add(new data("Raidurg","12","TS01A7634","8:00","9:30","08","25"));
        array.add(new data("Stadium","13","AP19A6514","8:25","8:50","12","29"));
        array.add(new data("Uppal","14","TS19A6514","7:30","8:45","08","30"));
        array.add(new data("Tarnaka","15","TS01A9310","6:50","9:30","20","39"));
        array.add(new data("Bhongir","16","AP17A6314","6:55","9:30","06","46"));
        array.add(new data("Stadium","17","TS13A6734","7:20","9:20","12","08"));
        array.add(new data("ECIL","18","TS06A5574","7:50","9:05","15","03"));
        array.add(new data("Yadagiri","19","AP12A6514","8:10","9:25","07","26"));
        array.add(new data("Secundrabad","20","AP02A6274","8:25","9:30","18","48"));
        array.add(new data("JNTU","21","TS14A3467","7:40","9:20","15","56"));
        array.add(new data("Madhapur","22","TS07A1654","6:55","9:15","12","39"));
        array.add(new data("Durgam Cheruvu","23","AP17A9845","7:55","9:30","16","59"));
        array.add(new data("Nagole","24","TS13A1265","8:25","8:50","18","03"));
        array.add(new data("Raidurg","25","TS01A7634","7:30","8:45","20","09"));
        array.add(new data("Staduim","26","TS19A6514","6:50","9:30","06","18"));
        array.add(new data("Uppal","27","TS01A9310","6:55","9:30","12","25"));
        array.add(new data("Tarnaka","29","AP17A6314","7:20","9:20","15","32"));
        array.add(new data("Stadium","28","TS13A6734","7:50","9:05","07","22"));
        array.add(new data("ECIL","30","TS06A5574","8:10","9:25","18","53"));
        array.add(new data("Yadagairi","31","AP12A6514","8:25","9:30","15","50"));
        array.add(new data("Secundrabad","32","AP02A6274","7:40","9:30","08","46"));
        array.add(new data("JNTU","33","TS14A3467","6:55","9:20","12","06"));
        array.add(new data("Madhapur","34","TS07A1654","7:55","9:15","16","39"));
        array.add(new data("Durgam Cheruvu","35","AP17A9845","8:25","9:30","18","17"));
        array.add(new data("Nagole","36","TS19A6514","7:30","8:50","20","15"));
        array.add(new data("Raidurg","37","TS01A9310","6:50","8:45","06","30"));
        array.add(new data("Stadium","38","AP17A6314","6:55","9:30","12","27"));
        array.add(new data("Uppal","39","TS13A6734","7:20","9:20","15","59"));
        array.add(new data("Tarnaka","40","TS06A5574","7:50","9:05","07","28"));
        array.add(new data("Stadium","41","AP12A6514","8:10","9:25","08","39"));
        array.add(new data("ECIL","42","AP02A6274","8:25","9:30","12","47"));
        array.add(new data("Uppal","43","TS14A3467","7:40","9.20","16","01"));
        array.add(new data("Yadagiri","44","TS07A1654","6:55","8:55","18","49"));
        array.add(new data("ECIL","45","AP17A9845","7:55","9:30","20","59"));

        BusAdapter_for_Search busAdapterForSearch = new BusAdapter_for_Search(getContext(),array);

        recyclerView.setAdapter(busAdapterForSearch);
        return v;
    }
    
}