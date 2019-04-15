package com.eran.myapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.firebase.client.Firebase;


import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class profile extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {
    Button ok;//button for add details of person

    private Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final SharedPreferences sp = getSharedPreferences("DocPro" , MODE_PRIVATE);
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        sp.edit().putString("userid" , mAuth.getUid());

        ok=(Button)findViewById(R.id.okbutton) ;
        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://projectrl-a891a.firebaseio.com/");
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v==ok)
                {

                    EditText firstname = findViewById(R.id.firstname1);
                    EditText lastname = findViewById(R.id.lastname1);
                    EditText emailperson = findViewById(R.id.emailperson1);
                    EditText phoneperson = findViewById(R.id.phoneperson1);

                    firebase.child("users").child("firstname").setValue(firstname.getText().toString());
                    firebase.child("users").child("lastname").setValue(lastname.getText().toString());
                    firebase.child("users").child("mail").setValue(emailperson.getText().toString());
                    firebase.child("users").child("plephone").setValue(phoneperson.getText().toString());
                    firebase.child("users").child("iduser").setValue(mAuth.getUid());


                    startActivity(new Intent(profile.this,addnew.class));

                }
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
