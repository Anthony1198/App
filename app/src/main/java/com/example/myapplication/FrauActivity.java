package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

class FrauActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frau);


        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText bauchTEXT = (EditText) findViewById(R.id.editText2);
                EditText halsTEXT = (EditText) findViewById(R.id.editText3);
                EditText groeßeTEXT = (EditText) findViewById(R.id.editText4);
                TextView ergebnis = (TextView) findViewById(R.id.textView2);
                ImageView bild = (ImageView) findViewById(R.id.imageView2);

                double bauch = Double.parseDouble(bauchTEXT.getText().toString());
                double hals = Double.parseDouble(halsTEXT.getText().toString());
                double groeße = Double.parseDouble(groeßeTEXT.getText().toString());


                double kfa = (86.010 * Math.log(bauch - hals)) - (70.041 * Math.log(groeße)) + 30.30;
                ergebnis.setText("KFA (Frau): " + kfa);

            }
        });
    }
}