package com.example.jkubala.exercise03;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class ContactDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
    }

    private void initListView() {

//        ListView listView = (ListView) findViewById(R.id.contact_list);
//
//        Contact[] contacts = {
//                new Contact("", "")
//        };
//
//        final List<Contact> phoneList = Arrays.asList(contacts);
//
//        ContactArrayAdapter adapter = new ContactArrayAdapter(this, phoneList);
//
//        listView.setAdapter(adapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(ContactDetailActivity.this, PhoneDetailActivity.class);
//                intent.putExtra("phone", phoneList.get(position));
//
//                startActivity(intent);
//            }
//        });

    }

}
