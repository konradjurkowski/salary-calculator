package com.example.salarycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mBtn_UP;
    private Button mBtn_UZ;
    private Button mBtn_UD;
    private int Request_code=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtn_UP = (Button) findViewById(R.id.btn_umowa_o_prace);
        mBtn_UZ = (Button) findViewById(R.id.umowa_zlecenie);
        mBtn_UD = (Button) findViewById(R.id.umowa_o_dzielo);

       mBtn_UP.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               openUmowaPraca();
           }
       });

       mBtn_UZ.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               openUmowaZlecenie();
           }
       });

       mBtn_UD.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               openUmowaDzielo();
           }
       });
    }

    public void openUmowaDzielo() {
        Intent intent = new Intent(this, UmowaDzielo.class);
        startActivity(intent);
    }

    public void openUmowaPraca() {
        Intent intent = new Intent(this, UmowaPraca.class);
        startActivity(intent);
    }

    public void openUmowaZlecenie() {
        Intent intent = new Intent(this, UmowaZlecenie.class);
        startActivity(intent);
    }
}