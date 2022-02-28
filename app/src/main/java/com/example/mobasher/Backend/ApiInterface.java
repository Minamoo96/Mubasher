package com.example.mobasher.Backend;

import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded // annotation used in POST type requests
    @POST("/retrofit/register.php")     // API's endpoints
    void registration(@Field("name") String name,
                      @Field("email") String email,
                      @Field("password") String password,
                      @Field("logintype") String logintype,
                      Callback<SignUpUser> callback);
}
