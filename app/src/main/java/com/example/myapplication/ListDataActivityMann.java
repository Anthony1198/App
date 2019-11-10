package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;



public class ListDataActivityMann extends AppCompatActivity {

    /**
     * Variablen/Objekt Deklaration
     */

    DatabaseHelperMann mDatabaseHelperMann;
    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data_mann);
        mListView = (ListView) findViewById(R.id.listView);
        mDatabaseHelperMann = new DatabaseHelperMann(this);
        Button löschen = (Button) findViewById(R.id.löschen);

        populateListView();

        löschen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelperMann.löscheDB();
                populateListView();
            }
        });
    }

    /**
     * Daten werden aus der Datenbank geholt und in die Liste hinzugeüfgt.
     */
    private void populateListView() {
        Cursor data = mDatabaseHelperMann.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add("Bauch: " + data.getString(1) + "\n" +
                        "Hals: " + data.getString(2) + "\n" +
                        "Größe: " + data.getString(3) + "\n" +
                        "Kfa: " + data.getString(4) + "%");
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);

    }

    /**
     * Nachrichten-Ausgabe
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
