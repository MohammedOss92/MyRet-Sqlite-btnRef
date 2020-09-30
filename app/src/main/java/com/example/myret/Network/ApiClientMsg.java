package com.example.myret.Network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientMsg {
    private static ApiClientMsg mInstance;
    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mohammedoss.000webhostapp.com/mohammed_ws/api/Msg/")
//                .baseUrl("https://192.168.10.106/sss/api/post/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return  retrofit;
    }


    public static synchronized ApiClientMsg getInstance() {
        if (mInstance == null) {
            mInstance = new ApiClientMsg();
        }
        return mInstance;
    }


    public static Api getApiInterface(){
        Api apiInterface = getRetrofit().create(Api.class);
        return apiInterface;
    }
}
