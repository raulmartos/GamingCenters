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
import com.example.proyectodam_final.pojo.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DeleteUser extends AppCompatActivity {

    private Spinner spMostrarUsuarios;
    private Button btDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_users);
        elementMatcher();
        mostrarUsuarios();
        btDelete.setOnClickListener(v -> delUser());
    }

    private void elementMatcher() {
        spMostrarUsuarios = findViewById(R.id.spDelUsers);
        btDelete = findViewById(R.id.btnDeleteUser);
    }

    private void mostrarUsuarios() {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("users");
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> referencias = new ArrayList<>();

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String referencia = userSnapshot.getKey();
                    referencias.add(referencia);
                }

                // Utiliza los nombres de usuario obtenidos como desees
                // Puedes mostrarlos en el Spinner u otros componentes de tu aplicación
                ArrayAdapter<String> adapter = new ArrayAdapter<>(DeleteUser.this, android.R.layout.simple_spinner_item, referencias);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spMostrarUsuarios.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Manejo de errores, si es necesario
            }
        });
    }

    private void delUser() {
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedReference = spMostrarUsuarios.getSelectedItem().toString();

                // Eliminar el usuario seleccionado de la base de datos
                DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(selectedReference);
                userRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Eliminación exitosa
                            Toast.makeText(DeleteUser.this, "Usuario eliminado", Toast.LENGTH_SHORT).show();
                        } else {
                            // Error al eliminar
                            Toast.makeText(DeleteUser.this, "Error al eliminar el usuario", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                finish();
            }
        });
    }
}
