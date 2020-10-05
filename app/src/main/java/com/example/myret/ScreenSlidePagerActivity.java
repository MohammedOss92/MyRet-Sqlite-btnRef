package com.example.myret;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myret.Modal.Msgs;
import com.example.myret.Sqlite.Sqlite;

import java.util.ArrayList;
import java.util.List;

public class ScreenSlidePagerActivity extends FragmentActivity {

    List<Msgs> msgsList = new ArrayList<>();
    private ViewPager2 viewPager;
    private FragmentStateAdapter pagerAdapter;
    int TypeDescription;
    int msgID=0;
    boolean sourceISFav;
    int origPOS=0;
    int newMsg=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        Intent i = getIntent();
        TypeDescription=i.getExtras().getInt("titleID");
        int pos=i.getExtras().getInt("pos");
        msgID=i.getExtras().getInt("msgID");
        origPOS=i.getExtras().getInt("origPos");
        newMsg=i.getExtras().getInt("newMsg");
        sourceISFav=i.getExtras().getBoolean("sourceIsFav");
        Sqlite s=new Sqlite(this);
        if(sourceISFav)
        {
            //we have to keep it ASC in Fav because of pager position
            //because we use its order while in normal messages it is not favorder but it is origposition
            msgsList = s.getFavMessages() ;
        }
        else {
            msgsList = s.getMessagesNotOrdered(TypeDescription);
        }

        pagerAdapter = new ScreenSlidePagerAdapter(this) ;

        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);

        if(sourceISFav) {
            viewPager.setCurrentItem(pos);
        }
        else{
            viewPager.setCurrentItem(pos);
        }


    }
    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }


        @Override
        public Fragment createFragment(int position) {

            Msgs m=(Msgs) msgsList.get(position);
            return ScreenSlidePageFragment.newInstance(position + 1,m.getMsgDescription(),m.getNewMsg(), m);

//            return new ScreenSlidePageFragment();
        }



        @Override
        public int getItemCount() {
            return msgsList.size();
        }
    }


}
