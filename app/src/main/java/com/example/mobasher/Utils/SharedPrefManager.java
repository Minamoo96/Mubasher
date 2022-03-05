package com.example.mobasher.Utils;

import static java.lang.String.valueOf;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private static SharedPrefManager mInstance;
    private static Context mCtx;
    private static final String SHARED_PREF_NAME = "mysharedpref";


    private static final String KEY_FNAME = "FastName";
    private static final String KEY_LNAME = "LastName";
    private static final String KEY_USERMAIL = "Email";
    private static final String KEY_USERPHONE = "Phone";
    private static final String KEY_USERPASSWORD = "Password";
    private static final String KEY_USERNAME = "Username";

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void userSignup(String firstname, String lastname, String email,
                           String phone, String password){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_FNAME, firstname);
        editor.putString(KEY_LNAME,lastname);
        editor.putString(KEY_USERMAIL,email);
        editor.putString(KEY_USERPHONE, phone);
        editor.putString(KEY_USERPASSWORD,password);

        editor.apply();

    }

    public void userLogin(String userEmail, String userPassword){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_USERNAME, userEmail);
        editor.putString(KEY_USERPASSWORD, userPassword);

        editor.apply();

    }

    public String getKeyUserphone(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(KEY_USERPHONE,null) != null)
            return KEY_USERPHONE;

        else
        return null;
    }

    public void changepass(String pass){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERPASSWORD, pass);
        editor.apply();
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(KEY_USERMAIL, null) != null && sharedPreferences.getString(KEY_USERPASSWORD,null) != null){
            return true;
        }
        return false;
    }

    public void logOut(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
