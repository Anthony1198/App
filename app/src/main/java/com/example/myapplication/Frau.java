package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Frau extends AppCompatActivity {

    DatabaseHelperFrau mDatabaseHelperFrau;
    private Button btnAdd, liste, graph;
    private EditText bauchT, halsT, größeT, hüfteT;
    private ImageView bild;
    private TextView ergebnis;
    double kfa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frau2);
        bauchT = (EditText) findViewById(R.id.editText2);
        halsT = (EditText) findViewById(R.id.editText3);
        größeT = (EditText) findViewById(R.id.editText4);
        hüfteT = (EditText) findViewById(R.id.editText5);
        ergebnis = (TextView) findViewById(R.id.textView2);
        bild = (ImageView) findViewById(R.id.imageView2);
        btnAdd = (Button) findViewById(R.id.button);
        liste = (Button) findViewById(R.id.button2);
        graph = (Button) findViewById(R.id.button3);

        mDatabaseHelperFrau = new DatabaseHelperFrau(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bauchT.getText().length() != 0 && halsT.getText().length() != 0 &&  größeT.getText().length() != 0 && hüfteT.getText().length() != 0)  {
                    int newEntry = Integer.parseInt(bauchT.getText().toString());
                    int newEntry2 = Integer.parseInt(halsT.getText().toString());
                    int newEntry3 = Integer.parseInt(größeT.getText().toString());
                    int newEntry4 = Integer.parseInt(hüfteT.getText().toString());

                    kfa = (163.205 * Math.log10(newEntry + newEntry4 - newEntry2)) - (97.684 * Math.log10(newEntry3)) - (104.912);
                    AddData(newEntry, newEntry2, newEntry3, newEntry4, (int)kfa);
                    bauchT.setText("");
                    halsT.setText("");
                    größeT.setText("");
                    hüfteT.setText("");
                    ergebnis.setText("Kfa: " + (int)kfa);

                    if(kfa < 21) {
                        bild.setImageResource(R.drawable.bild_zwanzigf);
                    }if(kfa > 20 && kfa < 26) {
                        bild.setImageResource(R.drawable.bild_fzwanzigf);
                    }if(kfa > 25 && kfa < 31) {
                        bild.setImageResource(R.drawable.bild_dreissigf);
                    }if(kfa > 30 && kfa < 36) {
                        bild.setImageResource(R.drawable.bild_fdreissigf);
                    }if(kfa > 35 && kfa < 41) {
                        bild.setImageResource(R.drawable.bild_vierzigf);
                    }if(kfa > 40 && kfa < 46) {
                        bild.setImageResource(R.drawable.bild_fvierzigf);
                    }
                } else {
                    toastMessage("Felder dürfen nicht leer sein!");
                }
            }
        });

        liste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Frau.this, ListDataActivityFrau.class);
                startActivity(intent);
            }
        });

        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rows = mDatabaseHelperFrau.getRowsCount();
                if(rows >1){
                    Intent intent = new Intent(Frau.this, LiniendiagrammFrau.class);
                    startActivity(intent);
                }else{
                    toastMessage("Graph erst ab zwei Datensätzen nutzbar!");
                }
            }
        });
    }

    public void AddData(int newEntry, int newEntry2, int newEntry3, int newEntry4, int newEntry5) {
        boolean insertData = mDatabaseHelperFrau.addData(newEntry, newEntry2, newEntry3, newEntry4, newEntry5);

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
