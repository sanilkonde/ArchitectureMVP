package com.example.sanil.mvpexample.data.remote;

import com.example.sanil.mvpexample.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by sanil on 08-05-2018.
 */


public interface IMvpApiService {

    @FormUrlEncoded
    @POST("login")
    Call<ApiResponse> authenticateService(@Field("data") String data);

    @GET("GetList")
    Call<ApiResponse> getListService();
}
