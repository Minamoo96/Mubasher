package com.example.mobasher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobasher.Backend.LoginInterface;
import com.example.mobasher.Utils.SharedPrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.edit_text_username);
//        password = findViewById(R.id.edit_text_password);

        findViewById(R.id.new_acount).setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

        findViewById(R.id.forget_password).setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, PasswordResetActivity.class)));

        findViewById(R.id.login_botton).setOnClickListener(view -> {
            if (username.getText().toString().isEmpty()){
                Toast.makeText(LoginActivity.this,"تأكد من إدخال رقم هاتفك الصحيح.",Toast.LENGTH_LONG).show();
            }else {
                if (SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn()) {
                    Toast.makeText(LoginActivity.this, "مرحبا بك مجدداَ", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this, AppartmentsActivity.class));
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, "عذراَ: يبدو أنك تحاول تحاول تسجيل دخول برقم حساب مختلف. تأكد من رقم حسابك ثم المحاولة مرة أخرى.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}