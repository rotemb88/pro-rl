package com.eran.myapp;

import android.app.usage.NetworkStats;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

public class Vaccines extends AppCompatActivity {
    private Button cat, dog;
    private ListView list;
    private VaccineAdapter adapter;
    private Firebase firebase;
    int flag = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccines);

        cat = findViewById(R.id.cat);
        dog = findViewById(R.id.dog);

        list = findViewById(R.id.list);
        adapter = new VaccineAdapter(this, R.layout.itemveccines);
        list.setAdapter(adapter);

        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://projectrl-a891a.firebaseio.com/users");


        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapter.clear();

                Vaccine cat1 = new Vaccine("חיסון 1" , " מרובע 1", "7 שבועות", "0");
                Vaccine cat2 = new Vaccine("חיסון 2" , "מרובע 2", "11 שבועות", "פעם בשנה");
                Vaccine cat3 = new Vaccine("חיסון 3" , "כלבת", "11 שבועות", "פעם בשנה");
                Vaccine cat4 = new Vaccine("חיסון 4" , "תילוע", "1 חודשים", "פעמיים בשנה");

                adapter.add(cat1);
                adapter.add(cat2);
                adapter.add(cat3);
                adapter.add(cat4);

                flag = 1;



            }
        });

        dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapter.clear();

                Vaccine dog1 = new Vaccine("חיסון 1" , "פרוו", "6 שבועות", "פעם אחת");
                Vaccine dog2 = new Vaccine("חיסון 2" , "משושה 1", "7 שבועות", "פעם אחת");
                Vaccine dog3 = new Vaccine("חיסון 3" , "משושה 2", "9 שבועות", "פעם אחת");
                Vaccine dog4 = new Vaccine("חיסון 4" , "משושה 3", "12 שבועות", "פעם בשנה");
                Vaccine dog5 = new Vaccine("חיסון 5" , "כלבת", "14 שבועות", "פעם בשנה");
                Vaccine dog6 = new Vaccine("חיסון 6" , "תולעת הפארק", "14 שבועות", "פעם בשלושה חודשים");
                Vaccine dog7 = new Vaccine("חיסון 7" , "תילוע", "1 חודשים", "פעם בחצי שנה");


                adapter.add(dog1);
                adapter.add(dog2);
                adapter.add(dog3);
                adapter.add(dog4);
                adapter.add(dog5);
                adapter.add(dog6);
                adapter.add(dog7);

                 flag = 2;


            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Vaccine vaccine = (Vaccine) parent.getItemAtPosition(position);

                //adapter.remove(vaccine);

                //Toast.makeText(Vaccines.this, "" + vaccine.getName(), Toast.LENGTH_SHORT).show();

                /*if (vaccine.getName()=="חיסון 7"){
                    dog1.remove();
                }*/
                final FirebaseAuth mAuth = FirebaseAuth.getInstance();

                final SharedPreferences sp = getSharedPreferences("DocPro" , MODE_PRIVATE);
                sp.edit().putString("userid" , mAuth.getUid());
                String userid = sp.toString();

                if (flag==1)
                    firebase.child(userid).child("personal Vaccination program").child("Cat").child(vaccine.getName()).setValue(vaccine.getName());

                else
                    firebase.child(userid).child("personal Vaccination program").child("Dog").child(vaccine.getName()).setValue(vaccine.getName());

                view.setBackgroundColor(Color.GRAY);




            }
        });

    }
}
