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
import com.example.myapplication.model.ListSeminar;

import java.util.List;

public class AdapterViewSeminar extends RecyclerView.Adapter<AdapterViewSeminar.ViewHolder> {

    LayoutInflater inflater;
    List<ListSeminar> listSeminars;

    public AdapterViewSeminar(Context ctx, List<ListSeminar> viewSeminars){
        this.inflater = LayoutInflater.from(ctx);
        this.listSeminars = viewSeminars;
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
        holder.title.setText(listSeminars.get(position).getTitle());
        holder.type.setText(listSeminars.get(position).getType());
        holder.city.setText(listSeminars.get(position).getCity());
        holder.location.setText(listSeminars.get(position).getLocation());
        holder.day.setText(listSeminars.get(position).getDay());
    }

    @Override
    public int getItemCount() {
        return listSeminars.size();
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
