package com.eran.myapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button login;
    FirebaseAuth mAuth;
    EditText emailEdit, passEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=(Button)findViewById(R.id.ok2button);
        login.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        emailEdit = (EditText) findViewById(R.id.emailtxt);
        passEdit = (EditText) findViewById(R.id.passwordtxt);

    }

    private void loginUser()
    {

        String email = emailEdit.getText().toString().trim();
        String password = passEdit.getText().toString().trim();
        mAuth.signInWithEmailAndPassword(email,password);

        //Toast.makeText(this, mAuth.getUid(), Toast.LENGTH_SHORT).show();
        SharedPreferences sp = getSharedPreferences("DocPro" , MODE_PRIVATE);
        sp.edit().putString("userid" , mAuth.getUid());


        if (email.isEmpty())
        {
            emailEdit.setError("email is required");
            emailEdit.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            emailEdit.setError("please enter a valid email");
            emailEdit.requestFocus();
            return;
        }

        if (password.isEmpty())
        {
            passEdit.setError("password is required");
            passEdit.requestFocus();
            return;
        }

        if(password.length()<6)
        {
            passEdit.setError("minimum lenght of password should be 6");
            passEdit.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    startActivity(new Intent(Login.this,menu.class));
                }
                else
                {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v==login)
        {
            loginUser();

        }
    }

}
