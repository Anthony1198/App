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

/**
 * Klasse für die Verarbeitung der Männer Activity
 */

public class MannActivity extends AppCompatActivity {

    /**
     * Variablen/Objekt Deklaration
     */

    DatabaseHelperMann mDatabaseHelperMann;
    private Button btnAdd, liste, graph, hilfe;
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
        hilfe = (Button) findViewById(R.id.helpButton);

        mDatabaseHelperMann = new DatabaseHelperMann(this);

        /**
         * Bei Klick auf Berechnen-Button werden die eingetragenen Werte geholt, der kfa berechnet und in die Datenabnk geschrieben.
         * Desweiteren wird das dazu passende Bild gewählt und eine Tpastmassage geworfen
         */
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bauchT.getText().length() != 0 && halsT.getText().length() != 0 &&  größeT.getText().length() != 0)  {
                    int newEntry = Integer.parseInt(bauchT.getText().toString());
                    int newEntry2 = Integer.parseInt(halsT.getText().toString());
                    int newEntry3 = Integer.parseInt(größeT.getText().toString());

                    kfa = (86.010 * Math.log10(newEntry - newEntry2)) - (70.041 * Math.log10(newEntry3)) + 30.30;

                    if(kfa > 0 && kfa < 36) {
                        AddData(newEntry, newEntry2, newEntry3, (int) kfa);
                        bauchT.setText("");
                        halsT.setText("");
                        größeT.setText("");
                        ergebnis.setText("        KFA: " + (int) kfa + "%");

                        if (kfa < 13) {
                            bild.setImageResource(R.drawable.bild_zwoelfm);
                        }
                        if (kfa > 11 && kfa < 16) {
                            bild.setImageResource(R.drawable.bild_fzehnm);
                        }
                        if (kfa > 14 && kfa < 21) {
                            bild.setImageResource(R.drawable.bild_zwanzigm);
                        }
                        if (kfa > 19 && kfa < 26) {
                            bild.setImageResource(R.drawable.bild_fzwanzigm);
                        }
                        if (kfa > 24 && kfa < 31) {
                            bild.setImageResource(R.drawable.bild_dreissigm);
                        }
                        if (kfa > 29 && kfa < 36) {
                            bild.setImageResource(R.drawable.bild_fdreissigm);
                        }
                    }
                    if(kfa < 0){
                        toastMessage("Der Körperfettanteil kann nicht unter 0% liegen!");
                        bauchT.setText("");
                        halsT.setText("");
                        größeT.setText("");
                    }
                    if(kfa > 35){
                        toastMessage("Ihr Köperfettanteil ist über 35%. Bitte suchen Sie einen Arzt auf!");
                        AddData(newEntry, newEntry2, newEntry3, (int) kfa);
                        bauchT.setText("");
                        halsT.setText("");
                        größeT.setText("");
                        ergebnis.setText("        KFA: " + (int) kfa + "%");
                        bild.setImageResource(R.drawable.bild_arzt);
                    }
                } else {
                    toastMessage("Felder dürfen nicht leer sein!");
                }
            }
        });

        /**
         * Bei Klick auf Listen-Button wird durch ein Intent die ListData Klasse aufgerufen
         */
        liste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MannActivity.this, ListDataActivityMann.class);
                startActivity(intent);
            }
        });

        /**
         * Bei Klick auf Graph-Button wird durch ein Intent die Liniendiagramm Klasse aufgerufen
         */
        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rows = mDatabaseHelperMann.getRowsCount();
                if(rows >1){
                Intent intent = new Intent(MannActivity.this, LiniendiagrammMann.class);
                startActivity(intent);
                }else{
                   toastMessage("Graph erst ab zwei Datensätzen nutzbar!");
            }
        }
    });

        /**
         * Bei Klick auf Hilfe-Button (Fragezeichen) wird durch ein Intent die Hilfe Klasse aufgerufen
         */
        hilfe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oeffneHilfe();
            }
        });
    }

    public void AddData(int newEntry, int newEntry2, int newEntry3, int newEntry4) {
        boolean insertData = mDatabaseHelperMann.addData(newEntry, newEntry2, newEntry3, newEntry4);

        if (kfa < 36) {
            if (insertData) {
                toastMessage("Daten wurden erfolgreich gespeichert!");
            } else {
                toastMessage("Etwas ist schief gelaufen :(");
            }
        }
        if (kfa < 36) {
            if (insertData) {
                toastMessage("Daten wurden erfolgreich gespeichert!");
            } else {
                toastMessage("Etwas ist schief gelaufen :(");
            }
        }
        if (kfa > 35) {
            if (insertData) {
            //nichts, da Meldung wegen Arzt!
            } else {
                toastMessage("Etwas ist schief gelaufen :(");
            }
        }
    }

    public void oeffneHilfe(){
        Intent iHilfe = new Intent(this, HilfeActivity.class);
        startActivity(iHilfe);
    }

    /**
     * Nachrichten-Ausgabe
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
