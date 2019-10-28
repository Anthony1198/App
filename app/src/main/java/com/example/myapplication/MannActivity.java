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

                if (bauchT.getText().length() != 0 && halsT.getText().length() != 0 &&  größeT.getText().length() != 0)  {
                    int newEntry = Integer.parseInt(bauchT.getText().toString());
                    int newEntry2 = Integer.parseInt(halsT.getText().toString());
                    int newEntry3 = Integer.parseInt(größeT.getText().toString());

                    kfa = (86.010 * Math.log(newEntry - newEntry2)) - (70.041 * Math.log(newEntry3)) + 30.30;
                    AddData(newEntry, newEntry2, newEntry3, (int)kfa);
                    bauchT.setText("");
                    halsT.setText("");
                    größeT.setText("");
                    ergebnis.setText("Kfa: " + (int)kfa);

                    if(kfa < 13) {
                        bild.setImageResource(R.drawable.bild_zwoelf);
                    }if(kfa > 11 && kfa < 16) {
                        bild.setImageResource(R.drawable.bild_fzehn);
                    }if(kfa > 14 && kfa < 21) {
                        bild.setImageResource(R.drawable.bild_zwanzig);
                    }if(kfa > 19 && kfa < 26) {
                        bild.setImageResource(R.drawable.bild_fzwanzig);
                    }if(kfa > 24 && kfa < 31) {
                        bild.setImageResource(R.drawable.bild_dreissig);
                    }if(kfa > 29 && kfa < 36) {
                        bild.setImageResource(R.drawable.bild_fdreissig);
                    }
                } else {
                    toastMessage("Felder dürfen nicht leer sein!");
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
                //if(){
                Intent intent = new Intent(MannActivity.this, Liniendiagramm.class);
                startActivity(intent);
                //}else{
                  //  toastMessage("Graph erst ab zwei Datnsätzen nutzbar!");
           // }
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
