package com.kc.showdisease;

import androidx.appcompat.app.AppCompatActivity;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;


import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;

import android.view.MenuItem;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kc.showdisease.databinding.ActivityMainBinding;

import java.util.Iterator;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    static SupportMapFragment mapFragment;
    static GoogleMap map;
    static SharedPreferencesGenerator spmodel;
    static AppDatabase db;
    Intent intent;
    String Dname;
    double latitude = -34;
    double longitude = 151;
    Disease target = null;
    static Iterator<Disease> diseaseIterator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        spmodel = ViewModelProviders.of(this).get(SharedPreferencesGenerator.class);

        /* build database with Room */
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "disease").allowMainThreadQueries().build();

//        todo: 타이틀 정해야함 일단은 jaja라고
        binding.toolbar.setTitle("jaja");
        setSupportActionBar(binding.toolbar);
        List<Disease> targets = db.diseaseDao().getAll();
        diseaseIterator = targets.iterator();


//        todo: 지도 마커 구현
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                while (diseaseIterator.hasNext()) {
                    target = diseaseIterator.next();
                    String Dname = target.getName();
                    latitude = target.getLatitude();
                    longitude = target.getLongitude();
                    LatLng sydney = new LatLng(latitude, longitude);
                    map.addMarker(new MarkerOptions().position(sydney).title(Dname));
                    map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                }

            }
        });

        try {
            MapsInitializer.initialize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        invalidateOptionsMenu();
        super.onResume();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        if (spmodel.getString(this, "id") != "") {
            menu.findItem(R.id.adddisease).setVisible(true);
        }
        MenuItem shareItem = menu.findItem(R.id.adddisease);


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.login_bar:
                intent = new Intent(getApplicationContext(), LginActivity.class);
                startActivity(intent);
                return true;
            case R.id.adddisease:
                intent = new Intent(getApplicationContext(), AddEditDiseaseInfo.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
