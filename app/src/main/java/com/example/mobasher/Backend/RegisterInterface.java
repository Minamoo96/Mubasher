package com.example.mobasher.Backend;

import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegisterInterface {

    @FormUrlEncoded // annotation used in POST type requests
    @POST("/retrofit/register.php")     // API's endpoints
    void registration(@Field("fname") String fname,
                      @Field("lname") String lname,
                      @Field("email") String email,
                      @Field("phone") String phone,
                      @Field("password") String password,
                      Callback<SignUpUser> callback);
}
