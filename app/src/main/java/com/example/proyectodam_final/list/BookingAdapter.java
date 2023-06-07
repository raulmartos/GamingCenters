package com.example.proyectodam_final.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectodam_final.R;
import com.example.proyectodam_final.pojo.Booking;

import java.util.List;

public class BookingAdapter extends ArrayAdapter<String> {

    Context context;
    private List<Booking> bookings;

    public BookingAdapter(Context context, List<Booking> bookings){
        super(context, R.layout.selected_item);
        this.context = context;
        this.bookings = bookings;
    }

}
