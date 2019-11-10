package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Klasse für die Auflistung der Frauen-Daten
 */

public class ListDataActivityFrau extends AppCompatActivity {

    /**
     * Variablen/Objekt Deklaration
     */

        DatabaseHelperFrau mDatabaseHelperFrau;
        private ListView mListView;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_list_data_mann);
            mListView = (ListView) findViewById(R.id.listView);
            mDatabaseHelperFrau = new DatabaseHelperFrau(this);
            Button löschen = (Button) findViewById(R.id.löschen);

            populateListView();

            löschen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDatabaseHelperFrau.löscheDB();
                    populateListView();
                }
            });
        }

    /**
     * Daten werden aus der Datenbank geholt und in die Liste hinzugeüfgt.
     */
        private void populateListView() {
            Cursor data = mDatabaseHelperFrau.getData();
            ArrayList<String> listData = new ArrayList<>();
            while(data.moveToNext()){
                listData.add("Bauch: " + data.getString(1) + "\n" +
                        "Hals: " + data.getString(2) + "\n" +
                        "Größe: " + data.getString(3) + "\n" +
                        "Hüfte: " + data.getString(4) + "\n" +
                        "Kfa: " + data.getString(5) + "%");
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
