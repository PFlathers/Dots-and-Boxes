package com.epindustries.hackdayapp;

/**
 * Created by Patrick Flathers on 4/8/17.
 */

public class myBoxes {

    myLine l1;
    myLine l2;
    myLine l3;

    public myLine getL1() {
        return l1;
    }

    public myLine getL2() {
        return l2;
    }

    public myLine getL3() {
        return l3;
    }

    public myLine getL4() {
        return l4;
    }

    public int getId() {
        return id;
    }

    myLine l4;
    
    int counter;
    
    public void increment(){
        counter++;
    }
    public int getCounter(){
        return counter;
    }



    int id = -1;


    public myBoxes(myLine l1, myLine l2, myLine l3, myLine l4){

        this.l1 = l1;
        this.l2 = l2;
        this.l3 = l3;
        this.l4 = l4;
        counter = 0;
    }


    public void setId(int id){
        this.id = id;
    }


    public void setCounter(int counter) {
        this.counter = counter;
    }
}
