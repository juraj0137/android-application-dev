package com.example.k8049.exercise02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String[] items = {"Pasi", "Juha", "Kari", "Jouni", "Esa", "Hannu"};

        AutoCompleteTextView login = (AutoCompleteTextView) findViewById(R.id.inputLogin);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, items);
        login.setAdapter(adapter);
    }

    public void onLoginClick(View view) {
        AutoCompleteTextView login = (AutoCompleteTextView) findViewById(R.id.inputLogin);
        EditText pass = (EditText) findViewById(R.id.inputPass);
        String text = "Login: " + login.getText() + "\nPass: " + pass.getText();

        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
