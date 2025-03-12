package com.example.bustracker;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;

import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;

public class MapsActivity extends AppCompatActivity {
    private MapView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        map = findViewById(R.id.map);

        RotationGestureOverlay rotationGestureOverlay = new RotationGestureOverlay(map);
        rotationGestureOverlay.setEnabled(true);
        map.getOverlays().add(rotationGestureOverlay);


        map.setMapOrientation(0.0f);
        Button btnRecenter = findViewById(R.id.btn_recenter);

        btnRecenter.setOnClickListener(v -> {
            IMapController mapController = map.getController();
            GeoPoint currentLocation = new GeoPoint(17.3850, 78.4867); // Your location
            mapController.animateTo(currentLocation);
            mapController.setZoom(15);  // Zoom into the location
        });

        map.setTileSource(TileSourceFactory.MAPNIK);  // Default OSM (Fastest)
        map.setMultiTouchControls(true); // Mutli Touch
        map.getController().setZoom(15.0); // deafualt zoom
        map.getController().setCenter(new GeoPoint(17.443978734448677, 78.7160820426824)); // Default Ghatkesar
        map.setMinZoomLevel(7.0);  //minimum zoom
        map.setTilesScaledToDpi(true); // Improve zoom clarity
    }

}
