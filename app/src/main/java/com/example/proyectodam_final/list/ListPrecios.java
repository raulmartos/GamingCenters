package com.example.proyectodam_final.list;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectodam_final.R;
import com.example.proyectodam_final.pojo.Price;
import com.example.proyectodam_final.pojo.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListPrecios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_precios);
        listPrecios();
    }

    private void listPrecios() {
        RecyclerView recyclerView = findViewById(R.id.preciosList);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("prices");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Price> pricesList = new ArrayList<>();
        PrecioAdapter precioAdapter = new PrecioAdapter(this, pricesList);
        recyclerView.setAdapter(precioAdapter);

        ref.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Price price = dataSnapshot.getValue(Price.class);
                    pricesList.add(price);
                }
                precioAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
