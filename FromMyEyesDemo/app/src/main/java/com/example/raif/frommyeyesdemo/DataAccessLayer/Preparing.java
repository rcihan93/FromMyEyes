package com.example.raif.frommyeyesdemo.DataAccessLayer;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raif.frommyeyesdemo.DataModel;
import com.example.raif.frommyeyesdemo.GPSTracker;
import com.example.raif.frommyeyesdemo.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;


public class Preparing extends AppCompatActivity {

    private ImageView prepare;
    GPSTracker gps;
    private static final String TAG = Preparing.class.getSimpleName();

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preparing);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        prepare=(ImageView)findViewById(R.id.prepairingimg);
        final Bitmap preparingPhoto = getIntent().getParcelableExtra("photo");
        prepare.setImageBitmap(preparingPhoto);

        final TextView txtlongitude=(TextView)findViewById(R.id.txtlongitude);
        final TextView txtlatitude=(TextView)findViewById(R.id.txtlatitude);
        Button getLocation=(Button)findViewById(R.id.getGpsLocations);
        Button share=(Button)findViewById(R.id.share_btn);
        gps=new GPSTracker(this);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        // store app title to 'app_title' node
        mFirebaseInstance.getReference("app_title").setValue("Realtime Database");


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gps.canGetLocation()){
                    double longitude=gps.getLongitude();
                    double latitude=gps.getLatitude();
                    txtlongitude.setText(String.valueOf(longitude));
                    txtlatitude.setText(String.valueOf(latitude));
                    String txtLongitude=String.valueOf(longitude);
                    String txtLatitude=String.valueOf(latitude);

                    DataModel model=new DataModel(preparingPhoto, latitude, longitude);
                    String modelId=mFirebaseDatabase.push().getKey();
                    mFirebaseDatabase.child(modelId).setValue(model);

                    mFirebaseDatabase.child(modelId).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            Log.e(TAG, "User data is changed!");
                            Toast.makeText(getApplicationContext(),"Image uploaded",Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            Log.e(TAG, "Failed to read user", error.toException());
                        }
                    });
                }
                else
                    Toast.makeText(getApplicationContext(),"GPS Disabled",Toast.LENGTH_SHORT).show();
            }
        });



       getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gps.canGetLocation()){
                    double longitude=gps.getLongitude();
                    double latitude=gps.getLatitude();
                    txtlongitude.setText(String.valueOf(longitude));
                    txtlatitude.setText(String.valueOf(latitude));

                }
                else
                    Toast.makeText(getApplicationContext(),"GPS Disabled",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
