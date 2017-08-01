package com.epindustries.hackdayapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by flayers on 4/8/17.
 */

public class GameFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView Instruction = (TextView) getActivity().findViewById(R.id.textView2);
        Instruction.setText("Red's Turn!\n Red Score:0\tBlue Score:0");

        MainActivity.uid = 0;

        PixelGridView pix = new PixelGridView(getActivity());
        pix.setNumColumns(4);
        pix.setNumRows(6);
        pix.createPoints();
        pix.createBoxes();
        return pix;
    }
}