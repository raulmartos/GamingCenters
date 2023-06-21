package com.example.proyectodam_final.list;

import static com.example.proyectodam_final.list.TournamentAdapter.doubleParser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectodam_final.R;
import com.example.proyectodam_final.pojo.Price;

import java.util.ArrayList;

public class PrecioAdapter extends RecyclerView.Adapter<PrecioAdapter.MyViewHolder> {
    Context context;
    ArrayList<Price> pricesList;


    public PrecioAdapter(Context context, ArrayList<Price> pricesList) {
        this.context = context;
        this.pricesList = pricesList;
    }

    @NonNull
    @Override
    public PrecioAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_prices_item, parent, false);
        return new PrecioAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PrecioAdapter.MyViewHolder holder, int position) {
        Price price = pricesList.get(position);
        holder.type.setText(price.getType());
        holder.price.setText(doubleParser(price.getPrice()));
    }

    @Override
    public int getItemCount() {
        return pricesList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView type, price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.tvListType);
            price = itemView.findViewById(R.id.tvListPrice);
        }
    }

}
