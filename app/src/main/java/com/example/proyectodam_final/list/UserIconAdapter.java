package com.example.proyectodam_final.list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proyectodam_final.R;

import java.util.List;

public class UserIconAdapter extends ArrayAdapter<String> {
    Context context;
    List<String> iconList;

    public UserIconAdapter(Context context, List<String> iconList) {
        super(context, R.layout.selected_item, iconList);
        this.context = context;
        this.iconList = iconList;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.user_icon_list, parent, false);

        TextView iconName = row.findViewById(R.id.text);
        ImageView icon = row.findViewById(R.id.img);

        if (position == 0) {
            iconName.setText("");
            icon.setImageDrawable(null);
        } else {
            Resources res = context.getResources();
            String drawableName = iconList.get(position - 1).toLowerCase();
            @SuppressLint("DiscouragedApi") int resId = res.getIdentifier(drawableName, "drawable", context.getPackageName());
            @SuppressLint("UseCompatLoadingForDrawables") Drawable drawable = res.getDrawable(resId);

            iconName.setText(iconList.get(position - 1));
            icon.setImageDrawable(drawable);
        }
        return row;
    }
}