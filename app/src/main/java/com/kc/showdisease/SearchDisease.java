package com.kc.showdisease;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;


public class SearchDisease extends AppCompatActivity {
    private DiseaseViewModel diseaseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_disease);

        final RDiseaseAdapter adapter = new RDiseaseAdapter();


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


        diseaseViewModel = ViewModelProviders.of(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(DiseaseViewModel.class);
        diseaseViewModel.getAllDisease().observe(this, new Observer<List<Disease>>() {
            @Override
            public void onChanged(List<Disease> diseases) {
                adapter.setDiseases(diseases);
            }
        });


    }
}