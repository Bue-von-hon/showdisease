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
import android.widget.Toast;

import com.kc.showdisease.databinding.ActivityLginBinding;
import com.kc.showdisease.databinding.ActivityMainBinding;

import static com.kc.showdisease.MainActivity.spmodel;

public class LginActivity extends AppCompatActivity {
    public static Context mContext;
    ActivityLginBinding bindinglgin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        bindinglgin = DataBindingUtil.setContentView(this, R.layout.activity_lgin);

        //      viewlmodel을 사용해서 sharedPreferences 구현
        if (savedInstanceState == null) {
//            String id = spmodel.getString(mContext, "id");
//            String pw = spmodel.getString(mContext, "pw");
        }

        bindinglgin.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = bindinglgin.idText.getText().toString();
                String pw = bindinglgin.pwText.getText().toString();
                if (id.length() == 0)
                    Toast.makeText(LginActivity.this, "아이디를 입력하세요", Toast.LENGTH_LONG).show();
                if (pw.length() == 0)
                    Toast.makeText(LginActivity.this, "비밀번호를 입력하세요", Toast.LENGTH_LONG).show();
                else {
                    spmodel.clear(mContext);
                    spmodel.setString(mContext, "id", id);
                    spmodel.setString(mContext, "pw", pw);
                    finish();
                }
            }
        });


        bindinglgin.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spmodel.setString(mContext, "id", "");
                spmodel.setString(mContext, "pw", "");
                finish();
            }
        });

    }
}
