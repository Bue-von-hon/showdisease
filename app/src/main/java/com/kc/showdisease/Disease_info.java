package com.kc.showdisease;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Disease_info extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_info);

        Intent intent = getIntent();

        if (intent.hasExtra("name")) {
            String Dname = intent.getStringExtra("name");
            String Dlocation = intent.getStringExtra("location");
            String infoname = intent.getStringExtra("info");

            TextView Dtext = (TextView) findViewById(R.id.Dtext);
            TextView Dlocationtext = (TextView) findViewById(R.id.Ltext);
            TextView Ntext = (TextView) findViewById(R.id.Ntext);

            Dlocationtext.setText(Dlocation);
            Dtext.setText(Dname);
            Ntext.setText(infoname);
        }
        setResult(RESULT_OK);
    }
}