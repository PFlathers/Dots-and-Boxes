package com.epindustries.hackdayapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Patrick Flathers on ${DATE}.
 */


public class MainFragment extends Fragment {
    private View view;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_main, container, false);

        TextView Instruction = (TextView) getActivity().findViewById(R.id.textView2);
        Instruction.setText(R.string.Instructions);

        return view;
    }
}
