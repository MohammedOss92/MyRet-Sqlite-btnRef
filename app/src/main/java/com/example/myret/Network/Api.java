package com.example.myret.Network;


import com.example.myret.Response.MsgTypesResponse;
import com.example.myret.Response.MsgsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("select.php")
    Call<MsgTypesResponse> getAllMsgTypes();

    @GET("select.php/{TypeDescription}")
    Call<MsgsResponse> getAllMsgs(
            @Query("TypeDescription") int TypeDescription
    );
}
