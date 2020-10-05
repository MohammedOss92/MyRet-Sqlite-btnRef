package com.example.myret;



import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myret.Adapter.FavAdap;
import com.example.myret.Adapter.MsgsAdap;
import com.example.myret.Modal.Msgs;
import com.example.myret.Sqlite.Sqlite;

import java.util.ArrayList;
import java.util.List;

public class FavActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    FavAdap favAdap;
    List<Msgs> mymsgList= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        Sqlite s=new Sqlite(this);
        mRecyclerView=findViewById(R.id.r_view);
        mymsgList= s.getFavMessages();
        favAdap = new FavAdap(FavActivity.this,mymsgList);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(favAdap);
        favAdap.notifyDataSetChanged();

    }
    public void fillData(){
        Sqlite s=new Sqlite(this);
        List<Msgs> mymsgList= s.getFavMessages();
        favAdap = new FavAdap(FavActivity.this,mymsgList);
        mRecyclerView.setAdapter(favAdap);
        favAdap.notifyDataSetChanged();
    }
}