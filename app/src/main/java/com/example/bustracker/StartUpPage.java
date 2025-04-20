package com.example.bustracker;

import android.app.Dialog;
import android.content.ClipData;
import android.media.RouteListingPreference;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class StartUpPage extends AppCompatActivity {
    ArrayList<data> arr = new ArrayList<>();
    ArrayList<String> arrNames = new ArrayList<>();
    FloatingActionButton refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start_up_page);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        refresh = findViewById(R.id.refresh);


        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
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
        AutoCompleteTextView search = findViewById(R.id.search);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,arrNames);
        search.setAdapter(adapter);
        search.setThreshold(1);

        BusAdapter busAdapter = new BusAdapter(StartUpPage.this,arr);
        recyclerView.setAdapter(busAdapter);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(StartUpPage.this);
                dialog.setContentView(R.layout.add_details);
                TextInputEditText CollegeName = dialog.findViewById(R.id.CollegeName);
                TextInputEditText BusNumber = dialog.findViewById(R.id.BusNumber);
                TextInputEditText Start = dialog.findViewById(R.id.Start);
                Button add = dialog.findViewById(R.id.addbtn);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!CollegeName.getText().toString().isEmpty()){
                            if(!BusNumber.getText().toString().isEmpty()) {
                                if (!Start.getText().toString().isEmpty()) {
                                    arr.add(new data(CollegeName.getText().toString(), Start.getText().toString(), BusNumber.getText().toString()));
                                    busAdapter.notifyItemInserted(arr.size() - 1);
                                    recyclerView.scrollToPosition(arr.size() - 1);
                                    dialog.dismiss();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"Start Route cannot be empty",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Student Id cannot be empty",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"College Name cannot be empty",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();

            }
        });
    }

}