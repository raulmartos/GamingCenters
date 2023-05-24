package com.example.proyectodam_final.list;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.proyectodam_final.BookingApiService;
import com.example.proyectodam_final.R;
import com.example.proyectodam_final.pojo.Booking;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.ArrayList;
import java.util.List;

public class ListBooking extends AppCompatActivity {

    private BookingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_booking);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.calov.click/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookingApiService apiService = retrofit.create(BookingApiService.class);
        RecyclerView recyclerView = findViewById(R.id.rvBooking);
        adapter = new BookingAdapter(new ArrayList<>());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Call<List<Booking>> call = apiService.getBookings();

        call.enqueue(new Callback<List<Booking>>() {
            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                if (response.isSuccessful()) {
                    List<Booking> bookings = response.body();
                    adapter.setBookings(bookings);
                    adapter.notifyDataSetChanged();
                } else {
                    // Mostrar error si la respuesta no fue exitosa
                }
            }

            public void onFailure(Call<List<Booking>> call, Throwable t) {
                // Mostrar error si la petición falla
            }
        });

    }
}
