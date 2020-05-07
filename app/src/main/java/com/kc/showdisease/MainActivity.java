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


public class MainActivity extends AppCompatActivity {
    SupportMapFragment mapFragment;
    GoogleMap map;
    static SharedPreferencesGenerator spmodel;
    static AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        spmodel = ViewModelProviders.of(this).get(SharedPreferencesGenerator.class);

        /* build database with Room */
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();

//        todo: 타이틀 정해야함 일단은 jaja라고
        binding.toolbar.setTitle("jaja");
        setSupportActionBar(binding.toolbar);

//        todo: 지도 마커 구현
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                LatLng sydney = new LatLng(-34, 151);
                map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
                map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
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
        if (spmodel.getInt(this, "pw") != -1) {
            menu.findItem(R.id.adddisease).setVisible(true);
        }
        MenuItem shareItem = menu.findItem(R.id.adddisease);


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.login_bar:
                Intent intent = new Intent(getApplicationContext(), LginActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
