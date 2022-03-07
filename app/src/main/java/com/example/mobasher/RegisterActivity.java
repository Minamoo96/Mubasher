package com.example.mobasher;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.mobasher.Backend.User;
import com.example.mobasher.Backend.URLS;
import com.example.mobasher.Backend.VolleySingleton;
import com.example.mobasher.Utils.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

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

        imageAdder.setOnClickListener(this::openDialog);

        findViewById(R.id.register_button).setOnClickListener(v -> signUpUser());
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
        final View customLayout = getLayoutInflater().inflate(R.layout.custom_alert_dialog, null);
        alertDialogBuilder.setView(customLayout);
        alertDialogBuilder.setPositiveButton("Camera", (arg0, arg1) -> {

            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
        });

        alertDialogBuilder.setNegativeButton("Pick Image", (dialog, which) -> {

            Intent i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private String decodeImage(){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.id.imgadder);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();

        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    private void signUpUser() {

        final String firstname = fname.getText().toString().trim();
        final String lastname = laname.getText().toString().trim();
        final String uMail = email.getText().toString().trim();
        final String uPhone = phone.getText().toString().trim();
        final String ufpassword = fpassword.getText().toString().trim();
        final String ulpassword = lpassword.getText().toString().trim();
        final String uimage = decodeImage();


        if (TextUtils.isEmpty(firstname)){
            fname.setError("أدخل إسمك الأول");
            fname.requestFocus();
        }
        if (TextUtils.isEmpty(lastname)){
            laname.setError("أدخل إسمك الثاني");
            laname.requestFocus();
        }
        if (TextUtils.isEmpty(uMail)){
            email.setError("أدخل البريد الاكتروني");
            email.requestFocus();
        }
        if (TextUtils.isEmpty(uPhone)){
            phone.setError("أدخل رقم هاتفك");
            phone.requestFocus();
        }
        if (TextUtils.isEmpty(ufpassword)){
            fpassword.setError("أدخل كلمة المرور");
            fpassword.requestFocus();
        }
        if (TextUtils.isEmpty(ulpassword)){
            lpassword.setError("أكد إدخال كلمة المرور");
            lpassword.requestFocus();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLS.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);
                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                //getting the user from the response
                                JSONObject userJson = obj.getJSONObject("user");

                                //creating a new user object
                                User user = new User(
                                        userJson.getInt("id"),
                                        userJson.getString("firstname"),
                                        userJson.getString("lastname"),
                                        userJson.getString("email"),
                                        userJson.getString("phone"),
                                        userJson.getString("password"),
                                        userJson.getString("image")
                                );

                                //storing the user in shared preferences
                                SharedPrefManager.getInstance(getApplicationContext()).userSignup(user);

                                //starting the profile activity
                                finish();
                                startActivity(new Intent(getApplicationContext(), AppartmentsActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("firstname", firstname);
                params.put("lastname", lastname);
                params.put("email", uMail);
                params.put("phone", uPhone);
                params.put("password", ufpassword);
                params.put("image", uimage);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }
}

