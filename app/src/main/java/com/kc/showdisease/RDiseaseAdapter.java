package com.kc.showdisease;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RDiseaseAdapter extends RecyclerView.Adapter<RDiseaseAdapter.DiseaseHolder> {
    private List<Disease> diseases = new ArrayList<>();

    @NonNull
    @Override
    public DiseaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.disease_item, parent, false);
        return new DiseaseHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull DiseaseHolder holder, int position) {
        Disease currentDisease = diseases.get(position);
        holder.textViewName.setText(currentDisease.getName());
        holder.textViewLocation.setText(currentDisease.getLocation());
        holder.textViewInfo.setText(currentDisease.getInfo());

    }

    @Override
    public int getItemCount() {
        return diseases.size();
    }

    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
//         todo: notufy will be replaced
        notifyDataSetChanged();
    }

    class DiseaseHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewInfo;
        private TextView textViewLocation;

        public DiseaseHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.disease_name_txt);
            textViewInfo = itemView.findViewById(R.id.disease_info_txt);
            textViewLocation = itemView.findViewById(R.id.disease_location_txt);
        }
    }
}
