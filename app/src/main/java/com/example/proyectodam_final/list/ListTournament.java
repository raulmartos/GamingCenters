package com.example.proyectodam_final.list;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.example.proyectodam_final.R;

public class ListTournament extends AppCompatActivity {

    private TournamentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_tournament);

    }
}
