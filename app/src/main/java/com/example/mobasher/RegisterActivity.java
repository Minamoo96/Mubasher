package com.example.mobasher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText fname, laname, phone, email, fpassword, lpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        {
            fname = findViewById(R.id.text_field_first_name);
            laname = findViewById(R.id.text_field_last_name);
            phone = findViewById(R.id.text_field_phone);
            email = findViewById(R.id.text_field_email);
            fpassword = findViewById(R.id.text_field_password);
            lpassword = findViewById(R.id.text_field_repass);
        }

        findViewById(R.id.register_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fname.getText().toString().isEmpty() || fname.getText().toString().isEmpty() ||
                phone.getText().toString().isEmpty() || email.getText().toString().isEmpty() ||
                fpassword.getText().toString().isEmpty() || lpassword.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "تأكد من ملئ كل الحقول.",Toast.LENGTH_LONG).show();
                }
                else {
                    if (fpassword.getText().toString().equals(lpassword.getText().toString())){
                        Toast.makeText(RegisterActivity.this,"شكراً لتسجيلك معنا :" + fname.getText().toString()
                                + " "+laname.getText().toString(),Toast.LENGTH_LONG).show();
                        startActivity(new Intent(RegisterActivity.this, OtpActivity.class));
                    }else {
                        Toast.makeText(RegisterActivity.this,"تأكد من كلمات المرور يجب يتشابها",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}