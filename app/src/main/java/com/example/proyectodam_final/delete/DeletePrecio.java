package com.example.proyectodam_final.delete;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.proyectodam_final.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DeletePrecio extends AppCompatActivity {

    private Spinner spMostrarPrecios;
    private Button btDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_precio);
        elementMatcher();
        mostrarPrecios();
        btDelete.setOnClickListener(v -> delPrice());
    }

    private void elementMatcher() {
        spMostrarPrecios = findViewById(R.id.spDelPrecio);
        btDelete = findViewById(R.id.btnDeletePrice);
    }

    private void mostrarPrecios() {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("prices");
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> referencias = new ArrayList<>();

                for (DataSnapshot priceSnapshot : dataSnapshot.getChildren()) {
                    String referencia =  priceSnapshot.getKey();
                    referencias.add(referencia);
                }

                // Utiliza los precios obtenidos como desees
                // Puedes mostrarlos en el Spinner u otros componentes de tu aplicación
                ArrayAdapter<String> adapter = new ArrayAdapter<>(DeletePrecio.this, android.R.layout.simple_spinner_item, referencias);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spMostrarPrecios.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Manejo de errores, si es necesario
            }
        });
    }


    private void delPrice() {
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedReference = spMostrarPrecios.getSelectedItem().toString();

                // Obtener la referencia seleccionada de la base de datos
                DatabaseReference priceRef = FirebaseDatabase.getInstance().getReference().child("prices").child(selectedReference);
                priceRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Eliminación exitosa
                            Toast.makeText(DeletePrecio.this, "Precio eliminado", Toast.LENGTH_SHORT).show();
                        } else {
                            // Error al eliminar
                            Toast.makeText(DeletePrecio.this, "Error al eliminar el precio", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                finish();

            }
        });
    }
}
