package com.example.jkubala.exercise03;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContactArrayAdapter extends ArrayAdapter<String> {

    private final List<Contact> contacts;
    private final Context context;

    @TargetApi(Build.VERSION_CODES.N)
    public ContactArrayAdapter(Context context, List<Contact> contacts) {
        super(context, R.layout.contact_list_row, R.id.contact_name);

        this.context = context;
        this.contacts = contacts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.d("get view ---", "");

        // get row
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.contact_list_row, parent, false);

        // show phone name
        TextView contactName = (TextView) rowView.findViewById(R.id.contact_name);
        contactName.setText(contacts.get(position).getName());


        // show phone name
        TextView contactNumber = (TextView) rowView.findViewById(R.id.contact_phone_number);
        contactName.setText("Tel: " + contacts.get(position).getNumber());

        // return row view
        return rowView;
    }


}
