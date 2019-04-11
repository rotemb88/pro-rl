package com.eran.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class searchad extends AppCompatActivity {
    private ListView list;
    private AdAdapter adAdapter;
    private Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchad);

        list = findViewById(R.id.list);

        adAdapter = new AdAdapter(this, R.layout.admini);
        //adAdapter.add(new Ad("מיצי", "", "חתול"));
        list.setAdapter(adAdapter);

        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://projectrl-a891a.firebaseio.com/ad");

        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                adAdapter.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {




                    Ad ad = new Ad(""+snapshot.child("name").getValue() , "" , ""+snapshot.child("type").getValue());
                    adAdapter.add(ad);


                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent i = new Intent(searchad.this , )
            }
        });




    }
}
