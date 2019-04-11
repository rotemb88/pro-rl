package com.eran.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdAdapter extends ArrayAdapter {

    private Context _context;

    public AdAdapter(Context context, int resource) {
        super(context, resource);
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(_context).inflate(R.layout.admini, null);

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView type = (TextView) convertView.findViewById(R.id.type);


        Ad ad = (Ad) getItem(position);
        name.setText(ad.getName());
        type.setText(ad.getType());
        //image.setImage(ad.getImage());



        return convertView;
    }
}
