package com.example.jkubala.exercise04;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;
    private ProgressBar progressBar;
    private ProgressBar progressBar2;

    private String[] images = {
            "http://www.androidcentral.com/sites/androidcentral.com/files/styles/xlarge_wm_blw/public/article_images/2015/01/nexus-6-black-white.jpg?itok=EcEes0NO",
            "http://cdn.slashgear.com/wp-content/uploads/2014/12/nexus-6-review-sg-9-600x270.jpg",
            "http://img1.lesnumeriques.com/test/97/9741/test-google-nexus-6_3.jpg",
            "http://images.indianexpress.com/2014/11/nexus6-1.jpg"
    };

    private int imageIndex;
    private DownloadImageTask task;
    private float x1, x2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.imageText);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);

        // start showing images
        imageIndex = 0;
        showImage();
    }

    private void showImage() {
        new DownloadImageTask().execute(images[imageIndex]);
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                if (x1 < x2) {
                    imageIndex--;
                    if (imageIndex < 0) imageIndex = images.length - 1;
                } else {
                    imageIndex++;
                    if (imageIndex > (images.length - 1)) imageIndex = 0;
                }
                showImage();
                break;
        }
        return false;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(String... urls) {

            Bitmap bitmap = null;

            try {
                URL imageUrl = new URL(urls[0]);
                InputStream in = imageUrl.openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("DownloadImage load err", e.getMessage());
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            imageView.setImageBitmap(bitmap);
            textView.setText("Image " + (imageIndex + 1) + "/" + images.length);
            progressBar2.setProgress(Math.round(((imageIndex + 1) * 100) / images.length));
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
