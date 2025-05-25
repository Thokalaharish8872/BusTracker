package com.example.bustracker;

import static android.app.NotificationManager.IMPORTANCE_HIGH;
import static android.content.Context.NOTIFICATION_SERVICE;
import static android.content.Intent.getIntent;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
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
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.google.android.material.bottomsheet.BottomSheetBehavior;
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
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
// Add this import
import com.google.android.gms.maps.model.Polyline;

public class GoogleMaps extends Fragment implements OnMapReadyCallback {

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
    private double speed;
    private final int CHANNEL_ID = 100;
    private final String ID = "LIVE BUS UPDATES";
    Handler handler;
    Double result;
    BottomSheetBehavior bottomSheetBehavior;



    ArrayList<LatLng> points;
    private Polyline currentPolyline; // ⬅️ added for live polyline update

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.activity_google_maps, container, false);



        View bottomSheet = v.findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        bottomSheetBehavior.setPeekHeight(400); // Peek height in collapsed state
        bottomSheetBehavior.setHideable(false);
        bottomSheetBehavior.setDraggable(true); // REQUIRED to make it draggable


//        ImageView call_logo = v.findViewById(R.id.call_logo);
//        ImageView driver_logo = v.findViewById(R.id.driver_logo);
//        ImageView bus_logo = v.findViewById(R.id.bus_logo);
//        call_logo.setColorFilter(getResources().getColor(R.color.green));
//        bus_logo.setColorFilter(getResources().getColor(R.color.white));
//        driver_logo.setColorFilter(getRe
//        LinearLayout fullRoute = v.findViewById(R.id.fullRoute);
//        LinearLayout busInfo = v.findViewById(R.id.BusInfo);



        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                // Handle state change
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
                // Handle drag progress

            }
        });



        RecyclerView recyclerView = v.findViewById(R.id.recyclerView1);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Lb Nagar");
        arrayList.add("Uppal");
        arrayList.add("Tarnaka");
        arrayList.add("Ghatkesar");
        arrayList.add("Raigir");
        arrayList.add("Ankushapur");
        arrayList.add("ECIL");
        arrayList.add("Stadium");
        arrayList.add("BodUppal");
        arrayList.add("Annojiguda");
        arrayList.add("Secundrabad");

        StopsAdapter adapter = new StopsAdapter(arrayList,getContext());

        recyclerView.setAdapter(adapter);


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        atLoc = v.findViewById(R.id.atLoc);
        busNumber = v.findViewById(R.id.busNumber);
        arrivingTime = v.findViewById(R.id.ArrivingTime);
        distance = v.findViewById(R.id.distance);

        Intent i = getActivity().getIntent();
        if (i != null) {
            origin = new LatLng(Double.parseDouble(i.getStringExtra("stLat")), Double.parseDouble(i.getStringExtra("stLon")));
            Log.d("error","came");
            waypoints = i.getStringArrayExtra("waypoints");
            point = i.getStringExtra("points");
        }

        studentEmail = "23ag1a6939@gmail.com";
        database = FirebaseDatabase.getInstance().getReference("Users").child("Bus").child("08").child("Location");
        userPath = FirebaseDatabase.getInstance().getReference("Users").child("students").child(studentEmail.substring(0,studentEmail.length()-10).toUpperCase()).child("location");

        Log.d("error","came2");

//        handler = new Handler();

//        handler();

        mapFragment.getMapAsync(this);

        return v;
    }

    private void handler(){

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sendNotification();
                handler();
            }
        },30000);

    }

    private void sendNotification() {

        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(ID,"MY NOTIFICATION",IMPORTANCE_HIGH);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext())
                    .setSmallIcon(R.drawable.bus_caartoon)
                    .setChannelId(ID)
                    .setContentTitle("Bus Update")
                    .setContentText("Live Updates")
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText("Arriving in " + arrivingTime.getText() + "\n" +
                                    "Distance " + (result/1000) + "\n" +
                                    "Speed " + speed))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);


            notificationManager.createNotificationChannel(channel);
            notificationManager.notify(CHANNEL_ID,builder.build());
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("error","came3");
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_CODE);
        } else {
            Log.d("error","came4");

            startLocationUpdates();
            Log.d("error","came5");

        }

        busMarker = createNewMarker(origin, "Bus Location");

        Log.d("error","came6");

        collegeMarker = createNewMarker(destination, "Ace Engineering College");

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(origin, 15));

        Log.d("error","came7");

        points = new ArrayList<>();
        points.addAll(decodePolyline(point));
        Log.d("error","came8");

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

//                for (Location location : locationResult.getLocations()) {
                    LatLng latLng = new LatLng(Objects.requireNonNull(locationResult.getLastLocation()).getLatitude(), locationResult.getLastLocation().getLongitude());

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
//                            speed = snapshot.child("speed").getValue(Float.class);
                            speed = 0.5f;
                            latLng1 = new LatLng(lat, lon);
                            location1.setLatitude(destination.latitude);
                            location1.setLongitude(destination.longitude);

                            if (busMarker == null)
                                busMarker = createNewMarker(latLng1, "Bus Location");
                            else busMarker.setPosition(latLng1);

                            if(getContext() != null) {
                                busMarker.setIcon(getBitmapDescriptor(R.drawable.bus_marker));
                                userMarker.setAnchor(org.osmdroid.views.overlay.Marker.ANCHOR_CENTER, org.osmdroid.views.overlay.Marker.ANCHOR_CENTER);


                                drawRemainingPolyline(latLng1);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });

                }
//            }
        };

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) return;

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }

    private void drawRemainingPolyline(LatLng currentLocation) {
        List<LatLng> fullRoute = decodePolyline(point);
        List<LatLng> remainingRoute = getRemainingRoute(fullRoute, currentLocation);


        result =  calculateRouteDistance(remainingRoute);

        if(speed>0) {
            Double time = result/speed;

            time /= 60;


            if(time > 1000f){
                arrivingTime.setText("At Stop");

            }
            else {
                arrivingTime.setText(String.format("%.1f", time) + " min");
            }
            distance.setText(String.format("%.1f",(result/1000f)) + " km");
            busNumber.setText("08");
        }
        else{
            distance.setText("not moving");
            arrivingTime.setText("not moving");
            busNumber.setText("08");
        }


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

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (fusedLocationClient != null && locationCallback != null)
//            fusedLocationClient.removeLocationUpdates(locationCallback);
//    }

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

    public double calculateRouteDistance(List<LatLng> points) {
        double totalDistance = 0.0;

        for (int i = 0; i < points.size() - 1; i++) {
            LatLng start = points.get(i);
            LatLng end = points.get(i + 1);

            float[] results = new float[1];
            Location.distanceBetween(
                    start.latitude, start.longitude,
                    end.latitude, end.longitude,
                    results
            );

            totalDistance += results[0]; // meters
        }

        return totalDistance; // convert to km
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