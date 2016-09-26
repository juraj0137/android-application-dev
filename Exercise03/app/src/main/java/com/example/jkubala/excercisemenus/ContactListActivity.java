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
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class ContactListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        this.initListView();
    }

    private void initListView() {

        ListView listView = (ListView) findViewById(R.id.contact_list);

        Contact[] contacts = {
                new Contact("Lalia Blanch", "4812545279"),
                new Contact("Curt Gardenia", "8374165025"),
                new Contact("Gertie Audrey", "4989369462"),
                new Contact("Manley Annabel", "9179322839"),
                new Contact("Keir Elisabeth", "1138999911"),
                new Contact("Dione Bernetta", "7648383053"),
                new Contact("Galen Roscoe", "2666684403"),
                new Contact("Leanna Thomasina", "2369285531"),
                new Contact("Lalia Blanch", "4812545279"),
                new Contact("Curt Gardenia", "8374165025"),
                new Contact("Gertie Audrey", "4989369462"),
                new Contact("Manley Annabel", "9179322839"),
                new Contact("Keir Elisabeth", "1138999911"),
                new Contact("Dione Bernetta", "7648383053"),
                new Contact("Galen Roscoe", "2666684403"),
                new Contact("Leanna Thomasina", "2369285531"),
        };

        final List<Contact> contactList = Arrays.asList(contacts);

        ContactArrayAdapter adapter = new ContactArrayAdapter(this, contactList);

        listView.setAdapter(adapter);

        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ContactListActivity.this, ContactDetailActivity.class);
                intent.putExtra("contact_name", contactList.get(position).getName());
                intent.putExtra("contact_number", contactList.get(position).getNumber());
                startActivity(intent);
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextual_menu, menu);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // context menu item
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        TextView nameTextView = (TextView) info.targetView.findViewById(R.id.contact_name);
        String name = (String) nameTextView.getText();

        TextView numberTextView = (TextView) info.targetView.findViewById(R.id.contact_phone_number);
        String number = (String) numberTextView.getText();

        switch (item.getItemId()) {
            case R.id.action_call:
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", number, null)));
                return true;
            case R.id.action_sms:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null)));
                return true;
            case R.id.action_notification:
                Intent callintent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", number, null));
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, callintent, 0);

                Notification notification = new Notification.Builder(this)
                        .setCategory(Notification.CATEGORY_MESSAGE)
                        .setSmallIcon(R.drawable.notification)
                        .setContentIntent(pendingIntent)
                        .setContentTitle("Call remember")
                        .setContentText("Do not forget to call " + name + " (" + number + ")")
                        .setVisibility(Notification.VISIBILITY_PUBLIC)
                        .build();
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(1, notification);
                return true;
            case R.id.action_detail:
                Intent intent = new Intent(ContactListActivity.this, ContactDetailActivity.class);
                intent.putExtra("contact_name", name);
                intent.putExtra("contact_number", number);
                startActivity(intent);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
