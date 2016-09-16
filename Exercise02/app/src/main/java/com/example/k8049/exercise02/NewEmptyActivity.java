package com.example.k8049.exercise02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NewEmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_empty);

        TextView textView = (TextView) findViewById(R.id.textView2);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            textView.setText(extras.getString("text"));
        }
    }

    public void goBack(View view) {
        Intent intent = new Intent();
        intent.putExtra("result", "Back result");
        setResult(RESULT_OK, intent);
        finish();
    }
}
