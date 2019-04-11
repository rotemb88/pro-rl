package com.eran.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Vaccines extends AppCompatActivity {
    private Button cat, dog;
    private ListView list;
    private VaccineAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccines);

        cat = findViewById(R.id.cat);
        dog = findViewById(R.id.dog);

        list = findViewById(R.id.list);
        adapter = new VaccineAdapter(this, R.layout.itemveccines);
        list.setAdapter(adapter);


        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapter.clear();

                Vaccine cat1 = new Vaccine("חיסון 1" , "מרובע 1", "1", "3");
                Vaccine cat2 = new Vaccine("חיסון 2" , "מרובע 2", "3", "54");
                Vaccine cat3 = new Vaccine("חיסון 3" , "מרובע 3", "15", "23");
                Vaccine cat4 = new Vaccine("חיסון 4" , "מרובע 4", "11", "5");

                adapter.add(cat1);
                adapter.add(cat2);
                adapter.add(cat3);
                adapter.add(cat4);




            }
        });

        dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapter.clear();

                Vaccine dog1 = new Vaccine("חיסון 7" , "עיגול 1", "11", "13");
                Vaccine dog2 = new Vaccine("חיסון 82" , "משולש 2", "33", "55");
                Vaccine dog3 = new Vaccine("חיסון 23" , "עיגול 3", "15", "22");
                Vaccine dog4 = new Vaccine("חיסון 11" , "מרובע 4", "11", "5");

                adapter.add(dog1);
                adapter.add(dog2);
                adapter.add(dog3);
                adapter.add(dog4);


            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Vaccine vaccine = (Vaccine) parent.getItemAtPosition(position);

                adapter.remove(vaccine);

                //Toast.makeText(Vaccines.this, "" + vaccine.getName(), Toast.LENGTH_SHORT).show();

                /*if (vaccine.getName()=="חיסון 7"){
                    dog1.remove();
                }*/

            }
        });
    }
}
