package com.example.myret;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.myret.Modal.Msgs;

public class PlaceholderFragmen extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public PlaceholderFragmen() {
    }

    public static PlaceholderFragmen newInstance(int sectionNumber, String msg, Msgs m) {
        PlaceholderFragmen fragment = new PlaceholderFragmen();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString("Resaleh", msg);
        //args.putParcelable("MsgObject", (Parcelable) m);
        args.putParcelable("MsgObject",  m);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pager_msgs, container, false);

    return rootView;
    }
}
