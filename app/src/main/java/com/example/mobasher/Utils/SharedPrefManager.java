package com.example.mobasher.Utils;

import static java.lang.String.valueOf;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.mobasher.Backend.User;
import com.example.mobasher.LoginActivity;

public class SharedPrefManager {

    private static SharedPrefManager mInstance;
    private static Context mCtx;
    private static final String SHARED_PREF_NAME = "mysharedpref";

    private static final String KEY_ID = "UID";
    private static final String KEY_FNAME = "FastName";
    private static final String KEY_LNAME = "LastName";
    private static final String KEY_USERMAIL = "Email";
    private static final String KEY_USERPHONE = "Phone";
    private static final String KEY_USERPASSWORD = "Password";
    private static final String KEY_USERIMAGE = "UserImage";

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void userSignup(User user){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, user.getId());
        editor.putString(KEY_FNAME, user.getFirstName());
        editor.putString(KEY_LNAME,user.getLastName());
        editor.putString(KEY_USERMAIL, user.getEmail());
        editor.putString(KEY_USERPHONE, user.getPhone());
        editor.putString(KEY_USERPASSWORD, user.getPassword());
        editor.putString(KEY_USERIMAGE, user.getImage());
        editor.apply();

    }

    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_FNAME, null),
                sharedPreferences.getString(KEY_LNAME, null),
                sharedPreferences.getString(KEY_USERMAIL, null),
                sharedPreferences.getString(KEY_USERPHONE, null),
                sharedPreferences.getString(KEY_USERPASSWORD, null),
                sharedPreferences.getString(KEY_USERIMAGE, null)
        );
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(KEY_USERPHONE, null) != null){
            return true;
        }
        return false;
    }

    public void logOut(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
    }

}
