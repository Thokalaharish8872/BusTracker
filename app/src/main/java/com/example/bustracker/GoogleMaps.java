package com.example.bustracker;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.maps.android.SphericalUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
// Add this import
import com.google.android.gms.maps.model.Polyline;

public class GoogleMaps extends FragmentActivity implements OnMapReadyCallback {

    static final int LOCATION_PERMISSION_CODE = 1001;
    // Existing declarations...
    private GoogleMap mMap;
    private Marker userMarker, busMarker, collegeMarker, frndMarker;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;
    private LatLng origin;
    private final LatLng destination = new LatLng(17.438034776806255, 78.71615921224529);
    String studentEmail, point;
    DatabaseReference database, userPath;
    private Location location1;
    private TextView atLoc, busNumber, arrivingTime, distance;
    String[] waypoints;
    LatLng latLng1;


    ArrayList<LatLng> points;
    private Polyline currentPolyline; // ⬅️ added for live polyline update

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        atLoc = findViewById(R.id.atLoc);
        busNumber = findViewById(R.id.busNumber);
        arrivingTime = findViewById(R.id.ArrivingTime);
        distance = findViewById(R.id.distance);

        Intent i = getIntent();
        if (i != null) {
            origin = new LatLng(Double.parseDouble(i.getStringExtra("stLat")), Double.parseDouble(i.getStringExtra("stLon")));
            waypoints = i.getStringArrayExtra("waypoints");
            point = i.getStringExtra("points");
        }

        studentEmail = getSharedPreferences("Users", MODE_PRIVATE).getString("userEmail", "noMail").replace(".", "_");
        database = FirebaseDatabase.getInstance().getReference("Users").child("Bus").child("08").child("Location");
        userPath = database.child(studentEmail).child("location");

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_CODE);
        } else {
            startLocationUpdates();
        }

        busMarker = createNewMarker(origin, "Bus Location");
        collegeMarker = createNewMarker(destination, "Ace Engineering College");

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(origin, 15));

        points = new ArrayList<>();
        points.addAll(decodePolyline(point));
    }

    private Marker createNewMarker(LatLng latLng, String title) {
        return mMap.addMarker(new MarkerOptions().position(latLng).title(title));
    }

    private void startLocationUpdates() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(5000);
        locationRequest.setPriority(Priority.PRIORITY_HIGH_ACCURACY);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                if (locationResult == null) return;

//                for (Location location : locationResult.getLocations()) {
                    LatLng latLng = new LatLng(locationResult.getLastLocation().getLatitude(), locationResult.getLastLocation().getLongitude());

                    if (userMarker == null) {
                        userMarker = createNewMarker(latLng, "Your Location");
                        userMarker.setIcon(getBitmapDescriptor(R.drawable.student_marker));
                        userMarker.setAnchor(org.osmdroid.views.overlay.Marker.ANCHOR_CENTER, org.osmdroid.views.overlay.Marker.ANCHOR_CENTER);

//                        updateLocationToFirebase(latLng);
                        location1 = new Location("");


                    } else {
                        userMarker.setPosition(latLng);
                    }

                database.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot != null) {
                            Double lat = snapshot.child("latitude").getValue(Double.class);
                            Double lon = snapshot.child("longitude").getValue(Double.class);
                            latLng1 = new LatLng(lat, lon);
                            location1.setLatitude(destination.latitude);
                            location1.setLongitude(destination.longitude);

                            if (busMarker == null)
                                busMarker = createNewMarker(latLng1, "Bus Location");
                            else busMarker.setPosition(latLng1);

                            busMarker.setIcon(getBitmapDescriptor(R.drawable.bus_marker));
                            userMarker.setAnchor(org.osmdroid.views.overlay.Marker.ANCHOR_CENTER, org.osmdroid.views.overlay.Marker.ANCHOR_CENTER);



                            drawRemainingPolyline(latLng1);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });

                }
//            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) return;

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }

    private void drawRemainingPolyline(LatLng currentLocation) {
        List<LatLng> fullRoute = decodePolyline(point);
        List<LatLng> remainingRoute = getRemainingRoute(fullRoute, currentLocation);

        // Remove previous polyline
        if (currentPolyline != null) currentPolyline.remove();

        PolylineOptions polylineOptions = new PolylineOptions()
                .addAll(remainingRoute)
                .width(15f)
                .color(getResources().getColor(R.color.red));

        currentPolyline = mMap.addPolyline(polylineOptions);
    }

    private void updateLocationToFirebase(LatLng latLng) {
        userPath.child("latitude").setValue(latLng.latitude);
        userPath.child("longitude").setValue(latLng.longitude);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (fusedLocationClient != null && locationCallback != null)
            fusedLocationClient.removeLocationUpdates(locationCallback);
    }

    // Polyline decoding (same)
    private ArrayList<LatLng> decodePolyline(String encoded) {
        ArrayList<LatLng> poly = new ArrayList<>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dLat = ((result & 1) != 0 ? ~(result >> 1) : result >> 1);
            lat += dLat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dLng = ((result & 1) != 0 ? ~(result >> 1) : result >> 1);
            lng += dLng;

            poly.add(new LatLng(lat / 1E5, lng / 1E5));
        }
        return poly;
    }

    public int getClosestPointIndex(List<LatLng> polyline, LatLng userLocation) {
        double minDistance = Double.MAX_VALUE;
        int closestIndex = 0;

        for (int i = 0; i < polyline.size(); i++) {
            double distance = SphericalUtil.computeDistanceBetween(userLocation, polyline.get(i));
            if (distance < minDistance) {
                minDistance = distance;
                closestIndex = i;
            }
        }
        return closestIndex;
    }

    public List<LatLng> getRemainingRoute(List<LatLng> fullRoute, LatLng currentLocation) {
        int startIndex = getClosestPointIndex(fullRoute, currentLocation);
        return fullRoute.subList(startIndex, fullRoute.size());
    }

    private BitmapDescriptor getBitmapDescriptor(int image) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), image);
        Bitmap smallMarker = Bitmap.createScaledBitmap(bitmap, 150, 150, false);
        return BitmapDescriptorFactory.fromBitmap(smallMarker);
    }
}