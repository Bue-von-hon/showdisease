package com.kc.showdisease;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kc.showdisease.databinding.ActivityLginBinding;
import com.kc.showdisease.databinding.ActivityMainBinding;

import static com.kc.showdisease.MainActivity.spmodel;

public class LginActivity extends AppCompatActivity {
    private Context mContext;
    ActivityLginBinding bindinglgin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        bindinglgin = DataBindingUtil.setContentView(this, R.layout.activity_lgin);

        //      viewlmodel을 사용해서 sharedPreferences 구현
        if (savedInstanceState == null) {
            String id = spmodel.getString(mContext, "id");
            int pw = spmodel.getInt(mContext, "pw");
        }

        bindinglgin.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = bindinglgin.idText.getText().toString();
                int pw = Integer.parseInt(bindinglgin.pwText.getText().toString());
                spmodel.clear(mContext);
                spmodel.setString(mContext, "id", id);
                spmodel.setInt(mContext, "pw", pw);
                finish();

            }
        });

        bindinglgin.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spmodel.setString(mContext, "id", "");
                spmodel.setInt(mContext, "pw", -1);
                finish();
            }
        });

    }


}
