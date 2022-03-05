package com.example.mobasher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobasher.Utils.SharedPrefManager;

public class PasswordResetActivity extends AppCompatActivity {
    EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        search = findViewById(R.id.search_account);

        findViewById(R.id.buttonSearch).setOnClickListener(v -> {
            if (search.getText().toString().isEmpty()) {
                Toast.makeText(PasswordResetActivity.this,
                        "الرجاء قم بإدخال رقم هاتفك الصحيح ثم المتابعة",
                        Toast.LENGTH_LONG).show();
            } else {
                if (SharedPrefManager.getInstance(getApplicationContext()).getKeyUserphone().equals(search.getText().toString()))
                    startActivity(new Intent(PasswordResetActivity.this, NewPasswordActivity.class));
            }
        });

    }
}