package com.example.myret.Network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static ApiClient mInstance;
    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
//        https://abdallah92.000webhostapp.com/reg_login/api/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mohammedoss.000webhostapp.com/mohammed_ws/api/MsgTypes/")
//                .baseUrl("https://192.168.10.106/sss/api/post/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return  retrofit;
    }


    public static synchronized ApiClient getInstance() {
        if (mInstance == null) {
            mInstance = new ApiClient();
        }
        return mInstance;
    }


    public static Api getApiInterface(){
        Api apiInterface = getRetrofit().create(Api.class);
        return apiInterface;
    }
}
