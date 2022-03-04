package com.example.mobasher;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import com.example.mobasher.Utils.SharedPrefManager;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends Activity {

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int SELECT_PICTURE = 200;

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
            imageAdder = findViewById(R.id.imgadder);
            imageAdder.setBorderWidth(3);
            imageAdder.setBorderColor(Color.parseColor("#4CAF50"));
            imageAdder.setPadding(15, 15, 15, 15);
        }

        imageAdder.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                openDialog(v);
            }
        });

        findViewById(R.id.register_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!fname.getText().toString().equals(null) || !laname.getText().toString().equals(null)
                || email.getText().toString() != null || phone.getText().toString() != null
                || lpassword.getText().toString() != null ) {
                    if (fpassword.getText().toString().equals(lpassword.getText().toString())) {
                        if (SharedPrefManager.getInstance(RegisterActivity.this).isLoggedIn())
                        {Toast.makeText(RegisterActivity.this,
                                "User Already Exists",Toast.LENGTH_LONG).show();}
                        else {
                            SharedPrefManager.getInstance(RegisterActivity.this).userSignup(fname.getText().toString(),
                                    laname.getText().toString(),email.getText().toString(),phone.getInputType(),
                                    fpassword.getText().toString());
                            Toast.makeText(RegisterActivity.this,
                                    "Thank You For Registering With Us: " +fname +" "+ laname
                                    ,Toast.LENGTH_LONG).show();
                            startActivity(new Intent(RegisterActivity.this, AppartmentsActivity.class));
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this,
                                "تأكد من مطابقة كلمات المرور ثم حاول مرة أخرى.",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this,
                            "تأكد من ملئ كل الحقول ثم المحاولة مرة أخرى.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
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
        if (resultCode == RESULT_OK) {

            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    imageAdder.setImageURI(selectedImageUri);
                }
            }
        }
    }

    public void openDialog(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Please Select An Image Action");
        alertDialogBuilder.setPositiveButton("Camera", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        alertDialogBuilder.setNegativeButton("Pick Image",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}

