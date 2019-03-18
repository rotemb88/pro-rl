package com.eran.myapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class register extends AppCompatActivity implements View.OnClickListener {

    private Button mainact;
    private Button Register;
    FirebaseDatabase database;
    DatabaseReference ref;
    EditText emailEdit, passEdit;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mainact=(Button)findViewById(R.id.okbutton);
        mainact.setOnClickListener(this);
        Register = (Button) findViewById(R.id.okbutton);
        emailEdit = (EditText) findViewById(R.id.emailtxt);
        passEdit = (EditText) findViewById(R.id.passwordtxt);
        mAuth = FirebaseAuth.getInstance();

    }

    public void registerUser()
    {
        String email = emailEdit.getText().toString().trim();
        String password = passEdit.getText().toString().trim();
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
            passEdit.setError("minimum lenght of password shuld be 6");
            passEdit.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(), "user registered successfull", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(getApplicationContext(),  "הנך כבר רשום למערכת", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
}

    @Override
    public void onClick(View v) {

        if (v==mainact)
        {
            registerUser();
            startActivity(new Intent(register.this,MainActivity.class));
        }
    }

    private class FirebaseDatabase {
    }

    private class DatabaseReference {
    }
}
