package com.example.k8049.exercise02.listViewExample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.k8049.exercise02.R;

public class PhoneDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_detail);

        Intent intent = getIntent();
        String phone = intent.getExtras().getString("phone");

        if (phone != null) {

            TextView phoneNameTextView = (TextView) findViewById(R.id.textViewPhoneName);
            ImageView phoneImageImage = (ImageView) findViewById(R.id.imageViewPhoneImage);

            phoneNameTextView.setText(phone);

            switch (phone) {
                case "Android":
                    phoneImageImage.setImageResource(R.drawable.android);
                    break;
                case "iPhone":
                    phoneImageImage.setImageResource(R.drawable.ios);
                    break;
                case "WindowsMobile":
                    phoneImageImage.setImageResource(R.drawable.windows);
                    break;
                case "Blackberry":
                    phoneImageImage.setImageResource(R.drawable.blackberry);
                    break;
                case "WebOS":
                    phoneImageImage.setImageResource(R.drawable.webos);
                    break;
                case "Ubuntu":
                    phoneImageImage.setImageResource(R.drawable.ubuntu);
                    break;
            }
        }
    }

    public void goBackToLIst(View view) {
        finish();
    }
}
