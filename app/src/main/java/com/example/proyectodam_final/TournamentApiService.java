package com.example.proyectodam_final;

import com.example.proyectodam_final.pojo.Tournament;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface TournamentApiService {
    @GET("tournaments")
    Call<List<Tournament>> getTournaments();
}
