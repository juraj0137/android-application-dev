package com.example.k8049.exercise02;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class IntentExampleActivity extends AppCompatActivity {

    private final int ACTIVITY_SHOW_TEXT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_example);
    }

    private Intent createIntent() {
        EditText editText = (EditText) findViewById(R.id.editText);

        String text = editText.getText().toString();

        Intent intent = new Intent(this, NewEmptyActivity.class);
        intent.putExtra("text", text);

        return intent;
    }

    public void startNewActivity(View view) {
        startActivity(this.createIntent());
    }

    public void startNewActivityForResult(View view) {
        startActivityForResult(this.createIntent(), ACTIVITY_SHOW_TEXT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ACTIVITY_SHOW_TEXT:
                    Toast.makeText(getApplicationContext(), data.getExtras().getString("result"), Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }
}
