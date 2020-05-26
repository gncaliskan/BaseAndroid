package com.oyak.baseapp.rest;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkEngine {

    private static APIInterface apiInterface;
    private static String baseUrl = "https://abc.xyz/Services/";
    public static Retrofit retrofit;
    private static final long TIMEOUT = 50000;

    public static APIInterface getTracerApi() {
        if (apiInterface == null) {
            initApi();
        }
        return apiInterface;
    }

    private static void initApi() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient
                .Builder()
                .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor).build();

        retrofit = new Retrofit.Builder().baseUrl(baseUrl).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(APIInterface.class);
    }
}
