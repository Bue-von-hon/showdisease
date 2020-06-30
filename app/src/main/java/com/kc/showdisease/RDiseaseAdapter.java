package com.kc.showdisease;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RDiseaseAdapter extends RecyclerView.Adapter<RDiseaseAdapter.DiseaseHolder> implements Filterable {
    private List<Disease> diseases = new ArrayList<>();
    private List<Disease> AllDiseases = new ArrayList<>();
    private OnItemClickListener listener;

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
        AllDiseases = new ArrayList<>(diseases);
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(diseases.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Disease disease);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public Filter getFilter() {
        return diseasesFilter;
    }

    private Filter diseasesFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Disease> filtereddiseases = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filtereddiseases.addAll(AllDiseases);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Disease d : AllDiseases) {
                    if (d.getName().toLowerCase().contains(filterPattern)) {
                        filtereddiseases.add(d);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filtereddiseases;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            diseases.clear();
            diseases.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
