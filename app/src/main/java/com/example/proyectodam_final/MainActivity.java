package com.example.proyectodam_final;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button btnGestion;
    Button btnList;
    Button btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGestion = findViewById(R.id.btnGestion);
        btnList = findViewById(R.id.btnVer);
        btnEliminar = findViewById(R.id.btnEliminar);

        btnGestion.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CreateGestion.class);
            startActivity(intent);
        });

        btnList.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ListGestion.class);
            startActivity(intent);
        });

        btnEliminar.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, DeleteGestion.class);
            startActivity(intent);
        });

    }

}