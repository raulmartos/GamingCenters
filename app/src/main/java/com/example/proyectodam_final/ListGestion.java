package com.example.proyectodam_final;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.example.proyectodam_final.create.CreatePrecios;
import com.example.proyectodam_final.list.ListBooking;
import com.example.proyectodam_final.list.ListPrecios;
import com.example.proyectodam_final.list.ListTournament;
import com.example.proyectodam_final.list.ListUsers;

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
            Intent intent = new Intent(ListGestion.this, ListUsers.class);
            startActivity(intent);
        });

        btnPrecio.setOnClickListener(view -> {
            Intent intent = new Intent(ListGestion.this, ListPrecios.class);
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