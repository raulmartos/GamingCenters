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

    }
}
