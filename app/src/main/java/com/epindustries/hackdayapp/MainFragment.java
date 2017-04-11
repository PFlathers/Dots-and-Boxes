package com.epindustries.hackdayapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by flayers on 4/8/17.
 */




public class MainFragment extends Fragment {
    private View view;
    private Button start;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_main, container, false);


        TextView Instruction = (TextView) getActivity().findViewById(R.id.textView2);
        Instruction.setText("This a simple game of dots and boxes. To play draw a line between two dots, and if the lines draw form a box you will be awarded a point, but be careful! your opponent can use your lines to form their own boxes!");




        return view;



    }

}
