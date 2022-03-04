package com.example.mobasher;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.mobasher.Utils.SharedPrefManager;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    Handler handle = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPrefManager.getInstance(this);

        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SharedPrefManager.getInstance(SplashActivity.this).isLoggedIn()) {
                    Toast.makeText(SplashActivity.this,
                            "مرحبا بك مرة أخرى" ,
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SplashActivity.this, AppartmentsActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    finish();
                }
            }
        },3000);
    }
}