package com.example.mobasher.Backend;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginInterface {

    String LOGINURL = "192.168.100.103";
    @FormUrlEncoded
    @POST("loginuser.php")     // API's endpoints
    Call<String> getUserLogin(
            @Field("username") String username,
            @Field("password") String password
    );
}
