package com.example.jkubala.excercisemenus;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class ContactDetailActivity extends AppCompatActivity {


    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        Intent intent = getIntent();
        String contactName = intent.getExtras().getString("contact_name");
        String contactNumber = intent.getExtras().getString("contact_number");

        this.contact = new Contact(contactName, contactNumber);

        ((TextView) findViewById(R.id.contact_detail_name)).setText(contactName);
        ((TextView) findViewById(R.id.contact_detail_phone)).setText(contactNumber);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_menu, menu);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_detail_call:
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", this.contact.getNumber(), null)));
                return true;

            case R.id.action_detail_notification:
                Intent callintent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", this.contact.getNumber(), null));
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, callintent, 0);
                Notification notification = new Notification.Builder(this)
                        .setCategory(Notification.CATEGORY_MESSAGE)
                        .setContentTitle("Call remember")
                        .setContentText("Do not forget to call " + contact.getName() + " (" + contact.getNumber() + ")")
                        .setSmallIcon(R.drawable.notification)
                        .setContentIntent(pendingIntent)
                        .setVisibility(Notification.VISIBILITY_PUBLIC)
                        .build();
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(1, notification);
                return true;

            case R.id.action_detail_sms:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", this.contact.getNumber(), null)));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
