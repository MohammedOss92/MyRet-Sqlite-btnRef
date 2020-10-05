package com.example.myret;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.myret.Modal.Msgs;
import com.example.myret.Sqlite.Sqlite;
import java.util.List;
import java.util.Locale;


public class Pager_Messages extends AppCompatActivity {


    SectionsPagerAdapter mSectionsPagerAdapter;



    int titleID;
    List<Msgs> myArrayList;
    String filter="";
    int msgId=0;
    int origPOS=0;
    int newMsg=0;
    int pos=0;
    boolean sourceISFav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_message);

        Toolbar a = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(a);

        Intent i=getIntent();
        titleID=i.getExtras().getInt("titleID");
        pos=i.getExtras().getInt("pos");
        filter=i.getExtras().getString("filter");
        msgId=i.getExtras().getInt("msgID");
        // in case of fav the origPos sent from FavMessages as favOrder
        // but in case of normal message it sent as origpos
        origPOS=i.getExtras().getInt("origPos");
        newMsg=i.getExtras().getInt("newMsg");
        sourceISFav=i.getExtras().getBoolean("sourceIsFav");

        Sqlite ss=new Sqlite(getApplicationContext());
        if(sourceISFav)
        {
            //we have to keep it ASC in Fav because of pager position
            //because we use its order while in normal messages it is not favorder but it is origposition
            myArrayList = ss.getFavMessages() ;
        }

        else {
            myArrayList = ss.getMessages(titleID);
        }
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.


        // Set up the ViewPager with the sections adapter.
        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);

//        mViewPager.setCurrentItem(pos);
        if(sourceISFav)
        //because order of fav start from index difference while normal messages start from ok index
        {mViewPager.setCurrentItem(pos);}
        else
        {mViewPager.setCurrentItem(pos);}



    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Fragment fragment = new Fragment();
            Msgs m=(Msgs) myArrayList.get(position);


            return PlaceholderFragment.newInstance(position + 1,m.getMsgDescription(),m.getNewMsg(), m);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.

            return myArrayList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {

            }
            return null;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String ARG_MSG = "msg";
        int globalTextSize=12;

        String message;
        TextView tv_Msg,tv_Title;
        ImageView img_fave,img_new;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */

        public static PlaceholderFragment newInstance(int sectionNumber, String msg, int newMsg , Msgs m) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putString("Resaleh", msg);
            args.putInt("newMsg", newMsg);
            //args.putParcelable("MsgObject", (Parcelable) m);
            args.putParcelable("MsgObject",  m);

//            Bundle bundle = new Bundle();
//            bundle.putSerializable("msgClass", (Serializable) m);
//            args.putBundle("msgClass",(Serializable) m);
            fragment.setArguments(args);



            return fragment;
        }

        public PlaceholderFragment()
        {

        }


        @Override
        public void onAttach(Activity activity) {
            // Toast.makeText(getActivity(),"AAA",Toast.LENGTH_SHORT).show();
            super.onAttach(activity);
        }




        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {

            View rootView = inflater.inflate(R.layout.fragment_pager_msgs, container, false);
            tv_Msg  =rootView.findViewById(R.id.tvMsg) ;
            tv_Title=rootView.findViewById(R.id.tvTitle) ;
            img_fave=rootView.findViewById(R.id.imgFav)  ;
            img_new =rootView.findViewById(R.id.imgNew) ;

            String msg=getArguments().getString("Resaleh");
            int theNewMsg=getArguments().getInt("newMsg");
            final Msgs m=getArguments().getParcelable("MsgObject");
            final Sqlite s=new Sqlite(getActivity());
            String titleDesc=s.getMsgTitleByTitleID(m.getTypeDescription());

            tv_Title.setText(titleDesc);
            tv_Msg.setText(msg);

            if(theNewMsg==0)
            {
                img_new.setVisibility(View.INVISIBLE);
            }
            else
            {
                img_new.setVisibility(View.VISIBLE);
            }

            if(s.getIFMsgIsFav(m)==1)
            {
                img_fave.setBackgroundResource(R.drawable.f);
            }
            else
            {
                img_fave.setBackgroundResource(R.drawable.nf);
            }

            img_fave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (s.getIFMsgIsFav(m) == 0) {
                        img_fave.setBackgroundResource(R.drawable.f);
                        s.changeFav(m, 1);

                    } else {
                        img_fave.setBackgroundResource(R.drawable.nf);
                        s.changeFav(m, 0);
                    }    }
            });

                    return rootView;
                }

            }

}
