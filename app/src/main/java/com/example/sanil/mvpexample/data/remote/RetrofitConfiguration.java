package com.example.sanil.mvpexample.data.remote;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by sanil on 08-05-2018.
 */

public class RetrofitConfiguration {


    public static Retrofit retroClient = null;

    public static OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();

    public static Retrofit getClient()
    {
        if(retroClient==null)
        {
            retroClient =  new Retrofit.Builder()
                    .baseUrl("http://192.168.0.122:8080/DemoWebProject/")
                    .client(okHttpClient)
//                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retroClient;
    }
}
