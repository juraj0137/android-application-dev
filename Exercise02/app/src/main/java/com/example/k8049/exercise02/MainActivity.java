package com.example.k8049.exercise02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.k8049.exercise02.listViewExample.ListViewActivity;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleShowRadioButtonsActivity(View view) {
        showAnotherActivity(UiControlsActivity.class);
    }

    public void handleShowListViewActivity(View view) {
        showAnotherActivity(ListViewActivity.class);
    }

    public void handleShowLoginActivity(View view) {
        showAnotherActivity(LoginActivity.class);
    }

    public void handleShowMapActivity(View view) {
        showAnotherActivity(MapActivity.class);
    }

    private void showAnotherActivity(Class cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
}
