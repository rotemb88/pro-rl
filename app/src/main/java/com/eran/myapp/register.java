package com.eran.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class register extends AppCompatActivity implements View.OnClickListener {

    private Button mainact;
    private Spinner spinnerState;
    FirebaseDatabase database;
    DatabaseReference databaseReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mainact=(Button)findViewById(R.id.okbutton);
        mainact.setOnClickListener(this);
        spinnerState = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.listStates));
        spinnerState.setAdapter(adapter);



    }


    @Override
    public void onClick(View v) {
        if (v==mainact)
        {
            startActivity(new Intent(register.this,MainActivity.class));
        }
    }


}
