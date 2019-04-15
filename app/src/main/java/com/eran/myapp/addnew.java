package com.eran.myapp;

import android.app.usage.NetworkStats;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class addnew extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Button selectImage, add, Vaccines,profile;
    private ImageView imageView;
    private int REQUEST_CODE=1;
    Uri imageuri;
    String race[]=null;
    String city[]=null;


    private static final int PICK_IMAGE=1;
    private Firebase firebase;
    private Spinner animalspin;
    private Spinner areaspin;
    private Spinner trainspin;
    private Spinner racespin;
    private Spinner cityspin;



    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnew);
        animalspin=findViewById(R.id.typeanimal1);
        areaspin=findViewById(R.id.area1);
        trainspin=findViewById(R.id.traininganimal1);
        racespin=findViewById(R.id.raceanimal1);
        cityspin=findViewById(R.id.city1);

        final SharedPreferences sp = getSharedPreferences("DocPro" , MODE_PRIVATE);
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        sp.edit().putString("userid" , mAuth.getUid());



        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.animal,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this,R.array.area,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapter3=ArrayAdapter.createFromResource(this,R.array.train,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        animalspin.setAdapter(adapter);
        animalspin.setOnItemSelectedListener(this);

        areaspin.setAdapter(adapter2);
        areaspin.setOnItemSelectedListener(this);


        trainspin.setAdapter(adapter3);
        trainspin.setOnItemSelectedListener(this);

        selectImage=(Button)findViewById(R.id.selectImage);
        Vaccines=(Button)findViewById(R.id.Vaccination) ;
        Vaccines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v==Vaccines)
                {
                    startActivity(new Intent(addnew.this,Vaccines.class));

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

                    EditText age = findViewById(R.id.ageanimal1);
                    EditText name = findViewById(R.id.nameanimal1);
                    EditText information = findViewById(R.id.information1);


                    radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
                    RadioButton male = findViewById(R.id.male);
                    RadioButton female = findViewById(R.id.female);

                    int selectedId = radioSexGroup.getCheckedRadioButtonId();

                    radioSexButton = (RadioButton) findViewById(selectedId);


                    firebase.child("ad").child(todayAsString).child("city").setValue(cityspin.getSelectedItem().toString());
                    firebase.child("ad").child(todayAsString).child("gender").setValue(radioSexButton.getText());
                    firebase.child("ad").child(todayAsString).child("idad").setValue("???");
                    firebase.child("ad").child(todayAsString).child("iduser").setValue(mAuth.getUid());
                    firebase.child("ad").child(todayAsString).child("information").setValue(information.getText().toString());
                    firebase.child("ad").child(todayAsString).child("name").setValue(name.getText().toString());
                    firebase.child("ad").child(todayAsString).child("race").setValue(racespin.getSelectedItem().toString());
                    firebase.child("ad").child(todayAsString).child("age").setValue(age.getText().toString());
                    firebase.child("ad").child(todayAsString).child("state").setValue(areaspin.getSelectedItem().toString());
                    firebase.child("ad").child(todayAsString).child("training").setValue(trainspin.getSelectedItem().toString());
                    firebase.child("ad").child(todayAsString).child("type").setValue(animalspin.getSelectedItem().toString());
                    firebase.child("ad").child(todayAsString).child("date").setValue(todayAsString);


                    startActivity(new Intent(addnew.this,menu.class));

                }
            }
        });

        imageView=(ImageView)findViewById(R.id.imageView);

        profile= (Button) findViewById(R.id.profil);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v==profile)
                {
                    startActivity(new Intent(addnew.this,profile.class));

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
        if(position==0)
        {

            race=new String[]{"פודל","פיקינז"};
            city=new String[]{"חיפה","יוקנעם"};
        }
        if(position==1)
        {
            race=new String[]{"סיאמי","פרסי"};
            city=new String[]{"נתניה","רעננה"};

        }

        ArrayAdapter<String> adt=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,race);
        racespin.setAdapter(adt);
        ArrayAdapter<String> adt2=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,city);
        cityspin.setAdapter(adt2);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
