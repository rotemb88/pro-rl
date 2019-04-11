package com.eran.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class VaccineAdapter extends ArrayAdapter {
    private Context _context;

    public VaccineAdapter(Context context, int resource) {
        super(context, resource);
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(_context).inflate(R.layout.itemveccines, null);

        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView age = (TextView) convertView.findViewById(R.id.age);
        TextView frequency = (TextView) convertView.findViewById(R.id.frequency);


        Vaccine vaccine = (Vaccine) getItem(position);
        title.setText(vaccine.getTitle());
        name.setText(vaccine.getName());
        age.setText(vaccine.getAge());
        frequency.setText(vaccine.getFrequency());



        return convertView;
    }
}
