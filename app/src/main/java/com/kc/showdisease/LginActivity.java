package com.kc.showdisease;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

public class LginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        login function will be added
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lgin);

        SharedPreferences pref = getSharedPreferences("login", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

    }
}
