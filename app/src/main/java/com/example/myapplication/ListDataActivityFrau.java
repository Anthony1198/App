package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListDataActivityFrau extends AppCompatActivity {

        private static final String TAG = "ListDataActivity";

        DatabaseHelperFrau mDatabaseHelperFrau;

        private ListView mListView;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.list_layout);
            mListView = (ListView) findViewById(R.id.listView);
            mDatabaseHelperFrau = new DatabaseHelperFrau(this);

            populateListView();
        }

        private void populateListView() {
            Log.d(TAG, "populateListView: Displaying data in the ListView.");

            //get the data and append to a list
            Cursor data = mDatabaseHelperFrau.getData();
            ArrayList<String> listData = new ArrayList<>();
            while(data.moveToNext()){
                //get the value from the database in column 1
                //then add it to the ArrayList
                listData.add("Bauch: " + data.getString(1) + "\n" +
                        "Hals: " + data.getString(2) + "\n" +
                        "Größe: " + data.getString(3) + "\n" +
                        "Hüfte: " + data.getString(4) + "\n" +
                        "Kfa: " + data.getString(5) + "%");
            }
            //create the list adapter and set the adapter
            ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
            mListView.setAdapter(adapter);

        }

        /**
         * customizable toast
         * @param message
         */
        private void toastMessage(String message){
            Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
        }
    }
