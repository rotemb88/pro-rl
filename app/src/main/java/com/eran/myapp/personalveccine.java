package com.eran.myapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class personalveccine extends AppCompatActivity {
    private Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalveccine);

        final String type = getIntent().getStringExtra("type");
        final SharedPreferences sp = getSharedPreferences("DocPro" , MODE_PRIVATE);
        final String userid = sp.getString("userid" ,"");

        Firebase.setAndroidContext(this);

        if (type.equals("חתול")) {
            firebase = new Firebase("https://projectrl-a891a.firebaseio.com/users/" + userid + "/personal Vaccination program/Cat");
        } else {
            firebase = new Firebase("https://projectrl-a891a.firebaseio.com/users/" + userid + "/personal Vaccination program/Dog");

        }

        Log.e("talll the type: " , type);
        Log.e("talll the userid : " , userid);
        //Log.e("the snap : " + firebase.getValue() , "the data: " + dataSnapshot.getValue());

        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(snapshot.getValue());
            //        ((TextView) findViewById(R.id.textView3)).setText(("החיסונים שעשה:" + stringBuilder));
                    ((TextView) findViewById(R.id.textView3)).setText("חיסונים שעשה:\n");
                    ((TextView) findViewById(R.id.textView3)).setText(((TextView) findViewById(R.id.textView3)).getText() + "\n" + snapshot.getValue());

                    //Log.e("talll the v : " , snapshot.getValue()+"");

                }

            }







            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
}
