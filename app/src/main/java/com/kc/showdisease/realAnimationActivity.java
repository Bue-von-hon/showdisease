package com.kc.showdisease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class realAnimationActivity extends AppCompatActivity {

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        ImageView iv = (ImageView) findViewById(R.id.imageView);
//        iv.setBackgroundResource(R.drawable.sp);
//        AnimationDrawable animationDrawable = (AnimationDrawable) iv.getBackground();
        AnimationDrawable animationDrawable = (AnimationDrawable) iv.getDrawable();
        animationDrawable.setCallback(iv);
        animationDrawable.setVisible(true, true);
        animationDrawable.start();


        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_animation);


    }
}