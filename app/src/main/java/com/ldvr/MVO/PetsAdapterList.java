package com.ldvr.MVO;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ldvr.MVO.databinding.AdapterPetsBinding;
import com.ldvr.MVO.petsModule.Pet;

import java.util.List;

public class PetsAdapterList extends RecyclerView.Adapter<PetsAdapterList.ViewHolder>{
    List<Pet> pets;

    public PetsAdapterList(List<Pet> pets){
        this.pets = pets;
    }

    @NonNull
    @Override
    public PetsAdapterList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterPetsBinding b =  AdapterPetsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(b.getRoot(),b);
    }

    @Override
    public void onBindViewHolder(@NonNull PetsAdapterList.ViewHolder holder, int position) {
        Pet p = pets.get(position);
        holder.binding.hunger.setText(p.hunger + "");
        holder.binding.name.setText(p.id);


    }

    @Override
    public int getItemCount() {
        if(pets == null){
            return  0;
        }
        return pets.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        AdapterPetsBinding binding;
        public ViewHolder(@NonNull View itemView, AdapterPetsBinding binding) {
            super(itemView);
            this.binding = binding;
        }
    }
}
