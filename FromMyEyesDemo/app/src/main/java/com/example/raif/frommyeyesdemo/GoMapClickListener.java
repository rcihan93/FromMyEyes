package com.example.raif.frommyeyesdemo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Raif on 20.03.2017.
 */

public class GoMapClickListener implements View.OnClickListener {

    private DataModel data;
    private double latitude,longitude;
    private Context context;

    public GoMapClickListener(Context context, DataModel data) {
        this.data = data;
        this.context=context;
    }

    @Override
    public void onClick(View v) {

        latitude=data.getLocationX();
        longitude=data.getLocationY();
        Intent i=new Intent(context,MapsActivity.class);
        LatLng sydney = new LatLng(latitude, longitude);
        i.putExtra("Lat",sydney);
        context.startActivity(i);

    }
}
