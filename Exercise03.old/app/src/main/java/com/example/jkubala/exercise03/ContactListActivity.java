package com.example.jkubala.exercise03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class ContactListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        this.initListView();

        Log.d("Init list view", "haaaa");
    }

    private void initListView() {

        ListView listView = (ListView) findViewById(R.id.contact_list);

        Contact[] contacts = {
                new Contact("Lalia Blanch", "(481) 254-5279"),
                new Contact("Curt Gardenia", "(837) 416-5025"),
                new Contact("Gertie Audrey", "(498) 936-9462"),
                new Contact("Manley Annabel", "(917) 932-2839"),
                new Contact("Keir Elisabeth", "(113) 899-9911"),
                new Contact("Dione Bernetta", "(764) 838-3053"),
                new Contact("Galen Roscoe", "(266) 668-4403"),
                new Contact("Leanna Thomasina", "(236) 928-5531"),
                new Contact("Lalia Blanch", "(481) 254-5279"),
                new Contact("Curt Gardenia", "(837) 416-5025"),
                new Contact("Gertie Audrey", "(498) 936-9462"),
                new Contact("Manley Annabel", "(917) 932-2839"),
                new Contact("Keir Elisabeth", "(113) 899-9911"),
                new Contact("Dione Bernetta", "(764) 838-3053"),
                new Contact("Galen Roscoe", "(266) 668-4403"),
                new Contact("Leanna Thomasina", "(236) 928-5531"),
        };

        final List<Contact> contactList = Arrays.asList(contacts);

        ContactArrayAdapter adapter = new ContactArrayAdapter(this, contactList);

        listView.setAdapter(adapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(ContactListActivity.this, ContactDetailActivity.class);
//                intent.putExtra("contact_name", contactList.get(position).getName());
//                intent.putExtra("contact_number", contactList.get(position).getNumber());
//
//                startActivity(intent);
//            }
//        });

    }

}
