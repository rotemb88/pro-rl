package com.eran.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class menu extends AppCompatActivity implements View.OnClickListener {

    ImageButton tipimage, addimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        tipimage = (ImageButton) findViewById(R.id.imageButton);
        tipimage.setOnClickListener(this);

        addimage = (ImageButton) findViewById(R.id.imageButton2);
        addimage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v==tipimage)
        {
            startActivity(new Intent(menu.this,tips.class));

        }
        if(v==addimage)
        {
            startActivity(new Intent(menu.this,add.class));

        }

    }

}
