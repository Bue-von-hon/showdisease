package com.kc.showdisease;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;


public class SearchDisease extends AppCompatActivity {
    private DiseaseViewModel diseaseViewModel;
    public static final int INFO_REQUEST = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == INFO_REQUEST && resultCode == RESULT_OK) {
            ;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

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
        Intent intent = getIntent();
        if (intent.getStringExtra("query") != null) {
            diseaseViewModel.getAllDisease().observe(this, new Observer<List<Disease>>() {
                @Override
                public void onChanged(List<Disease> diseases) {
                    adapter.setDiseases(diseases);
                }
            });
            final String query = intent.getStringExtra("query");
            diseaseViewModel.getAllDisease().observe(this, new Observer<List<Disease>>() {
                @Override
                public void onChanged(List<Disease> diseases) {
                    adapter.getFilter().filter(query);
                }
            });

        } else {
            diseaseViewModel.getAllDisease().observe(this, new Observer<List<Disease>>() {
                @Override
                public void onChanged(List<Disease> diseases) {
                    adapter.setDiseases(diseases);
                }
            });
        }

        adapter.setOnItemClickListener(new RDiseaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Disease disease) {

//                todo: 콜링된 액티비티 죽이고 새로 액티비티 콜하기
                Intent intent = new Intent(getApplicationContext(), Disease_info.class);
                intent.putExtra("name", disease.getName().toString());
                intent.putExtra("location", disease.getLocation().toString());
                intent.putExtra("info", disease.getInfo().toString());
                startActivityForResult(intent, INFO_REQUEST);
            }
        });


    }

}