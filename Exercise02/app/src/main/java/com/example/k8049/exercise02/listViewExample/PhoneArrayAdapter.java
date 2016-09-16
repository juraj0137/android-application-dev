package com.example.k8049.exercise02.listViewExample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.k8049.exercise02.R;
import java.util.List;

public class PhoneArrayAdapter extends ArrayAdapter<String> {

    private final List<String> phones;
    private final Context context;

    public PhoneArrayAdapter(Context context, List<String> phones) {
        super(context, R.layout.rowlayout, R.id.phoneName, phones);

        this.context = context;
        this.phones = phones;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // get row
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);

        // show phone name
        TextView textView = (TextView) rowView.findViewById(R.id.phoneName);
        textView.setText(phones.get(position));

        // show phone icon/image
        ImageView imageView = (ImageView) rowView.findViewById(R.id.phoneImage);
        switch (phones.get(position)) {
            case "Android":
                imageView.setImageResource(R.drawable.android);
                break;
            case "iPhone":
                imageView.setImageResource(R.drawable.ios);
                break;
            case "WindowsMobile":
                imageView.setImageResource(R.drawable.windows);
                break;
            case "Blackberry":
                imageView.setImageResource(R.drawable.blackberry);
                break;
            case "WebOS":
                imageView.setImageResource(R.drawable.webos);
                break;
            case "Ubuntu":
                imageView.setImageResource(R.drawable.ubuntu);
                break;
        }

        // return row view
        return rowView;
    }


}
