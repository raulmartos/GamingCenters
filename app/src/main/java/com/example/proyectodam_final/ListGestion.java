package com.example.proyectodam_final;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.example.proyectodam_final.create.CreatePrecios;
import com.example.proyectodam_final.create.CreateTorneos;
import com.example.proyectodam_final.create.CreateUsers;
import com.example.proyectodam_final.list.ListBooking;
import com.example.proyectodam_final.list.ListTournament;

public class ListGestion extends AppCompatActivity {

    ImageButton btnUsers, btnPrecio, btnReservas, btnTorneos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listgestion);

        btnUsers = findViewById(R.id.btnUsers);
        btnPrecio = findViewById(R.id.btnPrecio);
        btnReservas = findViewById(R.id.btnReservas);
        btnTorneos = findViewById(R.id.btnTorneos);

        btnUsers.setOnClickListener(view -> {
            Intent intent = new Intent(ListGestion.this, CreateUsers.class);
            startActivity(intent);
        });

        btnPrecio.setOnClickListener(view -> {
            Intent intent = new Intent(ListGestion.this, CreatePrecios.class);
            startActivity(intent);
        });

        btnReservas.setOnClickListener(view -> {
            Intent intent = new Intent(ListGestion.this, ListBooking.class);
            startActivity(intent);
        });

        btnTorneos.setOnClickListener(view -> {
            Intent intent = new Intent(ListGestion.this, ListTournament.class);
            startActivity(intent);
        });
    }

}