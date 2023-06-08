package com.example.proyectodam_final.create;

import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.proyectodam_final.R;
import com.example.proyectodam_final.pojo.Price;
import com.google.firebase.database.*;


public class CreatePrecios extends AppCompatActivity {

    private Spinner spTipo;
    private EditText edtPrice;
    private Button btAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precios);
        elementMatcher();
        spinnerInfo();
        btAdd.setOnClickListener(v -> updatePrice());
    }

    private void elementMatcher() {
        edtPrice = findViewById(R.id.edtPrecioEntrada);
        spTipo = findViewById(R.id.spTipo);
        btAdd = findViewById(R.id.btnSavePrecio);
    }

    private void spinnerInfo() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTipo.setAdapter(adapter);
    }

    private void updatePrice() {
        int selectedPosition = spTipo.getSelectedItemPosition();
        if (selectedPosition == 0) {
            Toast.makeText(CreatePrecios.this, "Debes seleccionar un tipo", Toast.LENGTH_SHORT).show();
        } else {
            String type = spTipo.getSelectedItem().toString();
            String priceText = edtPrice.getText().toString();
            if (priceText.isEmpty()) {
                Toast.makeText(CreatePrecios.this, "Por favor, ingresa un precio", Toast.LENGTH_SHORT).show();
            } else {
                double price = Double.parseDouble(priceText);

                DatabaseReference pricesRef = FirebaseDatabase.getInstance().getReference().child("prices");
                pricesRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int currentPosition = 1;
                        for (DataSnapshot priceSnapshot : dataSnapshot.getChildren()) {
                            if (currentPosition == selectedPosition) {
                                Price updatedPrice = new Price(type, price);
                                priceSnapshot.getRef().setValue(updatedPrice);
                                Toast.makeText(CreatePrecios.this, "Precio actualizado exitosamente", Toast.LENGTH_SHORT).show();
                                finish();
                                return;
                            }
                            currentPosition++;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(CreatePrecios.this, "Error al actualizar el precio", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }



}