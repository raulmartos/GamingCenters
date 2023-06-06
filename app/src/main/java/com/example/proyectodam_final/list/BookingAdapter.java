package com.example.proyectodam_final.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectodam_final.R;
import com.example.proyectodam_final.pojo.Booking;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    private List<Booking> bookings;

    public BookingAdapter(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_booking, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Booking booking = bookings.get(position);
        holder.tvSeat.setText("Silla: " + booking.getSeat());
        holder.tvUser.setText("Usuario: " + booking.getUser());
        holder.tvDate.setText("Fecha: " + booking.getDate());
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    static class BookingViewHolder extends RecyclerView.ViewHolder {
        TextView tvSeat;
        TextView tvUser;
        TextView tvDate;


        BookingViewHolder(View itemView) {
            super(itemView);
            tvSeat = itemView.findViewById(R.id.tvSeat);
            tvUser = itemView.findViewById(R.id.tvUser);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }
}
