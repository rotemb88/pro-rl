package com.eran.myapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class changepass extends AppCompatActivity implements View.OnClickListener {

    Button ok3;
    EditText passnew,passnew2;
    FirebaseAuth mAuth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);

        ok3=(Button)findViewById(R.id.ok3);
        ok3.setOnClickListener(this);
        passnew = (EditText) findViewById(R.id.passwordchange1);
        passnew2 = (EditText) findViewById(R.id.passwordchange22);
        mAuth=FirebaseAuth.getInstance();
        dialog=new ProgressDialog(this);


    }


    @Override
    public void onClick(View v) {
        if(v==ok3) {
            String p1,p2;
            p1=passnew.getText().toString();
           p2= passnew2.getText().toString();
            if (p1.equals(p2)) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    dialog.setMessage("please wait");
                    dialog.show();
                    user.updatePassword(passnew.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                dialog.dismiss();
                                startActivity(new Intent(changepass.this, menu.class));

                                Toast.makeText(getApplicationContext(), "הסיסמא שלך שונתה בהצלחה", Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(getApplicationContext(), "ישנה שגיאה , הסיסמא שלך לא שונתה. בדוק את מילוי הפרטים", Toast.LENGTH_LONG).show();;

                            }
                        }

                    });

                }

            }
            else
            {
                Toast.makeText(getApplicationContext(), " אין התאמה בין הסיסמאות", Toast.LENGTH_LONG).show();;

            }
        }

    }
}
