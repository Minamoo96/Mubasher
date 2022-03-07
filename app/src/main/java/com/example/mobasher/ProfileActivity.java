package com.example.mobasher;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.TextView;

import com.example.mobasher.Backend.User;
import com.example.mobasher.Utils.SharedPrefManager;

import java.nio.charset.StandardCharsets;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    TextView fullName, Email, Phone;
    CircleImageView profile;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn()){
            fullName = findViewById(R.id.userFullName);
            Email = findViewById(R.id.userEmail);
            Phone = findViewById(R.id.userPhone);
            profile = findViewById(R.id.profileImage);
            User user = SharedPrefManager.getInstance(this).getUser();

            fullName.setText(user.getFirstName()+ " " +user.getLastName());
            Email.setText(user.getEmail());
            Phone.setText(user.getPhone());
            profile.setImageBitmap(decodeImage(user.getImage()));
        }
    }
    public Bitmap decodeImage(String base64){
        byte[] bytes = Base64.decode(base64.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);
        BitmapFactory.Options options = new BitmapFactory.Options();
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
    }
}