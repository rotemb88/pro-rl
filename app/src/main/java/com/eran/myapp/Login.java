package com.eran.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login=(Button)findViewById(R.id.ok2button);

        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==login)
        {
            startActivity(new Intent(Login.this,menu.class));

        }
    }

}
