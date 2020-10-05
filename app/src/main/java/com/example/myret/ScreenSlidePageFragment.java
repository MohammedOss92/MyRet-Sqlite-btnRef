package com.example.myret;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myret.Modal.Msgs;

public class ScreenSlidePageFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public ScreenSlidePageFragment() {
    }

    public static Pager_Messages.PlaceholderFragment newInstance(int sectionNumber, String msg, int newMsg , Msgs m) {
        Pager_Messages.PlaceholderFragment fragment = new Pager_Messages.PlaceholderFragment();
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_pager_msgs, container, false);
        TextView tv=rootView.findViewById(R.id.tvMsg);

        String msg=getArguments().getString("Resaleh");
        final Msgs m=getArguments().getParcelable("MsgObject");

        tv.setText(msg);
        return rootView;
    }
}
