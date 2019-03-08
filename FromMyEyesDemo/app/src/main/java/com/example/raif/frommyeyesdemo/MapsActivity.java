package com.example.raif.frommyeyesdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Raif on 27.03.2017.
 */

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_activity);
        SupportMapFragment  mapFragment=(SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;
        Intent i=getIntent();
        LatLng sydney =(LatLng) i.getParcelableExtra("Lat");
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker Located"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,12.0f));
    }
}
