package com.example.myret;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myret.Adapter.MsgsAdap;
import com.example.myret.Modal.Msgs;
import com.example.myret.Response.MsgsResponse;
import com.example.myret.Sqlite.Sqlite;
import com.example.myret.Network.ApiClientMsg;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MsgsActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private static final String TAG = "MsgsActivity";
    RecyclerView recyclerView;
    private List<Msgs>msgsList = new ArrayList<>();
    Sqlite sqlite;
    MsgsAdap msgsAdap;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    int TypeDescription;
    int titleID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msgs);
        sqlite = new Sqlite(this);

        Intent i = getIntent();
        if (i.getExtras()!=null) {
            TypeDescription = i.getExtras().getInt("TypeDescription");
        }


//        msgsViewModal = ViewModelProviders.of(MsgsActivity.this).get(MsgsViewModal.class);
        recyclerView = findViewById(R.id.recyclerviewMsgs);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);


        recyclerView.setItemAnimator(new DefaultItemAnimator());

//       msgsAdapter = new MsgsAdapter(MsgsActivity.this);
       // msgsAdap = new MsgsAdap(MsgsActivity.this,msgsList);
        //  recyclerView.setAdapter(msgsAdap);
//        recyclerView.setAdapter(msgsAdapter);

        Log.d(TAG, "onCreate: TYPE DESC: " + TypeDescription);





        //getAllMsgs();
        fillData();
    }

    public boolean onCreateOptionMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_msg,menu);
        MenuItem search = menu.findItem(R.id.ic_action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void getAllMsgs(int TypeDescription,final boolean goToMessages) {
        Call<MsgsResponse> call = ApiClientMsg.getInstance().getApiInterface().getAllMsgs(TypeDescription);

        call.enqueue(new Callback<MsgsResponse>() {
            @Override
            public void onResponse(Call<MsgsResponse> call, Response<MsgsResponse> response) {

                msgsList = response.body().getMsgs();
//                msgsAdap = new MsgsAdap(MsgsActivity.this, msgsList);
//                recyclerView.setAdapter(msgsAdap);
//                msgsAdap.notifyDataSetChanged();


                try {
//                    sqlite.ClearMsgs();
//                    sqlite.updateFavOnRefersh();
                    for(int i=0 ;i<msgsList.size();i++){
                sqlite.saveMessages(msgsList.get(i));
            }
        }catch (Exception e){

        }
                Sqlite s = new Sqlite(MsgsActivity.this);
//                s.updateFavOnRefersh();
                if (goToMessages) {
                    fillData();
                }

//                MsgsResponse msgsResponse = response.body();
//                List<Msgs> msgs = msgsResponse.getMsgs();
//                Log.d(TAG, "onResponse: SIZE IS: " + msgs.size());
//                for (Msgs msgs1 : msgs) {
//                    Msgs msgs2 = new Msgs(msgs1.getMsgDescription(), msgs1.getTypeDescription(), msgs1.getNewMsg());
//                    msgs2.setMsgID(msgs1.getMsgID());
//                    sqlite.saveMessages(msgs2);
//                }



    }

    @Override
            public void onFailure(Call<MsgsResponse> call, Throwable t) {

            }
        });
    }

    public void fillData(){
        Sqlite s=new Sqlite(this);
        List<Msgs> mymsgList= s.getMessages(TypeDescription);
        MsgsAdap msgsAdap = new MsgsAdap(MsgsActivity.this,mymsgList);
        recyclerView.setAdapter(msgsAdap);
        msgsAdap.notifyDataSetChanged();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        List<Msgs>newList = new ArrayList<>();
        for(Msgs msgs :msgsList){
            String msgDescription = msgs.getMsgDescription().toLowerCase();
            if(msgDescription.contains(newText)){
                newList.add(msgs);
            }
        }
        msgsAdap.setFilter(newList);
        return true;
    }
}