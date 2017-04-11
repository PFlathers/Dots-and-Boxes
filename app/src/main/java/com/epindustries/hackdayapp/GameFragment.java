package com.epindustries.hackdayapp;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.net.URL;

/**
 * Created by flayers on 4/8/17.
 */

public class GameFragment extends Fragment {
    private View view;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        TextView Instruction = (TextView) getActivity().findViewById(R.id.textView2);
        Instruction.setText("Red's Turn!\n Red Score:0\tBlue Score:0");
        MainActivity.uid = 0;


        PixelGridView pix = new PixelGridView(getActivity());


        //view = inflater.inflate(R.layout.fragment_matrix, container, false);






        pix.setNumColumns(4);
        pix.setNumRows(6);
        pix.createPoints();
        pix.createBoxes();
        //view = inflater.inflate(R.layout.fragment_matrix, container, false);


        return pix;


    }

}