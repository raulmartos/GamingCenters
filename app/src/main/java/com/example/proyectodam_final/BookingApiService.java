package com.example.proyectodam_final;

import com.example.proyectodam_final.create.CreateBooking;
import com.example.proyectodam_final.list.ListBooking;
import com.example.proyectodam_final.pojo.Booking;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface BookingApiService {

    @POST("createBooking")
    Call<CreateBooking> createBooking(
            @Query("name") String edtSillaInput,
            @Query("user") String edtUserInput,
            @Query("date") String edtDateInput,
            @Query("status") String spStatusInput
    );

    @GET("listBookings")
    Call<List<Booking>> getBookings();
}

