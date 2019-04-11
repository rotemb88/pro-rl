package com.eran.myapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class add extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button selectImage, add,Vaccines;
    ImageButton profile;
    private ImageView imageView;
    private int REQUEST_CODE=1;
    Uri imageuri;
    private static final int PICK_IMAGE=1;
    private Firebase firebase;
    private Spinner animalspin;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        animalspin=findViewById(R.id.typeanimal1);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.animal,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        animalspin.setAdapter(adapter);
        animalspin.setOnItemSelectedListener(this);

        selectImage=(Button)findViewById(R.id.selectImage);
        Vaccines=(Button)findViewById(R.id.Vaccination) ;
        Vaccines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v==Vaccines)
                {
                    startActivity(new Intent(add.this,Vaccines.class));

                }
            }
        });

        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://projectrl-a891a.firebaseio.com/");

        add=(Button)findViewById(R.id.add) ;
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v==add)
                {

                    String pattern = "MM-dd-yyyy HH:mm:ss";
                    DateFormat df = new SimpleDateFormat(pattern);
                    Date today = Calendar.getInstance().getTime();
                    String todayAsString = df.format(today);
                   // Toast.makeText(add.this, todayAsString , Toast.LENGTH_SHORT).show();

                    EditText age = findViewById(R.id.ageanimal1);



                    radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
                    RadioButton male = findViewById(R.id.male);
                    RadioButton female = findViewById(R.id.female);

                    int selectedId = radioSexGroup.getCheckedRadioButtonId();

                    radioSexButton = (RadioButton) findViewById(selectedId);


                    firebase.child("ad").child(todayAsString).child("city").setValue("???");
                    firebase.child("ad").child(todayAsString).child("gender").setValue(radioSexButton.getText());
                    firebase.child("ad").child(todayAsString).child("idad").setValue("???");
                    firebase.child("ad").child(todayAsString).child("iduser").setValue("???");
                    firebase.child("ad").child(todayAsString).child("information").setValue("???");
                    firebase.child("ad").child(todayAsString).child("name").setValue("???");
                    firebase.child("ad").child(todayAsString).child("race").setValue("???");
                    firebase.child("ad").child(todayAsString).child("age").setValue(age.getText().toString());
                    firebase.child("ad").child(todayAsString).child("state").setValue("???");
                    firebase.child("ad").child(todayAsString).child("training").setValue("???");
                    firebase.child("ad").child(todayAsString).child("type").setValue(animalspin.getSelectedItem().toString());
                    firebase.child("ad").child(todayAsString).child("date").setValue(todayAsString);


                    startActivity(new Intent(add.this,menu.class));

                }
            }
        });

        imageView=(ImageView)findViewById(R.id.imageView);

        profile= (ImageButton)findViewById(R.id.imageButton3);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v==profile)
                {
                    startActivity(new Intent(add.this,profile.class));

                }
            }
        });


        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"הוספת תמונה"),REQUEST_CODE);
            }
        });



    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==PICK_IMAGE && resultCode==RESULT_OK )
        {
            imageuri =data.getData();
            try{
                Bitmap bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),imageuri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String typestring=parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

