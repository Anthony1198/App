package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageButton bMann = (ImageButton) findViewById(R.id.imageButtonMANN);
        bMann.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oeffneMann();
            }
        });

        ImageButton bFrau = (ImageButton) findViewById(R.id.imageButtonFRAU);
        bFrau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oeffneFrau();
            }
        });

    }

    public void oeffneMann(){
        Intent iMann = new Intent(this, MannActivity.class);
        startActivity(iMann);
    }
    public void oeffneFrau(){
        Intent iFrau = new Intent(this, Frau.class);
        startActivity(iFrau);
    }


}