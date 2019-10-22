package com.example.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    final String databaseWert = "databaseData.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //diagrammOeffnen();

                berechne();


            }
        });
    }

    public void berechne(){
        RadioButton m = (RadioButton) findViewById(R.id.radioButton2);
        RadioButton w = (RadioButton) findViewById(R.id.radioButton3);
        EditText bauchTEXT = (EditText) findViewById(R.id.editText2);
        EditText halsTEXT = (EditText) findViewById(R.id.editText3);
        EditText groeßeTEXT = (EditText) findViewById(R.id.editText4);
        TextView ergebnis = (TextView) findViewById(R.id.textView2);
        ImageView bild = (ImageView) findViewById(R.id.imageView2);

        double bauch = Double.parseDouble(bauchTEXT.getText().toString());
        double hals = Double.parseDouble(halsTEXT.getText().toString());
        double groeße = Double.parseDouble(groeßeTEXT.getText().toString());


                double kfa = (86.010 * Math.log(bauch - hals)) - (70.041 * Math.log(groeße)) + 30.30;
                ergebnis.setText("KFA: " + kfa);
                if(kfa > 30) {
                    bild.setImageResource(R.drawable.ic_launcher_background);
                }else {
                    bild.setImageResource(R.drawable.abc);
                }


           }

    public void diagrammOeffnen(){
        Intent inte = new Intent(this, Liniendiagramm.class);
        startActivity(inte);
    }

}