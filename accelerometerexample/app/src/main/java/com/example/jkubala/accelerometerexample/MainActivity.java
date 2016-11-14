package com.example.jkubala.accelerometerexample;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private Ball ball;
    private View table;
    private boolean isDialogVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        table = findViewById(R.id.table);

        SensorManager mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_FASTEST);

        Point displaySize = new Point();
        getWindowManager().getDefaultDisplay().getSize(displaySize);

        ball = new Ball(displaySize.x / 2, displaySize.y / 2);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER)
            return;

        if (ball.isFalling)
            return;

        float x = event.values[0];
        float y = event.values[1];

        x = (Math.signum(x) * x * x) / 1.5f;
        y = (Math.signum(y) * y * y) / 1.5f;

        ball.setTranslation(-x, y);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void showEndGameDialog() {

        if (isDialogVisible)
            return;

        final MainActivity activity = this;

        new AlertDialog.Builder(activity)
                .setTitle("Game over")
                .setMessage("Do you want to play next game?")
                .setPositiveButton("New game", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ball.reset();
                        isDialogVisible = false;
                    }
                })
                .setNegativeButton("Quit game", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        activity.finishAffinity();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

        isDialogVisible = true;
    }

    class Ball {

        private final View ballView = findViewById(R.id.ball);
        private float initialX;
        private float initialY;
        private float prevX;
        private float prevY;
        private boolean isFalling = false;
        private int ballHeight = 0;
        private int ballWidth = 0;
        private int tableWidth = 0;
        private int tableHeight = 0;

        private final int DIR_LEFT = 0;
        private final int DIR_RIGHT = 1;
        private final int DIR_TOP = 2;
        private final int DIR_BOTTOM = 3;

        public Ball(float x, float y) {

            initialX = x;
            initialY = y;

            setAbsolute(x, y, false);
        }

        public void setAbsolute(float x, float y) {
            setAbsolute(x, y, true);
        }

        public void setAbsolute(float x, float y, boolean checkBoundaries) {

            prevX = x;
            prevY = y;

            if (checkBoundaries) {

                ballHeight = ballView.getHeight();
                ballWidth = ballView.getHeight();
                tableWidth = table.getWidth();
                tableHeight = table.getHeight();

                x = Math.max(0, Math.min(x, tableWidth - ballWidth));
                y = Math.max(0, Math.min(y, tableHeight - ballHeight));

                float centerX = x + (ballWidth / 2);
                float centerY = y + (ballHeight / 2);

                if (centerX < ballWidth) fallDown(DIR_LEFT);

                if (centerX > tableWidth - ballWidth) fallDown(DIR_RIGHT);

                if (centerY < ballHeight) fallDown(DIR_TOP);

                if (centerY > tableHeight - ballHeight) fallDown(DIR_BOTTOM);
            }

            ballView.setX(x);
            ballView.setY(y);

        }

        public void setTranslation(float x, float y) {
            setAbsolute(prevX + x, prevY + y);
        }

        private void fallDown(final int direction) {

            isFalling = true;
            final float lastX = prevX;
            final float lastY = prevY;

            Animation a = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {

                    interpolatedTime *= 1.5;

                    if (interpolatedTime < 0.50) {

                        if (direction == DIR_LEFT) {
                            setAbsolute((ballWidth / 2) * (1 - (interpolatedTime * 2)), lastY, false);
                        } else if (direction == DIR_RIGHT) {
                            setAbsolute(lastX + ((ballWidth / 2) * interpolatedTime * 2), lastY, false);
                        } else if (direction == DIR_BOTTOM) {
                            setAbsolute(lastX, lastY + ((ballHeight / 2) * interpolatedTime * 2), false);
                        } else if (direction == DIR_TOP) {
                            setAbsolute(lastX, (ballHeight / 2) * (1 - (interpolatedTime * 2)), false);
                        }

                    } else if (interpolatedTime < 1.5) {

                        int newSize = ballHeight - (int) (ballHeight * (interpolatedTime - 0.5));

                        ballView.getLayoutParams().height = newSize;
                        ballView.getLayoutParams().width = newSize;

                        ballView.setX(prevX + ((ballWidth - newSize) / 2));
                        ballView.setY(prevY + ((ballHeight - newSize) / 2));

                        ballView.requestLayout();
                    } else {
                        showEndGameDialog();
                    }
                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };
            a.setDuration(300);
            ballView.startAnimation(a);
        }

        private void reset() {
            isFalling = false;
            setAbsolute(initialX, initialY, false);
            ballView.getLayoutParams().width = ballWidth;
            ballView.getLayoutParams().height = ballHeight;
            ballView.requestLayout();
        }

    }

}
