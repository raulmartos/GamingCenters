package com.example.proyectodam_final.list;

import android.annotation.SuppressLint;
import android.os.Bundle;


import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectodam_final.R;
import com.example.proyectodam_final.pojo.Booking;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ListBooking extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_booking);
        listBooking();
    }

    private void listBooking() {
        RecyclerView recyclerView = findViewById(R.id.reservaList);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("bookings");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Booking> bookingList = new ArrayList<>();
        ArrayList<String> bookingKeysList = new ArrayList<>();
        BookingAdapter bookingAdapter = new BookingAdapter(this, bookingList, bookingKeysList);
        recyclerView.setAdapter(bookingAdapter);

        ref.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Booking booking = dataSnapshot.getValue(Booking.class);
                    String key = dataSnapshot.getKey();
                    bookingList.add(booking);
                    bookingKeysList.add(key);
                }
                bookingAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}