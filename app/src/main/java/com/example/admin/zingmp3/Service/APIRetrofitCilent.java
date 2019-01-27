package com.example.admin.zingmp3.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 26/9/2018.
 */

public class APIRetrofitCilent {
    private static Retrofit retrofit = null;
    public static Retrofit getClient(String base_url){
        //Kiem tra mang hoac giao thuc ben phia server
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //thoi gian ngat khi cho doc du lieu
                        .readTimeout(10000, TimeUnit.MILLISECONDS)
                        .writeTimeout(10000,TimeUnit.MILLISECONDS)
                .connectTimeout(10000,TimeUnit.MILLISECONDS)
                //khi co loi ve mang van co gang ket noi
                .retryOnConnectionFailure(true)
                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                .build();
        //convert tu khoa API snag tu khoa ben phia Java
         Gson gson = new GsonBuilder().setLenient().create();
       // addConverterFactory(GsonConverterFactory.create(gson))
        retrofit = new Retrofit.Builder()
                       .baseUrl(base_url)
                       .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

         return retrofit;
    }
}
