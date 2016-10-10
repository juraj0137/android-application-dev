package com.example.jkubala.exercise05_a;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ChangeTextDialog.ChangeTextDialogListener {

    private final String STATEKEY = "statekey";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView1);

        if (savedInstanceState != null && savedInstanceState.containsKey(STATEKEY)) {
            textView.setText(savedInstanceState.getString(STATEKEY));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {
        saveInstanceState.putString(STATEKEY, textView.getText().toString());
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String text) {
        textView.setText(text);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
    }

    public void buttonClicked(View view) {
        ChangeTextDialog dialog = new ChangeTextDialog();
        dialog.show(getSupportFragmentManager(), "Title");
    }
}
