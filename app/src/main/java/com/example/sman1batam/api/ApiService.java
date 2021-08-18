package com.example.sman1batam.api;

import com.example.sman1batam.model.ResponseModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @FormUrlEncoded
    @POST("login")
    Call<ResponseModel> login(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("register")
    Call<ResponseModel> register(@FieldMap Map<String, String> params);

    @GET("jadwal")
    Call<ResponseModel> getjadwal();

    @GET("jadwalujian")
    Call<ResponseModel> getjadwaluji();

    @GET("nilai/tugas")
    Call<ResponseModel> getNilai(
            @Query("user") String nilai
    );

    // @FormUrlEncoded
    //@POST("login")
    //Call<> login(
    // @Field("email") String email,
    // @Field("password") String password
    //);
}
