package com.example.mobasher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobasher.Utils.SharedPrefManager;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.edit_text_username);
        password = findViewById(R.id.edit_text_password);

        findViewById(R.id.new_acount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        findViewById(R.id.forget_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, PasswordResetActivity.class));
            }
        });
        findViewById(R.id.login_botton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString() != null || password.getText().toString() != null){
                    if (SharedPrefManager.getInstance(LoginActivity.this).isLoggedIn()){
                        Toast.makeText(LoginActivity.this,"User Already Exist",Toast.LENGTH_LONG).show();

                    }else {
                        SharedPrefManager.getInstance(LoginActivity.this).userLogin(username.getText().toString(),password.getText().toString());
                        startActivity(new Intent(LoginActivity.this,AppartmentsActivity.class));
                        finish();
                    }
                }else {
                    Toast.makeText(LoginActivity.this,"تأكد من ملئ جميع الحقول.",Toast.LENGTH_LONG).show();
                }

            }
        });
    }



}