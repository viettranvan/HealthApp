package com.example.health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health.R;
import com.example.health.modal.RepMax;

import java.util.ArrayList;


public class RepMaxAdapter extends RecyclerView.Adapter<RepMaxAdapter.ItemHolder> {

    Context myContext;
    ArrayList<RepMax> listValueRepMax;
    ItemHolder itemHolder;

    public RepMaxAdapter(Context myContext, ArrayList<RepMax> listValueRepMax) {
        this.myContext = myContext;
        this.listValueRepMax = listValueRepMax;
    }

    @NonNull
    @Override
    public RepMaxAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rep_max,null);
        itemHolder = new ItemHolder(view);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RepMaxAdapter.ItemHolder holder, int position) {
        RepMax repMax = listValueRepMax.get(position);

        String kg = repMax.getValueKG();
        String rm = repMax.getIndexRM();
        holder.tvValueKg.setText(kg);
        holder.tvIndexRM.setText(rm);
    }

    @Override
    public int getItemCount() {
        return listValueRepMax.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        TextView tvValueKg,tvIndexRM;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            tvValueKg = itemView.findViewById(R.id.tv_value_kg);
            tvIndexRM = itemView.findViewById(R.id.tv_index_RM);
        }

    }
}
