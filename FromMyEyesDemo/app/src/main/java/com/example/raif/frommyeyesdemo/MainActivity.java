package com.example.raif.frommyeyesdemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raif.frommyeyesdemo.DataAccessLayer.FakeDataCreator;
import com.example.raif.frommyeyesdemo.DataAccessLayer.Preparing;

import java.util.Observable;
import java.util.Observer;


public class MainActivity extends AppCompatActivity implements Observer {
    private static final int CAMERA_REQUEST=1888;
    static final int REQUEST_TAKE_PHOTO = 1;
    private ListView timeLine;
    GPSTracker gps;
    private FakeDataCreator fakeDataCreator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeLine=(ListView)findViewById(R.id.timeLine);

        fakeDataCreator = new FakeDataCreator(this);
        fakeDataCreator.setObserver(this);
        fakeDataCreator.updateData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        //prepare=(ImageView)findViewById(R.id.prepairing);
    }

    @Override
    protected void onStop() {
        super.onStop();

        //prepare=(ImageView)findViewById(R.id.prepairing);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.camera:
                goCamera();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void goCamera(){
        Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent,CAMERA_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==CAMERA_REQUEST && resultCode== Activity.RESULT_OK){

            Intent intPre = new Intent(this, Preparing.class);

            Bitmap photo=(Bitmap) data.getExtras().get("data");
            //prepare.setImageBitmap(photo);
            intPre.putExtra("photo", photo);
            startActivity(intPre);

        }
    }


    @Override
    public void update(Observable o, Object arg) {
        CostumAdapter adapter = new CostumAdapter(fakeDataCreator.getDataModels(), this);

        timeLine.setAdapter(adapter);
    }
}
