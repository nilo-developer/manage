package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.ListSeminar;
import com.example.myapplication.model.Seminar;

import java.util.List;

public class AdapterListSeminar extends RecyclerView.Adapter<AdapterListSeminar.ViewHolder> {

    LayoutInflater inflater;
    List<ListSeminar> seminar1;

    public AdapterListSeminar(Context ctx, List<ListSeminar> seminar1){
        this.inflater = LayoutInflater.from(ctx);
        this.seminar1 = seminar1;
    }


////3 متد oncreateViewHolder , onBindViewHolder , getItemcount در نتیجه impliment کردن کلاس اداپتر

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_view_seminar,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // bind the data
        holder.title.setText(seminar1.get(position).getTitle());
        holder.type.setText(seminar1.get(position).getType());
        holder.city.setText(seminar1.get(position).getCity());
        holder.location.setText(seminar1.get(position).getLocation());
        holder.day.setText(seminar1.get(position).getDay());
    }

    @Override
    public int getItemCount() {
        return seminar1.size();
    }


    public static class ViewHolder extends  RecyclerView.ViewHolder{
        TextView title,type,city,location,day,txt_sabt;
//        Button add_sans;
//        Spinner spinner4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.txt_title);
            type = itemView.findViewById(R.id.txt_type);
            city = itemView.findViewById(R.id.txt_city);
            location = itemView.findViewById(R.id.txt_location);
            day = itemView.findViewById(R.id.txt_day);
            txt_sabt = itemView.findViewById(R.id.txt_sabt);
//            add_sans = itemView.findViewById(R.id.add_sans);
//            spinner4 = itemView.findViewById(R.id.spinner4);

            // handle onClick

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "دریافت اطلاعات", Toast.LENGTH_SHORT).show();
                }
            });
//            itemView.findViewById(R.id.add_sans).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(v.getContext(), "", Toast.LENGTH_SHORT).show();
//                }
//            });
//            itemView.findViewById(R.id.spinner4).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(v.getContext(), "", Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }
}
