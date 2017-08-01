package com.epindustries.hackdayapp;

import android.graphics.Point;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Patrick Flathers on 4/8/17.
 */

public class myLine {

    myPoint p1, p2;

    myPoint[] p = new myPoint[2];
    int uid;

    public myLine(myPoint p1, myPoint p2, int uid) {
        p[0] = p1;
        p[1] = p2;
        if(p1.getX() + p1.getY() > p2.getX() + p2.getY()){
            this.p1 = p2;
            this.p2 = p1;
        }
        else{
            this.p1 = p1;
            this.p2 = p2;
        }
        this.uid = uid;
    }


    public myPoint getP1() {
        return p1;
    }

    public myPoint getP2() {
        return p2;
    }

    //Prevents duplicates of lines from existing in a game
    public boolean equals(myLine suspect) {

        myPoint temp1 = suspect.getP1();
        myPoint temp2 = suspect.getP2();

        if (temp1.getX() + temp1.getY() > temp2.getX() + temp2.getY()){
            myPoint temp3 = temp1;
            temp1 = temp2;
            temp2 = temp3;
        }
        if (temp1.getX() == p1.getX()) {
            if (temp1.getY() == p1.getY()) {
                if (temp2.getX() == p2.getX()) {
                    if (temp2.getY() == p2.getY()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int getUid() {
        return uid;
    }
}
