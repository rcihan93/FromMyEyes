package com.example.raif.frommyeyesdemo.DataAccessLayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;

import com.example.raif.frommyeyesdemo.DataModel;
import com.example.raif.frommyeyesdemo.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Raif on 20.03.2017.
 */

public class FakeDataCreator extends Observable {

    private ArrayList<DataModel> dataModels;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("users");



    public FakeDataCreator(Context context)
    {
        dataModels = new ArrayList<DataModel>();

        Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.galatakulesi);
        Bitmap icon2 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.mevlana);

        observers = new ArrayList<Observer>();

        // burada fake veri oluşturulacak
        //dataModels.add(new DataModel(icon,41.0258727,28.9738067 ));
        //dataModels.add(new DataModel(icon2,37.870701,32.5028923 ));

        // Attach a listener to read the data at our posts reference


}

    public void updateData() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
/*
                for (DataSnapshot snap: dataSnapshot.getChildren()){
                    dataModels.add(snap.getValue(DataModel.class));
                }
*/
                if(dataSnapshot.getChildrenCount()==0)
                    return;

                GenericTypeIndicator<Map<String, DataModel>> t = new GenericTypeIndicator<Map<String, DataModel>>() {};
                //HashMap post = (HashMap) dataSnapshot.getValue();
                //List<DataModel> messages = dataSnapshot.getValue(t);
                Map<String, DataModel> map = dataSnapshot.getValue(t);
                dataModels = new ArrayList<DataModel>(map.values()); //(DataModel[]) map.values().addAll(dataModels)
                        //System.out.println(post);
                //dataModels = new ArrayList<DataModel>(post.values());
                updateNotify();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    private void updateNotify(){
        for (Observer obs:observers) {
            obs.update(this, dataModels);
        }
    }

    private List<Observer> observers;

    public void setObserver(Observer observer) {
        observers.add(observer);
    }

    public ArrayList<DataModel> getDataModels() {
        return dataModels;
    }
}
