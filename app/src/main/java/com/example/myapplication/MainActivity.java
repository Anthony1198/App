package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import androidx.appcompat.app.AppCompatActivity;

/**
 * Klasse für die Auswahl des Geschlechtes auf der Startseite
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Bei Klick auf Mann-Bildbutton wird durch ein Intent die Mann Klasse aufgerufen
         */
        ImageButton bMann = (ImageButton) findViewById(R.id.imageButtonMANN);
        bMann.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oeffneMann();
            }
        });

        /**
         * Bei Klick auf Frauen-Bildbutton wird durch ein Intent die Frauen Klasse aufgerufen
         */
        ImageButton bFrau = (ImageButton) findViewById(R.id.imageButtonFRAU);
        bFrau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oeffneFrau();
            }
        });

        /**
         * Bei Klick auf Hilfe-Button (Fragezeichen) wird durch ein Intent die Hilfe Klasse aufgerufen
         */
        Button bHilfe = (Button) findViewById(R.id.helpButton);
        bHilfe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oeffneHilfe();
            }
        });

    }

    public void oeffneMann(){
        Intent iMann = new Intent(this, MannActivity.class);
        startActivity(iMann);
    }
    public void oeffneFrau(){
        Intent iFrau = new Intent(this, FrauActivity.class);
        startActivity(iFrau);
    }
    public void oeffneHilfe(){
        Intent iHilfe = new Intent(this, HilfeActivity.class);
        startActivity(iHilfe);
    }
}