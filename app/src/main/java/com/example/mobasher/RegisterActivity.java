package com.example.mobasher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.net.URI;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends Activity {

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    EditText fname, laname, phone, email, fpassword, lpassword;
    CircleImageView imageAdder;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
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
        imageAdder = findViewById(R.id.imgadder);
        imageAdder.setBorderWidth(5);
        imageAdder.setBorderColor(Color.parseColor("#007505"));
        imageAdder.setPadding(15,15,15,15);
        imageAdder.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_DENIED){
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                }else {

                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });

        findViewById(R.id.register_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkIfNull(fname.getText().toString(),laname.getText().toString(),
                        phone.getText().toString(),email.getText().toString(),fpassword.toString(),
                        lpassword.getText().toString())){
                    if (checkPasswords(fpassword.getText().toString(),lpassword.getText().toString())){
                        startActivity(new Intent(RegisterActivity.this, OtpActivity.class));
                    }
                }
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageAdder.setImageBitmap(photo);
        }
    }

    public boolean checkIfNull(String fname, String lname, String email, String phone, String fpass, String lpass){

        if (fname == null || lname == null || email == null || phone == null || fpass == null || lpass == null){
            Toast.makeText(this,"يجب ملئ جميع الحقول بصورة صحيحة", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public boolean checkPasswords(String Password1, String Password2){
        if (!Password1.equals(Password2)){
            Toast.makeText(this,"تأكد من تطابق كلمات المرور ثم حاول مرة اخرى", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}