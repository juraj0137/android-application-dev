package com.example.k8049.exercise02;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }

    public void onShowMapClick(View view) {

        EditText latitudeEditText = (EditText) findViewById(R.id.inputLatitude);
        EditText longitudeEditText = (EditText) findViewById(R.id.inputLongitude);

        Double lat = Double.parseDouble(latitudeEditText.getText().toString());
        Double lng = Double.parseDouble(longitudeEditText.getText().toString());

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:" + lat + "," + lng));
        startActivity(intent);
    }
}
