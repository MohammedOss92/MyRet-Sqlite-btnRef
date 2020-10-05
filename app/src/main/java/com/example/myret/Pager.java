package com.example.myret;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myret.Modal.Msgs;

import java.util.List;

public class Pager extends AppCompatActivity {

    private SectionsPagerAdapter pagerAdapter;
    private ViewPager mViewPager;
    List<Msgs> myArrayList;
    int titleID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_message);

        Toolbar a = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(a);
        Intent i=getIntent();
        titleID=i.getExtras().getInt("titleID");
        int pos=i.getExtras().getInt("pos");
        pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(pagerAdapter);
    }
        public class SectionsPagerAdapter extends FragmentPagerAdapter {

            public SectionsPagerAdapter(@NonNull FragmentManager fm) {
                super(fm);
            }

            @NonNull
            @Override
            public Fragment getItem(int position) {
                Msgs m=(Msgs) myArrayList.get(position);

                return PlaceholderFragmen.newInstance(position + 1,m.getMsgDescription(), m);
            }

            @Override
            public int getCount() {
                return myArrayList.size();
            }
        }
}
