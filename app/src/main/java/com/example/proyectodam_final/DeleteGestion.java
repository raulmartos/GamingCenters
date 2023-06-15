package com.example.proyectodam_final;

import android.content.Intent;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.example.proyectodam_final.delete.DeleteTorneo;
import android.os.Bundle;
import com.example.proyectodam_final.create.CreateBooking;
import com.example.proyectodam_final.create.CreatePrecios;
import com.example.proyectodam_final.create.CreateTorneos;
import com.example.proyectodam_final.create.CreateUsers;


public class DeleteGestion extends AppCompatActivity {

    ImageButton btnUsers, btnPrecio, btnReservas, btnTorneos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletegestion);

        btnUsers = findViewById(R.id.btnUsers);
        btnPrecio = findViewById(R.id.btnPrecio);
        btnReservas = findViewById(R.id.btnReservas);
        btnTorneos = findViewById(R.id.btnTorneos);

        /*btnUsers.setOnClickListener(view -> {
            Intent intent = new Intent(DeleteGestion.this, DeleteUsers.class);
            startActivity(intent);
        });

        btnPrecio.setOnClickListener(view -> {
            Intent intent = new Intent(DeleteGestion.this, DeletePrecios.class);
            startActivity(intent);
        });

        btnReservas.setOnClickListener(view -> {
            Intent intent = new Intent(DeleteGestion.this, DeleteBooking.class);
            startActivity(intent);
        });*/

        btnTorneos.setOnClickListener(view -> {
            Intent intent = new Intent(DeleteGestion.this, DeleteTorneo.class);
            startActivity(intent);
        });
    }
}