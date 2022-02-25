package com.example.mobasher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class OtpActivity extends AppCompatActivity {
    EditText code1, code2, code3, code4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        code1 = findViewById(R.id.input_code1);
        code2 = findViewById(R.id.input_code2);
        code3 = findViewById(R.id.input_code3);
        code4 = findViewById(R.id.input_code4);

        code1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (code1.getText().length()==1) {
                    code2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        code2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (code2.getText().length() == 1) {
                    code3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
        code3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (code3.getText().length()==1) {
                    code4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        findViewById(R.id.resend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OtpActivity.this,"تم ارسال الرمز مرة اخرى" , Toast.LENGTH_LONG).show();
            }
        });
    }
}