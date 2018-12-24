package com.eran.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button Reg;
    private  Button log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Reg=(Button)findViewById(R.id.register_btn);
        Button log=(Button)findViewById(R.id.log_btn);
        Reg.setOnClickListener(this);
        log.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        if (v==Reg)
        {
            startActivity(new Intent(MainActivity.this,register.class));

        }
        if (v==log)
        {
            startActivity(new Intent(MainActivity.this,Login.class));

        }
    }
}

