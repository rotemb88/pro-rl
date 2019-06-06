package com.eran.myapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.app.usage.NetworkStats;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;
import java.security.KeyStore;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import static com.eran.myapp.R.id.imageView4;


public class addnew extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Button selectImage, add, Vaccines,profile,newimage;
    private ImageView imageView;
    private int REQUEST_CODE=1;
    private static final int GalleryPick=1;
    Uri imageuri;
    String race[]=null;
    String city[]=null;
    String todayAsString;
    EditText nameimage;
    public   String linkimage;
    private static final int PICK_IMAGE=1;
    private Firebase firebase;
    private Spinner animalspin;
    private Spinner areaspin;
    private Spinner trainspin;
    private Spinner racespin;
    private Spinner cityspin;
    private Spinner Purposespin;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseref;
    public String aaaa;
    private ProgressDialog mprogress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnew);
        animalspin=findViewById(R.id.typeanimal1);
        areaspin=findViewById(R.id.area1);
        trainspin=findViewById(R.id.traininganimal1);
        racespin=findViewById(R.id.raceanimal1);
        cityspin=findViewById(R.id.city1);
        Purposespin=findViewById(R.id.spinnerprpose);
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mDatabaseref=FirebaseDatabase.getInstance().getReference();
        //  currentUserId=mAuth.getCurrentUser().getUid();

        imageView=(ImageView)findViewById(R.id.imageView);

        mprogress=new ProgressDialog(this);
        newimage=findViewById(R.id.button3);
        Vaccines=(Button)findViewById(R.id.Vaccination) ;
        add=(Button)findViewById(R.id.add) ;
        profile= (Button) findViewById(R.id.profil);



        final SharedPreferences sp = getSharedPreferences("DocPro" , MODE_PRIVATE);
        sp.edit().putString("userid" , mAuth.getUid());


        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.animal,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this,R.array.area,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapter3=ArrayAdapter.createFromResource(this,R.array.train,android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapter4=ArrayAdapter.createFromResource(this,R.array.Purpose,android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        animalspin.setAdapter(adapter);
        animalspin.setOnItemSelectedListener(this);

        areaspin.setAdapter(adapter2);
        areaspin.setOnItemSelectedListener(this);

        trainspin.setAdapter(adapter3);
        trainspin.setOnItemSelectedListener(this);

        Purposespin.setAdapter(adapter4);
        Purposespin.setOnItemSelectedListener(this);


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

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v==add)
                {

                    String pattern = "MM-dd-yyyy HH:mm:ss";
                    DateFormat df = new SimpleDateFormat(pattern);
                    Date today = Calendar.getInstance().getTime();
                    todayAsString = df.format(today);
                    EditText age = findViewById(R.id.ageanimal1);
                    EditText name = findViewById(R.id.nameanimal1);
                    EditText information = findViewById(R.id.information1);
                    radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
                    RadioButton male = findViewById(R.id.male);
                    RadioButton female = findViewById(R.id.female);
                    int selectedId = radioSexGroup.getCheckedRadioButtonId();
                    radioSexButton = (RadioButton) findViewById(selectedId);
                    EditText first_name_person = findViewById(R.id.first_name22);
                    EditText last_name_person = findViewById(R.id.last_name22);
                    EditText email_person = findViewById(R.id.email_person22);
                    EditText phone_person = findViewById(R.id.phone_person22);



                    firebase.child("ad").child(todayAsString).child("city").setValue(cityspin.getSelectedItem().toString());
                    firebase.child("ad").child(todayAsString).child("gender").setValue(radioSexButton.getText());
                    firebase.child("ad").child(todayAsString).child("idad").setValue("???");
                    firebase.child("ad").child(todayAsString).child("iduser").setValue(mAuth.getUid());
                    firebase.child("ad").child(todayAsString).child("information").setValue(information.getText().toString());
                    firebase.child("ad").child(todayAsString).child("name").setValue(name.getText().toString());
                    firebase.child("ad").child(todayAsString).child("race").setValue(racespin.getSelectedItem().toString());
                    firebase.child("ad").child(todayAsString).child("age").setValue(age.getText().toString());
                    firebase.child("ad").child(todayAsString).child("state").setValue(areaspin.getSelectedItem().toString());
                    firebase.child("ad").child(todayAsString).child("Purpose").setValue(Purposespin.getSelectedItem().toString());
                    firebase.child("ad").child(todayAsString).child("training").setValue(trainspin.getSelectedItem().toString());
                    firebase.child("ad").child(todayAsString).child("type").setValue(animalspin.getSelectedItem().toString());
                    firebase.child("ad").child(todayAsString).child("date").setValue(todayAsString);
                    firebase.child("ad").child(todayAsString).child("image").setValue(aaaa);
                    firebase.child("ad").child(todayAsString).child("firstname").setValue(first_name_person.getText().toString());
                    firebase.child("ad").child(todayAsString).child("lastname").setValue(last_name_person.getText().toString());
                    firebase.child("ad").child(todayAsString).child("email").setValue(email_person.getText().toString());
                    firebase.child("ad").child(todayAsString).child("phone").setValue(phone_person.getText().toString());



                    startActivity(new Intent(addnew.this,menu.class));

                }
            }
        });



        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v==profile)
                {
                    startActivity(new Intent(addnew.this,profile.class));

                }
            }
        });

        newimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,GalleryPick);
            }

        });

    }

    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data) {

        if (requestCode == GalleryPick && resultCode == RESULT_OK) {
            mprogress.setMessage("uploading....");
            mprogress.show();
            ;
            Uri uri = data.getData();
            final StorageReference FilePath = mStorageRef.child("uploads").child(uri.getLastPathSegment());
            FilePath.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        //getting storage string

                        //String DownloadUrl= task.getResult().getDownloadUrl().toString();

                        final String DownloadUrl = task.getResult().getStorage().getDownloadUrl().toString();

                        FilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                final String downloadUrl = uri.toString();

                                mDatabaseref.child("Image").setValue(downloadUrl).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            mprogress.dismiss();
                                            Picasso.with(addnew.this).load(downloadUrl).into(imageView);
                                            aaaa=downloadUrl;
                                            Toast.makeText(addnew.this, "התמונה הועלתה בהצלחה", Toast.LENGTH_SHORT).show();


                                        } else {
                                            Toast.makeText(addnew.this, "שגיאה בהעלאת התמונה.. נסה שנית", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        });


                        //uploading into database


                    } else {
                        Toast.makeText(addnew.this, "Your picture did NOT saved", Toast.LENGTH_SHORT).show();
                        mprogress.dismiss();
                    }
                }
            });

        }
        ;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId())
        {
            case R.id.typeanimal1:
                if(position==0)
                {
                    race=new String[]{"ללא"};
                }
                if(position==1)
                {
                    race=new String[]{"אמסטף","בוקסר","בולדוג אמריקאי","בולדוג אנגלי","בולדוג צרפתי","גולדן רטריבר","דוברמן","האסקי סיבירי","וויט טרייר","לבדרדור","פודל","פקינז","רוטווילר"};
                }
                if(position==2)
                {
                    race=new String[]{"אביסני","אנגורה","אקזוטי","בירמן","בנגלי","בריטי","הימלאיה","מיין קון","מעורב","סומלי","סיאמי","ספינקס","סקוטי","פרסי","רגדול","רוסי כחול","אחר"};
                }
                if(position==3)
                {
                    race=new String[]{"חמוס"};
                }
                if(position==4)
                {
                    race=new String[]{"דררה","זאקו","קוקטייל","קקדו","תוכון","יונים","כנרית"};
                }


            case R.id.area1:
                    if(position==0)
                    {
                        city=new String[]{"ללא"};
                    }
                    if(position==1)
                    {
                        city=new String[]{"חיפה והסביבה","קריות והסביבה","עכו-נהריה והסביבה","גליל עליון","טבריה והסביבה","כרמיאל והסביבה","נצרת","ראש פינה","גליל תחתון","הגולן","זכרון וחוף הכרמל","חדרה והסביבה","קיסריה והסביבה","יקנעם והסביבה","עפולה ועמקים"};
                    }

                  if(position==2)
                    {
                        city=new String[]{"נתניה והסביבה","רמת השרון-הרצליה","רעננה-כפר סבא","הוד השרון והסביבה","דרום השרון","צפון השרון"};

                    }

                if(position==3)
                {
                    city=new String[]{"ירושלים","בית שמש והסביבה","הרי יהודה-מבשרת והסביבה","מעלה אדומים והסביבה"};

                }
                if(position==4)
                {
                    city=new String[]{"תל אביב","ראשון לציון והסביבה","חולון-בת ים","רמת גן-גבעתיים","פתח תקווה והסביבה","ראש העין והסביבה","רמלה -לוד","בני ברק","מודיעין והסביבה","נס ציונה-רחובות","גדרה-יבנה והסביבה"};

                }
                if(position==5)
                {
                    city=new String[]{"אשדוד-אשקלון והסביבה","קרית גת והסביבה","באר שבע והסביבה","ישובי הנגב","הנגב המערבי","אילת והערבה"};

                }



        }
    /*    if(view==(R.id.typeanimal1))
        {
            if(position==0)
            {
                race=new String[]{"ללא"};
            }
            if(position==1)
            {
                race=new String[]{"אמסטף","בוקסר","בולדוג אמריקאי","בולדוג אנגלי","בולדוג צרפתי","גולדן רטריבר","דוברמן","האסקי סיבירי","וויט טרייר","לבדרדור","פודל","פקינז","רוטווילר"};
            }
            if(position==2)
            {
                race=new String[]{"אביסני","אנגורה","אקזוטי","בירמן","בנגלי","בריטי","הימלאיה","מיין קון","מעורב","סומלי","סיאמי","ספינקס","סקוטי","פרסי","רגדול","רוסי כחול","אחר"};
            }
        }*/
        String typestring=parent.getItemAtPosition(position).toString();
      /*  if(view==(R.id.area1))
        {
            if(position==0)
            {
                city=new String[]{"ללא"};
            }
            if(position==1)
            {
                city=new String[]{"חיפה והסביבה","קריות והסביבה","עכו-נהריה והסביבה","גליל עליון","טבריה והסביבה","כרמיאל והסביבה","נצרת","ראש פינה","גליל תחתון","הגולן","זכרון וחוף הכרמל","חדרה והסביבה","קיסריה והסביבה","יקנעם והסביבה","עפולה ועמקים"};
            }

            if(position==2)
            {
                city=new String[]{"נתניה והסביבה","רמת השרון-הרצליה","רעננה-כפר סבא","הוד השרון והסביבה","דרום השרון","צפון השרון"};

            }
        }*/



     /*   if(position==3)
        {
            race=new String[]{"חמוס"};
            city=new String[]{"ירושלים","בית שמש והסביבה","הרי יהודה-מבשרת והסביבה","מעלה אדומים והסביבה"};

        }

        if(position==4)
        {
            race=new String[]{"דררה","זאקו","קוקטייל","קקדו","תוכון","יונים","כנרית"};

            city=new String[]{"תל אביב","ראשון לציון והסביבה","חולון-בת ים","רמת גן-גבעתיים","פתח תקווה והסביבה","ראש העין והסביבה","רמלה -לוד","בני ברק","מודיעין והסביבה","נס ציונה-רחובות","גדרה-יבנה והסביבה"};

        }

        if(position==5)
        {
            race=new String[]{"אוגר","ארנב","שרקן","שפן"};
            city=new String[]{"אשדוד-אשקלון והסביבה","קרית גת והסביבה","באר שבע והסביבה","ישובי הנגב","הנגב המערבי","אילת והערבה"};

        }*/


        ArrayAdapter<String> adt=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,race);
        racespin.setAdapter(adt);
        ArrayAdapter<String> adt2=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,city);
        cityspin.setAdapter(adt2);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }





}

