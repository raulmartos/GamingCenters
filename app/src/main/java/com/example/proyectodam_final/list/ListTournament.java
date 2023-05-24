package com.example.proyectodam_final.list;

import android.os.Bundle;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.proyectodam_final.R;
import com.example.proyectodam_final.TournamentApiService;
import com.example.proyectodam_final.pojo.Tournament;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.ArrayList;
import java.util.List;

public class ListTournament extends AppCompatActivity {

    private TournamentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_tournament);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.calov.click/getTournaments/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TournamentApiService apiService = retrofit.create(TournamentApiService.class);
        LinearLayout linearLayout = findViewById(R.id.llTorunament);
        adapter = new TournamentAdapter(new ArrayList<>());

        //linearLayout.addView(adapter);

        Call<List<Tournament>> call = apiService.getTournaments();

        call.enqueue(new Callback<List<Tournament>>() {
            public void onResponse(Call<List<Tournament>> call, Response<List<Tournament>> response) {
                if (response.isSuccessful()) {
                    List<Tournament> tournaments = response.body();
                    adapter.setTournaments(tournaments);
                } else {
                    // Mostrar error si la respuesta no fue exitosa
                }
            }

            public void onFailure(Call<List<Tournament>> call, Throwable t) {
                // Mostrar error si la petición falla
            }
        });

    }
}
