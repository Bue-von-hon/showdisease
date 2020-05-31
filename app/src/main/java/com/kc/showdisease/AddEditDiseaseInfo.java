package com.kc.showdisease;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kc.showdisease.databinding.ActivityAddEditDiseaseInfoBinding;

import java.util.List;

import static com.kc.showdisease.MainActivity.db;
import static com.kc.showdisease.MainActivity.diseaseIterator;

import static com.kc.showdisease.MainActivity.map;
import static com.kc.showdisease.MainActivity.mapFragment;

public class AddEditDiseaseInfo extends AppCompatActivity {
    private DiseaseViewModel diseaseViewModel;
    Disease target = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityAddEditDiseaseInfoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_add_edit_disease_info);
        diseaseViewModel = ViewModelProviders.of(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(DiseaseViewModel.class);
        binding.AddBtn.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

                final String name = binding.AddDiseaseNameTxt.getText().toString();
                final double latitude = Double.parseDouble(binding.AddLatitudeTxt.getText().toString());
                final double longitude = Double.parseDouble(binding.AddLongitudeTxt.getText().toString());
                final String info = binding.AddinfoTxt.getText().toString();
                final String location = binding.AddLocationTxt.getText().toString();
                db.diseaseDao().insert(new Disease(name, latitude, longitude, info, location));
                mapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        map = googleMap;
                        LatLng sydney = new LatLng(latitude, longitude);
                        map.addMarker(new MarkerOptions().position(sydney).title(name));
                        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));

                    }
                });

                finish();
            }
        });

    }
}
