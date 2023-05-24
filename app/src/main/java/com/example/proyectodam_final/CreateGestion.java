package com.example.proyectodam_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.example.proyectodam_final.create.CreateBooking;
import com.example.proyectodam_final.create.CreatePrecios;
import com.example.proyectodam_final.create.CreateTorneos;
import com.example.proyectodam_final.create.CreateUsers;

public class CreateGestion extends AppCompatActivity {

    ImageButton btnUsers, btnPrecio, btnReservas, btnTorneos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion);

        btnUsers = findViewById(R.id.btnUsers);
        btnPrecio = findViewById(R.id.btnPrecio);
        btnReservas = findViewById(R.id.btnReservas);
        btnTorneos = findViewById(R.id.btnTorneos);

        btnUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(CreateGestion.this, CreateUsers.class);
                startActivity(intent);
            }
        });

        btnPrecio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(CreateGestion.this, CreatePrecios.class);
                startActivity(intent);
            }
        });

        btnReservas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(CreateGestion.this, CreateBooking.class);
                startActivity(intent);
            }
        });

        btnTorneos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(CreateGestion.this, CreateTorneos.class);
                startActivity(intent);
            }
        });
    }

}