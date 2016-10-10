package com.example.jkubala.exercise05_a;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class ChangeTextDialog extends DialogFragment {

    public interface ChangeTextDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, String text);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    ChangeTextDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.fragment_change_text_dialog, null);

        builder.setView(dialogView)
                .setTitle("New text")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText editText = (EditText) dialogView.findViewById(R.id.editText);
                        String text = editText.getText().toString();
                        listener.onDialogPositiveClick(ChangeTextDialog.this, text);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogNegativeClick(ChangeTextDialog.this);
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (ChangeTextDialogListener) context;
    }

}
