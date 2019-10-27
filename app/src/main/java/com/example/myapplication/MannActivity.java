package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MannActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    private Button btnAdd, liste, graph;
    private EditText bauchT, halsT, größeT;
    private ImageView bild;
    private TextView ergebnis;
    double kfa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mann);
        bauchT = (EditText) findViewById(R.id.editText2);
        halsT = (EditText) findViewById(R.id.editText3);
        größeT = (EditText) findViewById(R.id.editText4);
        ergebnis = (TextView) findViewById(R.id.textView2);
        bild = (ImageView) findViewById(R.id.imageView2);
        btnAdd = (Button) findViewById(R.id.button);
        liste = (Button) findViewById(R.id.button2);
        graph = (Button) findViewById(R.id.button3);

        mDatabaseHelper = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newEntry = Integer.parseInt(bauchT.getText().toString());
                int newEntry2 = Integer.parseInt(halsT.getText().toString());
                int newEntry3 = Integer.parseInt(größeT.getText().toString());
                if (bauchT.length() != 0 && halsT.length() != 0 &&  größeT.length() != 0)  {
                    kfa = (86.010 * Math.log(newEntry - newEntry2)) - (70.041 * Math.log(newEntry3)) + 30.30;
                    AddData(newEntry, newEntry2, newEntry3, (int)kfa);
                    bauchT.setText("");
                    halsT.setText("");
                    größeT.setText("");
                    ergebnis.setText("Kfa: " + (int)kfa);
                } else {
                    toastMessage("Felder dürfen nicht leer sein!");
                }
                if(kfa > 30) {
                    bild.setImageResource(R.drawable.ic_launcher_background);
                }else {
                    bild.setImageResource(R.drawable.abc);
                }
            }
        });

        liste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MannActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });

        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MannActivity.this, Liniendiagramm.class);
                startActivity(intent);
            }
        });

    }

    public void AddData(int newEntry, int newEntry2, int newEntry3, int newEntry4) {
        boolean insertData = mDatabaseHelper.addData(newEntry, newEntry2, newEntry3, newEntry4);

        if (insertData) {
            toastMessage("Daten wurden erfolgreich gespeichert!");
        } else {
            toastMessage("Etwas ist schief gelaufen :(");
        }
    }

    /**
     * Nachrichten-Ausgabe
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
