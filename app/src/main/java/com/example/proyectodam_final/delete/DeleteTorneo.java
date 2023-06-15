package com.example.proyectodam_final.delete;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.proyectodam_final.R;
import com.example.proyectodam_final.pojo.Tournament;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DeleteTorneo extends AppCompatActivity {

    private Spinner spMostrarTorneos;
    private Button btDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_torneos);
        elementMatcher();
        mostrarTorneo();
        btDelete.setOnClickListener(v -> delTournament());
    }

    private void elementMatcher() {
        spMostrarTorneos = findViewById(R.id.spDelTournaments);
        btDelete = findViewById(R.id.btnDelete);
    }

    private void mostrarTorneo() {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("tournaments");
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> referencias = new ArrayList<>();

                for (DataSnapshot tournamentSnapshot : dataSnapshot.getChildren()) {
                    String referencia = tournamentSnapshot.getKey();
                    referencias.add(referencia);
                }

                // Utiliza las referencias obtenidas como desees
                // Puedes mostrarlas en el Spinner u otros componentes de tu aplicación
                ArrayAdapter<String> adapter = new ArrayAdapter<>(DeleteTorneo.this, android.R.layout.simple_spinner_item, referencias);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spMostrarTorneos.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Manejo de errores, si es necesario
            }
        });
    }

    private void delTournament() {
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedReference = spMostrarTorneos.getSelectedItem().toString();

                // Eliminar la referencia seleccionada de la base de datos
                DatabaseReference tournamentRef = FirebaseDatabase.getInstance().getReference().child("tournaments").child(selectedReference);
                tournamentRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Eliminación exitosa
                            Toast.makeText(DeleteTorneo.this, "Torneo eliminado", Toast.LENGTH_SHORT).show();
                        } else {
                            // Error al eliminar
                            Toast.makeText(DeleteTorneo.this, "Error al eliminar el torneo", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                finish();

            }
        });
    }

}