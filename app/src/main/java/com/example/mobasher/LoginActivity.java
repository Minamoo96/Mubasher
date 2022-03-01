package com.example.mobasher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                checkIfNull(username.getText().toString(),password.getText().toString());
                startActivity(new Intent(LoginActivity.this, AppartmentsActivity.class));
            }
        });
    }

    public void checkIfNull(String username, String password){

        if (username == null || password == null ){
            Toast.makeText(this,"يجب ملئ الحقول بصورة صحيحة",Toast.LENGTH_SHORT).show();
        }

    }

}