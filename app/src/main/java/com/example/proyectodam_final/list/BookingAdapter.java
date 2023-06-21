package com.example.proyectodam_final.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectodam_final.R;
import com.example.proyectodam_final.pojo.Booking;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.MyViewHolder> {

    Context context;
    ArrayList<Booking> bookingList;
    ArrayList<String> bookingsKeyList;


    public BookingAdapter(Context context, ArrayList<Booking> bookingList, ArrayList<String> bookingsKeyList) {
        this.context = context;
        this.bookingList = bookingList;
        this.bookingsKeyList = bookingsKeyList;
    }

    @NonNull
    @Override
    public BookingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.booking_item, parent, false);
        return new BookingAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.MyViewHolder holder, int position) {
        Booking booking = bookingList.get(position);
        String key = bookingsKeyList.get(position);
        holder.reserva.setText(key);
        holder.user.setText(booking.getUser());
        holder.fecha.setText(booking.getDate());
        holder.estado.setText(booking.getStatus());
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView reserva, user, fecha, estado;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            reserva = itemView.findViewById(R.id.tvReserva);
            user = itemView.findViewById(R.id.tvUser);
            fecha = itemView.findViewById(R.id.tvReservaFecha);
            estado = itemView.findViewById(R.id.tvStatus);
        }
    }

}