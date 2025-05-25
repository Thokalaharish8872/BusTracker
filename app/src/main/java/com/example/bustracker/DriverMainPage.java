package com.example.bustracker;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class DriverMainPage extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_CODE = 1001;
    DatabaseReference database;
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationCallback locationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_driver_main_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button start = findViewById(R.id.btnStart);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_CODE);
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(DriverMainPage.this);

        database = FirebaseDatabase.getInstance().getReference("Users").child("Bus").child("08").child("Location");

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (start.getText().equals("Share Location")) {
                    start.setText("Stop Sharing");
                    com.google.android.gms.location.LocationRequest locationRequest = new LocationRequest();
                    locationRequest.setInterval(5000);
                    locationRequest.setFastestInterval(2000);
                    locationRequest.setPriority(Priority.PRIORITY_HIGH_ACCURACY);

                     locationCallback = new LocationCallback() {
                        @Override
                        public void onLocationResult(@NonNull LocationResult locationResult) {
                            super.onLocationResult(locationResult);

//                        for(Location location: locationResult.getLocations()){

                            LatLng latLng = new LatLng(locationResult.getLastLocation().getLatitude(), locationResult.getLastLocation().getLongitude());

                            double sp = Double.parseDouble(locationResult.getLastLocation().getSpeed() + "");
//                        Toast.makeText(DriverMainPage.this,latLng.latitude+" "+latLng.longitude,Toast.LENGTH_SHORT).show();
                            database.child("latitude").setValue(latLng.latitude);
                            database.child("longitude").setValue(latLng.longitude);
                            database.child("speed").setValue(sp);

//                        }


                        }
                    };

                    if (ActivityCompat.checkSelfPermission(DriverMainPage.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(DriverMainPage.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                        return;
                    }
                    fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
                }
                else{
                    start.setText("Share Location");
                    fusedLocationProviderClient.removeLocationUpdates(locationCallback);

                }
            }
        });
    }
}