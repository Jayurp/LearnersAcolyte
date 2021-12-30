package com.example.learnersacolyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                FinishSplash();
            }
        },2000);
    }

    public void FinishSplash()
    {
        Intent intent = new Intent(this, GoogleLogin.class);
        startActivity(intent);
        finish();
    }
}