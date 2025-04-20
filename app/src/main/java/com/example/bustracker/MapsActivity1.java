package com.example.bustracker;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.Manifest;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MapsActivity1 extends AppCompatActivity {
    private FusedLocationProviderClient fusedLocationClient;
    private DatabaseReference database;
    private static final int LOCATION_PERMISSION_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps1);


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        database = FirebaseDatabase.getInstance().getReference("buses/bus_1").getDatabase().getReference();

        if (checkLocationPermission()) {
            startLocationUpdates();
        } else {
            requestLocationPermission();
        }
    }

    private void startLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create()
                .setInterval(5000)
                .setFastestInterval(3000)
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY);

        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                for (android.location.Location location : locationResult.getLocations()) {
                    saveLocationToFirebase(location.getLatitude(), location.getLongitude());
                }
            }
        };

        if (checkLocationPermission()) {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
        }
    }

    private void saveLocationToFirebase(double lat, double lon) {
        Map<String, Object> locationData = new HashMap<>();
        locationData.put("lat", lat);
        locationData.put("lon", lon);
        locationData.put("timestamp", System.currentTimeMillis());

        database.setValue(locationData);
    }

    private boolean checkLocationPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates();
            }
        }
    }
}
