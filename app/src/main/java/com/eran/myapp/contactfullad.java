package com.eran.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class contactfullad extends AppCompatActivity {
   // private DatabaseReference mDatabase,mDatabase2;
   // private TextView mNameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactfullad);

        String name2 = getIntent().getStringExtra("name");
        TextView name_animal2 = findViewById(R.id.nameanimal7);
        name_animal2.setText("שם החיה: " + name2);




    }
}
