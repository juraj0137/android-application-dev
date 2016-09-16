package com.example.k8049.exercise02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UiControlsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_controls);

        generateRadioButtons();
    }

    public void handleShowStatesButtonClick(View view) {

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupStates);

        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(checkedRadioButtonId);

        if (radioButton != null) {
            CharSequence text = radioButton.getText();
            Toast.makeText(this, "You choose " + text, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Nothing selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void generateRadioButtons() {

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupStates);

        String[] states = {"Finland", "Germany", "Slovakia", "France", "Italy"};

        RadioButton button;
        for (String state : states) {
            button = new RadioButton(this);
            button.setText(state);
            radioGroup.addView(button);
        }
    }
}
