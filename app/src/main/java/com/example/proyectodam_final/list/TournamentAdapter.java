package com.example.proyectodam_final.list;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.proyectodam_final.R;
import com.example.proyectodam_final.pojo.Tournament;

import java.util.List;

public class TournamentAdapter {

    private List<Tournament> tournaments;
    private LinearLayout linearLayout;

    public TournamentAdapter(List<Tournament> tournaments) {
        this.tournaments = tournaments;
        this.linearLayout = linearLayout;
    }

    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
        updateView();
    }

    private void updateView() {
        LayoutInflater inflater = LayoutInflater.from(linearLayout.getContext());
        linearLayout.removeAllViews();
        for (Tournament tournament : tournaments) {
            View itemView = inflater.inflate(R.layout.list_tournament, linearLayout, false);
            TextView titleTextView = itemView.findViewById(R.id.tvTitle);
            TextView dateTextView = itemView.findViewById(R.id.tvDateT);
            TextView hourTextView = itemView.findViewById(R.id.tvHour);
            TextView locationTextView = itemView.findViewById(R.id.tvLocation);
            TextView priceEntryTextView = itemView.findViewById(R.id.tvPriceEntry);
            TextView rewardTextView = itemView.findViewById(R.id.tvReward);
            TextView platformTextView = itemView.findViewById(R.id.tvPlatform);
            TextView totalPlayersTextView = itemView.findViewById(R.id.tvTotalPlayers);
            //ImageView backgroundImageView = itemView.findViewById(R.id.ivBackground);

            titleTextView.setText(tournament.getTitle());
            dateTextView.setText(tournament.getDate());
            hourTextView.setText(tournament.getHour());
            locationTextView.setText(tournament.getLocation());
            priceEntryTextView.setText(String.valueOf(tournament.getPriceEntry()));
            rewardTextView.setText(String.valueOf(tournament.getReward()));
            platformTextView.setText(tournament.getPlatform());
            totalPlayersTextView.setText(String.valueOf(tournament.getTotalPlayers()));
            // Aquí puedes cargar la imagen de fondo del torneo usando Picasso o Glide, por ejemplo:
            // Picasso.get().load(tournament.getBackground()).into(backgroundImageView);

            linearLayout.addView(itemView);
        }
    }

}
