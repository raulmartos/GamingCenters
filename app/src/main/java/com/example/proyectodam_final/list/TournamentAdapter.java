package com.example.proyectodam_final.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectodam_final.R;
import com.example.proyectodam_final.pojo.Tournament;

import java.util.ArrayList;

public class TournamentAdapter extends RecyclerView.Adapter<TournamentAdapter.MyViewHolder>{

    Context context;
    ArrayList<Tournament> tournamentList;


    public TournamentAdapter(Context context, ArrayList<Tournament> tournamentList) {
        this.context = context;
        this.tournamentList = tournamentList;
    }

    @NonNull
    @Override
    public TournamentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_tournament_item, parent, false);
        return new TournamentAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TournamentAdapter.MyViewHolder holder, int position) {
        Tournament tournament = tournamentList.get(position);
        holder.titulo.setText(tournament.getTitle());
        holder.ubicacion.setText(tournament.getLocation());
        holder.fecha.setText(tournament.getDate());
        holder.hora.setText(tournament.getHour());
        holder.plataforma.setText(tournament.getPlatform());
        holder.precio.setText(doubleParser(tournament.getPriceEntry()));
        holder.premio.setText(doubleParser(tournament.getReward()));
        holder.jugadores.setText(intParser(tournament.getTotalPlayers()));
    }

    @Override
    public int getItemCount() {
        return tournamentList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titulo, ubicacion, fecha, hora, plataforma, precio, premio, jugadores;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            titulo = itemView.findViewById(R.id.tvTitle);
            ubicacion = itemView.findViewById(R.id.tvLocation);
            fecha = itemView.findViewById(R.id.tvDate);
            hora = itemView.findViewById(R.id.tvHour);
            plataforma = itemView.findViewById(R.id.tvPlatform);
            precio = itemView.findViewById(R.id.tvPrice);
            premio = itemView.findViewById(R.id.tvPremio);
            jugadores = itemView.findViewById(R.id.tvPlayers);
        }
    }
    protected static String doubleParser(double value){
        return Double.toString(value);
    }
    protected static String intParser(int value){
        return Integer.toString(value);
    }
}