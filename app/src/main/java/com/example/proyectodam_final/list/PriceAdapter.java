/*package com.example.proyectodam_final.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.proyectodam_final.R;

public class PriceAdapter extends RecyclerView.Adapter<PriceAdapter.PrecioViewHolder> {

    private List<Precio> preciosList;

    public PriceAdapter(PreciosActivity preciosList) {
        this.preciosList = preciosList;
    }

    @Override
    public PrecioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.precio_item_layout, parent, false);
        return new PrecioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PrecioViewHolder holder, int position) {
        Precio precio = preciosList.get(position);
        holder.txtType.setText(precio.getType());
        holder.txtPrice.setText(String.valueOf(precio.getPrice()));
    }

    @Override
    public int getItemCount() {
        return preciosList.size();
    }

    public static class PrecioViewHolder extends RecyclerView.ViewHolder {
        TextView txtType, txtPrice;

        public PrecioViewHolder(View itemView) {
            super(itemView);
            txtType = itemView.findViewById(R.id.txt_type);
            txtPrice = itemView.findViewById(R.id.txt_price);
        }
    }
}*/

