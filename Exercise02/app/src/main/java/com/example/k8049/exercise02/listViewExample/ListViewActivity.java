package com.example.k8049.exercise02.listViewExample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.k8049.exercise02.MainActivity;
import com.example.k8049.exercise02.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        initListView();
    }

    private void initListView() {

        ListView listView = (ListView) findViewById(R.id.listView);

        String[] phones = {
                "Android", "iPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu",
                "Android", "iPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu",
                "Android", "iPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu"
        };

        final List<String> phoneList = Arrays.asList(phones);

        PhoneArrayAdapter adapter = new PhoneArrayAdapter(this, phoneList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListViewActivity.this, PhoneDetailActivity.class);
                intent.putExtra("phone", phoneList.get(position));

                startActivity(intent);
            }
        });

    }
}
