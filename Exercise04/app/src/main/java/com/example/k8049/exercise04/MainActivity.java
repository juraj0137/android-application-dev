package com.example.k8049.exercise04;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private Button asyncButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.asyncButton = (Button) findViewById(R.id.button);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    public void doSomeAsyncTask(View view) {
        new MyAsyncTask().execute();
    }

    private class MyAsyncTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            asyncButton.setEnabled(false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            asyncButton.setEnabled(true);
        }

        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 0; i < 100; i++) {
                publishProgress(i);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Log.d("Error", e.getMessage());
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }
    }

}
