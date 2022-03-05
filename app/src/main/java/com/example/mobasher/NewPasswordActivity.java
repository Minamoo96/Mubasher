package com.example.mobasher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobasher.Utils.SharedPrefManager;

public class NewPasswordActivity extends AppCompatActivity {

    EditText repass1, repass2;
    Button confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        {
            repass1 = findViewById(R.id.new_repass);
            repass2 = findViewById(R.id.new_repass2);

            confirmPassword = findViewById(R.id.confirm_password);
        }
        confirmPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repass1.getText().toString().isEmpty() || repass2.getText().toString().isEmpty()) {
                    Toast.makeText(NewPasswordActivity.this,"تاكد من ملئ كل الحقول.",Toast.LENGTH_LONG).show();
                } else {
                    if (repass1.getText().toString().equals(repass2.getText().toString())) {
                      if (SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn()) {
                          SharedPrefManager.getInstance(getApplicationContext()).changepass(repass1.getText().toString());

                          Toast.makeText(NewPasswordActivity.this, "تم تأكيد كلمات المرور", Toast.LENGTH_LONG).show();
                          startActivity(new Intent(NewPasswordActivity.this, LoginActivity.class));
                      }
                    } else {
                        Toast.makeText(NewPasswordActivity.this, "كلمات المرور ليست متطابقة. تأكد من أن كلمات المرور يجب ان تتطابق", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}