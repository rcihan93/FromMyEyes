package com.example.raif.frommyeyesdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Base64;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

/**
 * Created by Raif on 20.03.2017.
 */

public class DataModel {
    //private Bitmap mImage;
    double locationX;
    double locationY;
    String bitmapBase64;

    public DataModel() {}

    public DataModel(double locationX,double locationY) {
        this.locationX=locationX;
        this.locationY=locationY;
    }

    public DataModel(Bitmap mImage,double locationX,double locationY){
        //this.mImage=mImage;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        mImage.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            bitmapBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
        }

        this.locationX=locationX;
        this.locationY=locationY;
    }

    public  String getBitmapBase64(){
        return bitmapBase64;
    }

    public Bitmap getmImage(){
        if(bitmapBase64==null)
            return null;

        byte[] decodedBytes = new byte[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.FROYO) {
            decodedBytes = Base64.decode(bitmapBase64, 0);
        }
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    public double getLocationX(){
        return locationX;
    }

    public double getLocationY(){
        return locationY;
    }

    public void setLocationX(double locationX) {
        this.locationX = locationX;
    }

    public void setLocationY(double locationY) {
        this.locationY = locationY;
    }

    public void setBitmapBase64(String bitmapBase64) {
        this.bitmapBase64 = bitmapBase64;
    }
}
