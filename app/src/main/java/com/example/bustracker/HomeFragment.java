package com.example.bustracker;

import static android.app.NotificationManager.IMPORTANCE_HIGH;
import static android.content.Context.NOTIFICATION_SERVICE;

import static androidx.core.content.ContextCompat.getSystemService;
import static com.google.maps.model.PlaceDetails.Review.AspectRating.RatingType.SERVICE;

import android.app.Dialog;
import android.app.Notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ArrayList<data> arr = new ArrayList<>();
    ArrayList<String> arrNames = new ArrayList<>();
    FloatingActionButton refresh;
    String CHANNEL_ID = "MYCHANNEL";
    Fragment fragment = new ProfileFragment();
    DatabaseReference reference ;
    String isFeePaid;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ui_for_home_fragment, container, false);

        CardView cardView = v.findViewById(R.id.cardView);

        SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("Users", Context.MODE_PRIVATE);

        String userName = sharedPreferences.getString("userName","Harish");

        reference = FirebaseDatabase.getInstance().getReference("Users").child("Students").child(userName);

        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(!isAdded()) return;
                if(task.getResult().exists()){
                    isFeePaid =  task.getResult().child("isFeePaid").getValue(String.class);
                    if(isFeePaid.equals("Yes") || isFeePaid.equals("Partially Paid")){
                        cardView.setBackgroundColor(getResources().getColor(R.color.paleGreen));
                    }
                    else{
                        cardView.setBackgroundColor(getResources().getColor(R.color.red));

                    }
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("isFeePaid",isFeePaid);

                    editor.apply();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
        ImageView profile = v.findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.FrameLayout,fragment);

                fragmentTransaction.commit();

            }
        });









//        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
//        refresh = v.findViewById(R.id.refresh);
//        ImageButton Menubtn = v.findViewById(R.id.MenuBtn);

//        Menubtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getContext(),DriverMainPage.class));
//            }
//        });

//        Menubtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(),"Opened Menu",Toast.LENGTH_SHORT).show();
//                DrawerView.more();
//            }
//        });

//        refresh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NotificationManager nm = (NotificationManager) getContext().getSystemService(NOTIFICATION_SERVICE);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    NotificationChannel channel = new NotificationChannel("CHANNEL ID","HELLO",IMPORTANCE_HIGH);
//                    Notification notification = new Notification.Builder(getContext())
//                            .setContentTitle("Harish")
//                            .setContentText("Hi, how are you")
//                            .setChannelId("CHANNEL ID")
//                            .setSmallIcon(R.drawable.exit_icon)
//                            .build();
//                    nm.createNotificationChannel(channel);
//                    nm.notify(100,notification);
//                }
//            }
//        });


//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        arr.add(new data("Ace Engineering College","Uppal","10"));
//        arr.add(new data("Ace Engineering College","Swarnagairi","09"));
//        arr.add(new data("Ace Engineering College","Ramnagar","12"));
//        arr.add(new data("Ace Engineering College","Tarnaka","05"));
//        arr.add(new data("Ace Engineering College","Nampally","07"));
//        arr.add(new data("Ace Engineering College","Miyapur","13"));
//        arr.add(new data("VBIT","Uppal","18"));
//        arr.add(new data("VBIT","Miyapur","16"));
//        arr.add(new data("VBIT","LB Nagar","02"));
//        arr.add(new data("VBIT","Narapally","08"));
//        arr.add(new data("VBIT","Tarnaka","01"));
//        arr.add(new data("VBIT","Stadium","23"));
//        arr.add(new data("VBIT","Cherlapally","21"));
//        arr.add(new data("VBIT","Bibi Nagar","20"));
//        arr.add(new data("VBIT","Yadagiri","23"));
//        arr.add(new data("VBIT","Ghatkesar","17"));
//        arr.add(new data("VBIT","Tarnaka","19"));
//        arr.add(new data("VBIT","Uppal","25"));
//        arr.add(new data("VBIT","Miyapur","27"));
//        arr.add(new data("VBIT","Stadium","14"));
//        arr.add(new data("VBIT","Yadagiri","07"));
//        arr.add(new data("VBIT","Tarnaka","12"));
//        arr.add(new data("VBIT","Secundrabad","29"));
//        arr.add(new data("VBIT","Nampally","28"));
//
//
//
//        arrNames.add("Uppal - Ankushapur    10");
//        arrNames.add("Ghatkesar - Ankushapur    09");
//        arrNames.add("LB Nagar - Ankushapur     12");
//        arrNames.add("JNTUH - Ankushapur    05");
//        arrNames.add("ECIL - Ankushapur     07");
//        arrNames.add("Bhongir - Ankushapur     13");
//        arrNames.add("Yadagiri - Ankushapur     18");
//        arrNames.add("Narapally - Ankushapur    16");
//        arrNames.add("Cherlapally - Ankushapur  02");
//        arrNames.add("Tarnaka - Ankushapur      08");
//        arrNames.add("Stadium - Ankushapur      01");
//        arrNames.add("Miyapur - Ankushapur      23");
//        arrNames.add("Secundrabad - Ankushapur     21");
//        arrNames.add("Nagole - Ankushapur       20");
//        arrNames.add("KPHP - Ankushapur     11");
//        arrNames.add("Madhapur - Ankushapur     17");
//        arrNames.add("Amberpet - Ankushapur     19");
//        arrNames.add("Ameerpet - Ankushapur     25");
//        arrNames.add("OldCity - Ankushapur      27");
//        arrNames.add("Habsiguda - Ankushapur        14");
//        arrNames.add("Mettuguda - Ankushapur        07");
//        arrNames.add("NGRI - Ankushapur     03");
//        arrNames.add("Bagumpet - Ankushapur     29");
//        arrNames.add("Bharat Nagar - Ankushapur     28");
//
//
//        AutoCompleteTextView search = v.findViewById(R.id.search);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,arrNames);
//        search.setAdapter(adapter);
//        search.setThreshold(1);
//
//        BusAdapter busAdapter = new BusAdapter(getContext(),arr);
//        recyclerView.setAdapter(busAdapter);

        return v;
    }
}