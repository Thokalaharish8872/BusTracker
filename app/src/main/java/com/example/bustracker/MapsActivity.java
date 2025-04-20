package com.example.bustracker;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity {
    private MapView map;
    GeoPoint defaultLocation = new GeoPoint(17.43728302410395, 78.71752824592916); // Ghatkesar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        // Initialize osmdroid configuration
        Configuration.getInstance().load(getApplicationContext(),
                androidx.preference.PreferenceManager.getDefaultSharedPreferences(MapsActivity.this));

        map = findViewById(R.id.map);

        relocate();

        // Enable rotation gestures
        RotationGestureOverlay rotationGestureOverlay = new RotationGestureOverlay(map);
        rotationGestureOverlay.setEnabled(true);
        map.getOverlays().add(rotationGestureOverlay);

        map.setMaxZoomLevel(18.7);

        Marker marker = new Marker(map);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.bus_caartoon);
        Bitmap smallMarker = Bitmap.createScaledBitmap(bitmap,150,150,false);
        Drawable markerIcon = new BitmapDrawable(getResources(),smallMarker);
        marker.setIcon(markerIcon);
        marker.setAnchor(Marker.ANCHOR_CENTER,Marker.ANCHOR_CENTER);

        GeoPoint edulabad = new GeoPoint(17.42546029421507, 78.70995368767423);
        marker.setPosition(edulabad);
        map.getOverlays().add(marker);



        ArrayList<GeoPoint> route = new ArrayList<>();
        route.add(new GeoPoint(17.42546029421507, 78.70995368767423));
        route.add(new GeoPoint(17.4272619024869, 78.71178831866476));
        route.add(new GeoPoint(17.42824459041294, 78.71262516788852));
        route.add(new GeoPoint(17.43162253988145, 78.71474947741066));
        route.add(new GeoPoint(17.43331149120396, 78.71597256470332));
        route.add(new GeoPoint(17.43417131492453, 78.71654119302202));
        route.add(new GeoPoint(17.43728302410395, 78.71752824592916));


        Marker marker1 = new Marker(map);
        GeoPoint ace = new GeoPoint(17.43728302410395, 78.71752824592916);
        marker1.setPosition(ace);
        map.getOverlays().add(marker1);

        Polyline line = new Polyline();
        line.setPoints(route);
        line.setColor(getResources().getColor(R.color.red));
        line.setWidth(5.0f);

        map.getOverlays().add(line);

        // Enable multi-touch controls & zoom buttons
        map.setMultiTouchControls(true);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMinZoomLevel(7.0);

        // Recenter button functionality
        Button btnRecenter = findViewById(R.id.btn_recenter);
        btnRecenter.setOnClickListener(v -> {
            relocate();
        });
    }

    void relocate(){
        IMapController mapController = map.getController();
        mapController.setCenter(defaultLocation);
        mapController.setZoom(15.0);

    }
}

