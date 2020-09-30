package com.example.myret;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myret.Adapter.MsgTypesAdapter;
import com.example.myret.Modal.MsgTypes;
import com.example.myret.Response.MsgTypesResponse;
import com.example.myret.Modal.Msgs;
import com.example.myret.Response.MsgsResponse;
import com.example.myret.Sqlite.Sqlite;
import com.example.myret.Network.ApiClient;
import com.example.myret.Network.ApiClientMsg;
import com.example.myret.Network.CheckInternet;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MsgTypesAdapter msgTypesAdapter;
    private List<MsgTypes> msgTypeList= new ArrayList<>();
    private List<Msgs>msgsList = new ArrayList<>();
    Sqlite sqlite;
    int counter;
    int TypeDescription;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqlite = new Sqlite(this);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        msgTypesAdapter = new MsgTypesAdapter(MainActivity.this, msgTypeList);
        getAllMsgTypes();
        //getAllMsgs(TypeDescription);
        fillData();

        if (CheckInternet.isNetwork(MainActivity.this)) {
            //internet is connected do something

        } else {
            //do something, net is not connected
            Toast.makeText(this, "Noooooooooooooooooooo", Toast.LENGTH_SHORT).show();
        }




    }

    private void getAllMsgTypes() {
        Call<MsgTypesResponse>call = ApiClient.getInstance().getApiInterface().getAllMsgTypes();

        call.enqueue(new Callback<MsgTypesResponse>() {
            @Override
            public void onResponse(Call<MsgTypesResponse> call, Response<MsgTypesResponse> response) {

                msgTypeList = response.body().getMsgTypes();

                msgTypesAdapter = new MsgTypesAdapter(MainActivity.this, msgTypeList);
                recyclerView.setAdapter(msgTypesAdapter);
                msgTypesAdapter.notifyDataSetChanged();
                try {
                    sqlite.ClearMsgs();
                    sqlite.clearTables();
                    for (int i = 0; i < msgTypeList.size(); i++) {
                        sqlite.saveMsgTypes(msgTypeList.get(i));

                        // TODO: 27/09/2020 save second page data in sqlite
                        getAllMsgs( msgTypeList.get(i).getTypeID());

                    }
                }
                catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<MsgTypesResponse> call, Throwable t) {

            }
        });


    }

    public void getAllMsgs(int typeid) {
        Call<MsgsResponse> call = ApiClientMsg.getInstance().getApiInterface().getAllMsgs(typeid);

        call.enqueue(new Callback<MsgsResponse>() {
            @Override
            public void onResponse(Call<MsgsResponse> call, Response<MsgsResponse> response) {

                msgsList = response.body().getMsgs();
                // TODO: 27/09/2020 save in sqlite

                //sqlite.ClearMsgs();
                for(int i=0 ;i<msgsList.size();i++){
                    sqlite.saveMessages(msgsList.get(i));

//                MsgsResponse msgsResponse = response.body();
//                List<Msgs> msgs = msgsResponse.getMsgs();
//                Log.d(TAG, "onResponse: SIZE IS: " + msgs.size());
//                for (Msgs msgs1 : msgs) {
//                    Msgs msgs2 = new Msgs(msgs1.getMsgDescription(), msgs1.getTypeDescription(), msgs1.getNewMsg());
//                    msgs2.setMsgID(msgs1.getMsgID());
//                    sqlite.saveMessages(msgs2);
//                }



            }

            }

            @Override
            public void onFailure(Call<MsgsResponse> call, Throwable t) {

            }
        });
    }



    //This Code From Sqlite
    public void fillData()
    {
        Sqlite s=new Sqlite(this);
        List<MsgTypes> myArrayList=  s.getMsgTypes();
        MsgTypesAdapter a=new MsgTypesAdapter(this,myArrayList);
        recyclerView.setAdapter(a);
    }





}