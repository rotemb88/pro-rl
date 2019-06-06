package com.eran.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class searchad extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ListView list;
    private AdAdapter adAdapter;
    private Firebase firebase;
    private Spinner searchtype,searcharea;
    String todayAsString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchad);

        searchtype=findViewById(R.id.searchtype1);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.animal,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        searchtype.setAdapter(adapter);
        searchtype.setOnItemSelectedListener(this);

        searcharea=findViewById(R.id.searcharea1);
        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this,R.array.area,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        searcharea.setAdapter(adapter2);
        searcharea.setOnItemSelectedListener(this);

        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://projectrl-a891a.firebaseio.com/");

        String pattern = "MM-dd-yyyy HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = Calendar.getInstance().getTime();
        todayAsString = df.format(today);


        list = findViewById(R.id.list);

        adAdapter = new AdAdapter(this, R.layout.admini);
        //adAdapter.add(new Ad("מיצי", "", "חתול"));
        list.setAdapter(adAdapter);

        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://projectrl-a891a.firebaseio.com/ad");

     /*   firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                adAdapter.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {


                    Ad ad = new Ad(""+snapshot.child("name").getValue() , snapshot.child("image").getValue()+"" , ""+snapshot.child("type").getValue(),""+snapshot.child("Purpose").getValue(),""+snapshot.child("age").getValue(),
                            ""+snapshot.child("information").getValue(),"" +snapshot.child("city").getValue(),
                            ""+snapshot.child("race").getValue(),""+snapshot.child("training").getValue(),""+snapshot.child("state").getValue(),
                            ""+snapshot.child("gender").getValue() ,""+snapshot.child("date").getValue(),""+snapshot.child("firstname").getValue(),""+snapshot.child("lastname").getValue(),""+snapshot.child("email").getValue(),""+snapshot.child("phone").getValue());
                    adAdapter.add(ad);


                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });*/


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent i = new Intent(searchad.this , )
            }
        });




    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String typestring=parent.getItemAtPosition(position).toString();
        switch (parent.getId())
        {
            case R.id.searchtype1:

                if(position==0)
                {
                    firebase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            adAdapter.clear();

                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {


                                Ad ad = new Ad("" + snapshot.child("name").getValue(), snapshot.child("image").getValue() + "", "" + snapshot.child("type").getValue(), "" + snapshot.child("Purpose").getValue(), "" + snapshot.child("age").getValue(),
                                        "" + snapshot.child("information").getValue(), "" + snapshot.child("city").getValue(),
                                        "" + snapshot.child("race").getValue(), "" + snapshot.child("training").getValue(), "" + snapshot.child("state").getValue(),
                                        "" + snapshot.child("gender").getValue(), "" + snapshot.child("date").getValue(), "" + snapshot.child("firstname").getValue(), "" + snapshot.child("lastname").getValue(), "" + snapshot.child("email").getValue(), "" + snapshot.child("phone").getValue());


                                adAdapter.add(ad);


                            }


                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });
                }
                if(position==1)
                {
                    firebase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            adAdapter.clear();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Ad ad = new Ad("" + snapshot.child("name").getValue(), snapshot.child("image").getValue() + "", "" + snapshot.child("type").getValue(), "" + snapshot.child("Purpose").getValue(), "" + snapshot.child("age").getValue(),
                                        "" + snapshot.child("information").getValue(), "" + snapshot.child("city").getValue(),
                                        "" + snapshot.child("race").getValue(), "" + snapshot.child("training").getValue(), "" + snapshot.child("state").getValue(),
                                        "" + snapshot.child("gender").getValue(), "" + snapshot.child("date").getValue(), "" + snapshot.child("firstname").getValue(), "" + snapshot.child("lastname").getValue(), "" + snapshot.child("email").getValue(), "" + snapshot.child("phone").getValue());

                                if (ad.getType().equals("כלב")) {

                                        adAdapter.add(ad);
                                }

                            }

                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });
                }
                if(position==2)
                {
                    firebase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            adAdapter.clear();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Ad ad = new Ad("" + snapshot.child("name").getValue(), snapshot.child("image").getValue() + "", "" + snapshot.child("type").getValue(), "" + snapshot.child("Purpose").getValue(), "" + snapshot.child("age").getValue(),
                                        "" + snapshot.child("information").getValue(), "" + snapshot.child("city").getValue(),
                                        "" + snapshot.child("race").getValue(), "" + snapshot.child("training").getValue(), "" + snapshot.child("state").getValue(),
                                        "" + snapshot.child("gender").getValue(), "" + snapshot.child("date").getValue(), "" + snapshot.child("firstname").getValue(), "" + snapshot.child("lastname").getValue(), "" + snapshot.child("email").getValue(), "" + snapshot.child("phone").getValue());

                                if (ad.getType().equals("חתול")) {

                                    adAdapter.add(ad);
                                }

                            }

                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });
                }
                if(position==3)
                {
                    firebase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            adAdapter.clear();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Ad ad = new Ad("" + snapshot.child("name").getValue(), snapshot.child("image").getValue() + "", "" + snapshot.child("type").getValue(), "" + snapshot.child("Purpose").getValue(), "" + snapshot.child("age").getValue(),
                                        "" + snapshot.child("information").getValue(), "" + snapshot.child("city").getValue(),
                                        "" + snapshot.child("race").getValue(), "" + snapshot.child("training").getValue(), "" + snapshot.child("state").getValue(),
                                        "" + snapshot.child("gender").getValue(), "" + snapshot.child("date").getValue(), "" + snapshot.child("firstname").getValue(), "" + snapshot.child("lastname").getValue(), "" + snapshot.child("email").getValue(), "" + snapshot.child("phone").getValue());

                                if (ad.getType().equals("חמוס")) {

                                    adAdapter.add(ad);
                                }

                            }

                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });
                }
                if(position==4)
                {
                    firebase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            adAdapter.clear();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Ad ad = new Ad("" + snapshot.child("name").getValue(), snapshot.child("image").getValue() + "", "" + snapshot.child("type").getValue(), "" + snapshot.child("Purpose").getValue(), "" + snapshot.child("age").getValue(),
                                        "" + snapshot.child("information").getValue(), "" + snapshot.child("city").getValue(),
                                        "" + snapshot.child("race").getValue(), "" + snapshot.child("training").getValue(), "" + snapshot.child("state").getValue(),
                                        "" + snapshot.child("gender").getValue(), "" + snapshot.child("date").getValue(), "" + snapshot.child("firstname").getValue(), "" + snapshot.child("lastname").getValue(), "" + snapshot.child("email").getValue(), "" + snapshot.child("phone").getValue());

                                if (ad.getType().equals("תוכים ובעלי כנף")) {

                                    adAdapter.add(ad);
                                }

                            }

                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });
                }
                if(position==5)
                {
                    firebase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            adAdapter.clear();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Ad ad = new Ad("" + snapshot.child("name").getValue(), snapshot.child("image").getValue() + "", "" + snapshot.child("type").getValue(), "" + snapshot.child("Purpose").getValue(), "" + snapshot.child("age").getValue(),
                                        "" + snapshot.child("information").getValue(), "" + snapshot.child("city").getValue(),
                                        "" + snapshot.child("race").getValue(), "" + snapshot.child("training").getValue(), "" + snapshot.child("state").getValue(),
                                        "" + snapshot.child("gender").getValue(), "" + snapshot.child("date").getValue(), "" + snapshot.child("firstname").getValue(), "" + snapshot.child("lastname").getValue(), "" + snapshot.child("email").getValue(), "" + snapshot.child("phone").getValue());

                                if (ad.getType().equals("מכרסמים")) {

                                    adAdapter.add(ad);
                                }

                            }

                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });
                }
            case R.id.searcharea1:
                if(position==0)
                {
                    firebase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            adAdapter.clear();

                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {


                                Ad ad = new Ad("" + snapshot.child("name").getValue(), snapshot.child("image").getValue() + "", "" + snapshot.child("type").getValue(), "" + snapshot.child("Purpose").getValue(), "" + snapshot.child("age").getValue(),
                                        "" + snapshot.child("information").getValue(), "" + snapshot.child("city").getValue(),
                                        "" + snapshot.child("race").getValue(), "" + snapshot.child("training").getValue(), "" + snapshot.child("state").getValue(),
                                        "" + snapshot.child("gender").getValue(), "" + snapshot.child("date").getValue(), "" + snapshot.child("firstname").getValue(), "" + snapshot.child("lastname").getValue(), "" + snapshot.child("email").getValue(), "" + snapshot.child("phone").getValue());


                                adAdapter.add(ad);


                            }


                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });
                }
                if(position==1)
                {
                    firebase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            adAdapter.clear();

                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {


                                Ad ad = new Ad("" + snapshot.child("name").getValue(), snapshot.child("image").getValue() + "", "" + snapshot.child("type").getValue(), "" + snapshot.child("Purpose").getValue(), "" + snapshot.child("age").getValue(),
                                        "" + snapshot.child("information").getValue(), "" + snapshot.child("city").getValue(),
                                        "" + snapshot.child("race").getValue(), "" + snapshot.child("training").getValue(), "" + snapshot.child("state").getValue(),
                                        "" + snapshot.child("gender").getValue(), "" + snapshot.child("date").getValue(), "" + snapshot.child("firstname").getValue(), "" + snapshot.child("lastname").getValue(), "" + snapshot.child("email").getValue(), "" + snapshot.child("phone").getValue());

                                if (ad.getState().equals("צפון")) {

                                    adAdapter.add(ad);
                                }



                            }


                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });
                }




        }




    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
