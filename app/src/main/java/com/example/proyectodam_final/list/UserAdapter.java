package com.example.proyectodam_final.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectodam_final.R;
import com.example.proyectodam_final.pojo.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{

    Context context;
    ArrayList<User> userList;


    public UserAdapter(Context context, ArrayList<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_user_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = userList.get(position);
        holder.user.setText(user.getName());
        holder.names.setText(user.getLastName());
        holder.email.setText(user.getEmail());
        holder.birthday.setText(user.getBirthday());
        holder.role.setText(user.getRole());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView user, names, email, birthday, role;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            user = itemView.findViewById(R.id.tvUser);
            names = itemView.findViewById(R.id.tvNames);
            email = itemView.findViewById(R.id.tvEmail);
            birthday = itemView.findViewById(R.id.tvBirthday);
            role = itemView.findViewById(R.id.tvRol);
        }
    }
}