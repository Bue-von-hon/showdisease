package com.kc.showdisease;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LginActivity extends AppCompatActivity {
    private EditText idText;
    private EditText pwText;
    private Button logoutBtn;
    private Button loginBtn;
    private Context mContext;

    private void init() {
        idText = (EditText) findViewById(R.id.idText);
        pwText = (EditText) findViewById(R.id.pwText);
        logoutBtn = (Button) findViewById(R.id.logoutBtn);
        loginBtn = (Button) findViewById(R.id.loginBtn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lgin);
        init();
        mContext = this;

        //      viewlmodel을 사용해서 sharedPreferences 구현
        final SharedPreferencesGenerator spmodel = ViewModelProviders.of(this).get(SharedPreferencesGenerator.class);
        if (savedInstanceState == null) {


            String id = spmodel.getString(mContext, "id");
            int pw = spmodel.getInt(mContext, "pw");


        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = idText.getText().toString();
                int pw = Integer.parseInt(pwText.getText().toString());

                spmodel.clear(mContext);

                spmodel.setString(mContext, "id", id);
                spmodel.setInt(mContext, "pw", pw);


            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spmodel.setString(mContext, "id", "");
                spmodel.setInt(mContext, "pw", -1);

            }
        });

    }



}
