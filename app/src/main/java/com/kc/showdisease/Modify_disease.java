package com.kc.showdisease;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Query;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.internal.IProjectionDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import javax.xml.namespace.QName;

import static com.kc.showdisease.MainActivity.db;
import static com.kc.showdisease.MainActivity.map;
import static com.kc.showdisease.MainActivity.mapFragment;

public class Modify_disease extends AppCompatActivity {
    private EditText forModDiseaseName;
    private Button SearchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_disease);

    }

    public void Search(View v) {
        final String name = forModDiseaseName.getText().toString();
        String Dname = db.diseaseDao().findByName(name).getName().toString();
        if(name == Dname){
            Intent intent = new Intent(this, Modify_disease1.class);
            intent.putExtra("Dname",Dname);
            startActivityForResult(intent, 0);

        }
        else
            Toast.makeText(this, "이름이 잘못되었습니다.", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 0 && resultCode == RESULT_OK)
            Toast.makeText(this, "수정이 완료되었습니다.", Toast.LENGTH_LONG);
        super.onActivityResult(requestCode, resultCode, data);
    }


}