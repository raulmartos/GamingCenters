package com.example.proyectodam_final.delete;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.proyectodam_final.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class DeleteBooking extends AppCompatActivity {

    private Spinner spMostrarFilas;
    private Button btDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_booking);
        elementMatcher();
        mostrarBooking();
        btDelete.setOnClickListener(v -> limpiarDatos());
    }

    private void elementMatcher() {
        spMostrarFilas = findViewById(R.id.spDelBooking);
        btDelete = findViewById(R.id.btnDeleteBooking);
    }

    private void mostrarBooking() {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("bookings");
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> referencias = new ArrayList<>();

                for (DataSnapshot bookingSnapshot : dataSnapshot.getChildren()) {
                    String referencia = bookingSnapshot.getKey();
                    referencias.add(referencia);
                }

                // Utiliza las instancias obtenidas como desees
                // Puedes mostrarlas en el Spinner u otros componentes de tu aplicación
                ArrayAdapter<String> adapter = new ArrayAdapter<>(DeleteBooking.this, android.R.layout.simple_spinner_item, referencias);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spMostrarFilas.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Manejo de errores, si es necesario
            }
        });
    }


    private void limpiarDatos() {
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedInstance = spMostrarFilas.getSelectedItem().toString();

                // Obtener la referencia seleccionada de la base de datos
                DatabaseReference bookingRef = FirebaseDatabase.getInstance().getReference().child("bookings").child(selectedInstance);

                // Vaciar los campos dentro de la instancia
                bookingRef.child("createdAt").setValue(0);
                bookingRef.child("date").setValue("01/01/1970");
                bookingRef.child("status").setValue("free");
                bookingRef.child("user").setValue("");

                // Verificar si la eliminación fue exitosa
                bookingRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild("createdAt") || snapshot.hasChild("date") || snapshot.hasChild("status") || snapshot.hasChild("user")) {
                            // Error al vaciar los campos
                            Toast.makeText(DeleteBooking.this, "Error al vaciar los campos", Toast.LENGTH_SHORT).show();
                        } else {
                            // Campos vaciados exitosamente
                            Toast.makeText(DeleteBooking.this, "Campos vaciados", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Manejo de errores, si es necesario
                        Toast.makeText(DeleteBooking.this, "Error al eliminar la reserva", Toast.LENGTH_SHORT).show();
                    }
                });

                finish();
            }
        });
    }


}
