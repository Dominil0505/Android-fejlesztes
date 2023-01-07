package com.example.currency_exchanger;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextClock cl0ck = view.findViewById(R.id.timeTextClock);
        cl0ck.setFormat12Hour(null);
        cl0ck.setFormat24Hour("hh:mm:ss");


        TextClock d4te = view.findViewById(R.id.dateTextClock);
        d4te.setFormat12Hour(null);
        d4te.setFormat24Hour("EEE MMM d");
        return view;
    }
}