package com.eran.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class register extends AppCompatActivity implements View.OnClickListener {

    private Button mainact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mainact=(Button)findViewById(R.id.okbutton);

        mainact.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v==mainact)
        {
            startActivity(new Intent(register.this,MainActivity.class));
        }
    }
}
