package com.eran.myapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class menu extends AppCompatActivity implements View.OnClickListener {

    ImageButton tipimage, add_ad,search;
    Button changepass,deleteuser;
    FirebaseUser mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        tipimage = (ImageButton) findViewById(R.id.imageButton);
        tipimage.setOnClickListener(this);

        search = (ImageButton) findViewById(R.id.searchad);
        search.setOnClickListener(this);


        add_ad = (ImageButton) findViewById(R.id.imageButton2);
        add_ad.setOnClickListener(this);

        changepass=(Button)findViewById(R.id.changepass);
        changepass.setOnClickListener(this);

        deleteuser=(Button)findViewById(R.id.deleteuser);
        deleteuser.setOnClickListener(this);

        mAuth=FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public void onClick(View v) {

        if (v==tipimage)
        {
            startActivity(new Intent(menu.this,tips.class));

        }
        if(v==add_ad)
        {
            startActivity(new Intent(menu.this,addnew.class));

        }
        if(v==search)
        {
            startActivity(new Intent(menu.this,searchad.class));

        }
        if(v==changepass)
        {
            startActivity(new Intent(menu.this,changepass.class));

        }

        if(v==deleteuser)
        {
            FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
            if(user!=null)
            {
                user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(), "המשתמש הוסר בהצלחה", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(menu.this,MainActivity.class));


                        }

                    }
                });
            }

        }

    }

}
