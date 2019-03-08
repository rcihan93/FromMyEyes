package com.example.raif.frommyeyesdemo;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by Raif on 20.03.2017.
 */

public class CostumAdapter extends BaseAdapter implements View.OnClickListener {

    private ArrayList<DataModel> dataSet;
    Context mContext;

    private LayoutInflater itemInf;
    private LinearLayout itemLay;

    public CostumAdapter(ArrayList<DataModel> data, Context context){
        //super(context,R.layout.showphoto,data);
        this.dataSet=data;
        this.mContext=context;
        itemInf = LayoutInflater.from(context);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        itemLay = (LinearLayout)itemInf.inflate(R.layout.showphoto, parent, false);

        ImageView image = (ImageView)itemLay.findViewById(R.id.imageView);

        image.setImageBitmap(dataSet.get(position).getmImage());

        Button btnGoMap = (Button) itemLay.findViewById(R.id.gomap);
        btnGoMap.setOnClickListener(new GoMapClickListener(mContext, dataSet.get(position)));

        return itemLay;
    }
}
