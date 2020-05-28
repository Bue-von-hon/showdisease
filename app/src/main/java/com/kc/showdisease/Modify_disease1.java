package com.kc.showdisease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.http.SslCertificate;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.kc.showdisease.MainActivity.db;
import static com.kc.showdisease.MainActivity.map;
import static com.kc.showdisease.MainActivity.mapFragment;

public class Modify_disease1 extends AppCompatActivity {
    Disease target = null;
    private TextView ModDiseaseName;
    private TextView ModDiseaseNameTxt;
    private EditText ModName;
    private EditText ModLocation;
    private EditText ModInfo;
    private EditText ModLat;
    private EditText ModLong;
    private Button ModBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_disease1);

        ModBtn = (Button) findViewById(R.id.ModBtn);

        Intent intent = getIntent();
        final String Dname = intent.getStringExtra("Dname");

        ModBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = ModDiseaseNameTxt.getText().toString();
                final double latitude = Double.parseDouble(ModLat.getText().toString());
                final double longitude = Double.parseDouble(ModLong.getText().toString());
                final String info = ModInfo.getText().toString();
                final String location = ModLocation.getText().toString();
                db.diseaseDao().findByName(Dname);

                db.diseaseDao().update(new Disease(name, latitude, longitude, info, location));
                mapFragment.getMapAsync(new OnMapReadyCallback() {
                    public void onMapReady(GoogleMap googleMap) {
                        map = googleMap;
                        LatLng sydney = new LatLng(latitude, longitude);
                        map.addMarker(new MarkerOptions().position(sydney).title(name));
                        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                    }
                });

                finish();

                Intent data = new Intent();
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
