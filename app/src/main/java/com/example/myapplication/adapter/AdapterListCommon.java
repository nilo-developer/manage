package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Address;
import com.example.myapplication.model.Common;

import java.util.List;

public class AdapterListCommon extends RecyclerView.Adapter<AdapterListCommon.ViewHolder> {
    LayoutInflater inflater;
    List<Common> commons;

    public AdapterListCommon(Context context, List<Common> commons1) {
        this.inflater = LayoutInflater.from(context);
        this.commons = commons1;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_common_activity,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(commons.get(position).getName());
        holder.last_name.setText(commons.get(position).getLast_name());
        holder.jensiat.setText(commons.get(position).getJensiat());
        holder.code_meli.setText(commons.get(position).getCode_meli());
        holder.address.setText(commons.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return commons.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,last_name,jensiat,code_meli,address;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.Common1Seminar);
            last_name =itemView.findViewById(R.id.Common2Seminar);
            jensiat =itemView.findViewById(R.id.Common3Seminar);
            code_meli =itemView.findViewById(R.id.Common4Seminar);
            address =itemView.findViewById(R.id.Common5Seminar);

        }
    }
}
