package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Seminar;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    LayoutInflater inflater;
    List<Seminar> seminars;

    public Adapter(Context ctx, List<Seminar> seminars){
        this.inflater = LayoutInflater.from(ctx);
        this.seminars = seminars;
    }


////3 متد oncreateViewHolder , onBindViewHolder , getItemcount در نتیجه impliment کردن کلاس اداپتر

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_seminar,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // bind the data
        holder.title.setText(seminars.get(position).getTitle());
        holder.type.setText(seminars.get(position).getType());
        holder.city.setText(seminars.get(position).getCity());
        holder.location.setText(seminars.get(position).getLocation());
        holder.day.setText(seminars.get(position).getDay());
    }

    @Override
    public int getItemCount() {
        return seminars.size();
    }


    public  class ViewHolder extends  RecyclerView.ViewHolder{
        TextView title,type,city,location,day;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.txt_title);
            type = itemView.findViewById(R.id.txt_type);
            city = itemView.findViewById(R.id.txt_city);
            location = itemView.findViewById(R.id.txt_location);
            day = itemView.findViewById(R.id.txt_day);

            // handle onClick

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "دریافت اطلاعات", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
