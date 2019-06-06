package com.eran.myapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AdAdapter extends ArrayAdapter {

    private Context _context;
    private Context _context2;




    public AdAdapter(Context context, int resource) {
        super(context, resource);
        _context = context;
        _context2 = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(_context).inflate(R.layout.admini, null);

        //convertView.setBackgroundColor();

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView type = (TextView) convertView.findViewById(R.id.type);
        ImageView image=(ImageView)convertView.findViewById(R.id.imageView2);





        final Ad ad = (Ad) getItem(position);
        name.setText("שם החיה :"+ad.getName());
        type.setText("סוג החיה: "+ad.getType());


        Picasso.with(_context).load(ad.getImage()).into(image);
      //  Log.e("tal123 the image : " , ad.getImage());





        convertView.findViewById(R.id.clickme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(_context , fullad.class);
                intent.putExtra("name", ad.getName());
                intent.putExtra("type", ad.getType());
                intent.putExtra("image", ad.getImage());
                intent.putExtra("Purpose", ad.getPurpose());
                intent.putExtra("age", ad.getAge());
                intent.putExtra("information", ad.getInformation());
                intent.putExtra("race", ad.getRace());
                intent.putExtra("training", ad.getTraining());
                intent.putExtra("state", ad.getState());
                intent.putExtra("city", ad.getCity());
                intent.putExtra("gender", ad.getGender());
                intent.putExtra("date", ad.getDate());
                intent.putExtra("firstname", ad.getFirstname());
                intent.putExtra("lastname", ad.getLastname());
                intent.putExtra("email", ad.getEmail());
                intent.putExtra("phone", ad.getPhone());



                /*Intent intent2 = new Intent(_context , contactfullad.class);
                intent2.putExtra("name", ad.getName());
                _context2.startActivity(intent2);*/
                _context.startActivity(intent);






            }

        });






        return convertView;
    }

}
