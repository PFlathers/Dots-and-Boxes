package com.epindustries.hackdayapp;


import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;



import java.util.ArrayList;

/**
 * Created by flayers on 4/8/17.
 *
 */

public class PixelGridView extends View {
    private int numColumns, numRows;
    private int cellWidth, cellHeight;
    private Paint blackPaint = new Paint();
    private Paint redPaint = new Paint();
    private Paint bluePaint = new Paint();
    private  Paint crimson = new Paint();
    private Paint indigo = new Paint();

    private final static int CELLOFFSET = 10;
    final String redT = "Red's Turn!";
    final String blueT = "Blue's Turn!";
    String temp = "";

    ArrayList<myBoxes> mb = new ArrayList<>();


    private int blueboxes = 0;
    private int redboxes = 0;
    int numboxes = 0;

    int x1, x2;
    int y1, y2;
    boolean drawing = false;
    ArrayList<myLine> lines = new ArrayList<>();
    ArrayList<myPoint> points = new ArrayList<>();

    boolean swap = true;

    int firstPointIndex = -1, secPointIndex = -1;




    public PixelGridView(Context context) {
        this(context, null);
    }

    public PixelGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        blackPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        redPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        redPaint.setColor(Color.RED);
        redPaint.setStrokeWidth(10);


        bluePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        bluePaint.setColor(Color.BLUE);
        bluePaint.setStrokeWidth(10);

        crimson.setStyle(Paint.Style.FILL_AND_STROKE);
        crimson.setColor(Color.rgb(255,13,181));
        crimson.setStrokeWidth(10);

        indigo.setStyle(Paint.Style.FILL_AND_STROKE);
        indigo.setColor(Color.CYAN);
        indigo.setStrokeWidth(10);


    }

    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        //super.onLayout(changed, left + 10, top + 10, right - 10, bottom - 10);

    }

    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
        calculateDimensions();
    }

//    public int getNumColumns() {
//        return numColumns;
//    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
        calculateDimensions();
    }

//    public int getNumRows() {
//        return numRows;
//    }


    public void createPoints() {



        for (int i = 0; i < numColumns +1; i++) {
            for (int j = 0; j < numRows +1; j++) {
                points.add(new myPoint(i, j));
               // System.out.println("X: " + i + " Y: " + j);

            }

        }

    }



    public void createBoxes() {

        myBoxes box;
        myLine tempLine1;
        myLine tempLine2;
        myLine tempLine3;
        myLine tempLine4;

        for (int i = 0; i < points.size() - 8; i++) {
            tempLine1 = new myLine(points.get(i), points.get(i + 1), -1);
            tempLine2 = new myLine(points.get(i), points.get(i + 7), -1);
            tempLine3 = new myLine(points.get(i+1), points.get(i + 8), -1);
            tempLine4 = new myLine(points.get(i + 8), points.get(i + 7), -1);

            System.out.println("lX1: " + tempLine1.getP1().getX() + " Y1: " + tempLine1.getP1().getY() + " X2: " + tempLine1.getP2().getX() + " Y2: " + tempLine1.getP2().getY());
            System.out.println("lX2: " + tempLine2.getP1().getX() + " Y1: " + tempLine2.getP1().getY() + " X2: " + tempLine2.getP2().getX() + " Y2: " + tempLine2.getP2().getY());
            System.out.println("lX3: " + tempLine3.getP1().getX() + " Y1: " + tempLine3.getP1().getY() + " X2: " + tempLine3.getP2().getX() + " Y2: " + tempLine3.getP2().getY());
            System.out.println("lX4: " + tempLine4.getP1().getX() + " Y1: " + tempLine4.getP1().getY() + " X2: " + tempLine4.getP2().getX() + " Y2: " + tempLine4.getP2().getY());

            System.out.println(points.get(i + 6). getX() + " " + points.get(i + 6).getY());

            box = new myBoxes(tempLine1, tempLine2, tempLine3, tempLine4);

            mb.add(box);
        }
    }

    private void swapUid() {
        if (MainActivity.uid == 0) {
            MainActivity.uid = 1;
        } else {
            MainActivity.uid = 0;
        }

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        calculateDimensions();
    }

    private void calculateDimensions() {
        if (numColumns < 1 || numRows < 1) {
            return;
        }

        cellWidth = (getWidth() - 20) / numColumns;
        cellHeight = (getHeight() - 20) / numRows;


        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);


        if (numColumns == 0 || numRows == 0) {
            return;
        }

        int width = getWidth();
        int height = getHeight();


        for (myBoxes box : mb){

                if (box.getId() == 0){
                     canvas.drawRect(box.getL1().getP1().getX() * cellWidth + CELLOFFSET, (box.getL1().getP1().getY()) * cellHeight + CELLOFFSET,  (box.getL1().getP1().getX() + 1) * cellWidth + CELLOFFSET, (box.getL1().getP1().getY() + 1) * cellHeight + CELLOFFSET, crimson);

                }
                else if (box.getId() == 1){
                    canvas.drawRect(box.getL1().getP1().getX() * cellWidth + CELLOFFSET,box.getL1().getP1().getY() * cellHeight + CELLOFFSET, box.getL4().getP2().getX() * cellWidth  + CELLOFFSET, box.getL4().getP2().getY() * cellHeight + CELLOFFSET, indigo);

                }

        }

        for (int i = 0; i < numColumns + 1; i++) {
            canvas.drawLine(i * cellWidth + CELLOFFSET, 0 + CELLOFFSET, i * cellWidth + CELLOFFSET, height + CELLOFFSET, blackPaint);
        }


        for (int i = 0; i < numRows + 1; i++) {
            canvas.drawLine(0 + CELLOFFSET, i * cellHeight + CELLOFFSET, width + CELLOFFSET, i * cellHeight + CELLOFFSET, blackPaint);
        }







        for (int i = 0; i < lines.size(); i++) {

            myLine l = lines.get(i);

            if (l.getUid() == 0) {
                canvas.drawLine(l.getP1().getX() * cellWidth + CELLOFFSET, l.getP1().getY() * cellHeight + CELLOFFSET, l.getP2().getX() * cellWidth + CELLOFFSET, l.getP2().getY() * cellHeight + CELLOFFSET, redPaint);
            } else {
                canvas.drawLine(l.getP1().getX() * cellWidth + CELLOFFSET, l.getP1().getY() * cellHeight + CELLOFFSET, l.getP2().getX() * cellWidth + CELLOFFSET, l.getP2().getY() * cellHeight + CELLOFFSET, bluePaint);
            }


        }


        for (int i = 0; i < points.size(); i++) {
            myPoint p = points.get(i);

            int x = 0;
            int y = 0;

            x = p.getX() *cellWidth + CELLOFFSET;
            y = p.getY() * cellHeight + CELLOFFSET;





                canvas.drawCircle(x, y, 10, blackPaint);




        }
        Paint currentPaint;



        if (MainActivity.uid == 0) {
            currentPaint = redPaint;
        } else {
            currentPaint = bluePaint;
        }

        if (drawing) {

            canvas.drawLine(x1, y1, x2, y2, currentPaint);
        }

    }





    public void nop(){

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        boolean result = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = x2 = (int) event.getX();
                y1 = y2 = (int) event.getY();


                drawing = true;
                result = true;
                break;
            case MotionEvent.ACTION_MOVE:
                x2 = (int) event.getX();
                y2 = (int) event.getY();
                result = true;
                break;
            case MotionEvent.ACTION_UP:
                x2 = (int) event.getX();
                y2 = (int) event.getY();


                myPoint p1, p2;


                int x, y;
                for (int i = 0; i < points.size(); i++) {

                    x = points.get(i).getX() * cellWidth;
                    y = points.get(i).getY() * cellHeight;


                    if (((x1 > (x - 100)) && (x1 < (x + 100))) && ((y1 > (y - 100)) && (y1 < (y + 100)))) {


                        firstPointIndex = i;
                    }


                    if (((x2 > (x - 100)) && (x2 < (x + 100))) && ((y2 > (y - 100)) && (y2 < (y + 100)))) {
                        secPointIndex = i;
                    }
                }


                    if (firstPointIndex == secPointIndex) {
                        nop();
                    } else if (firstPointIndex == -1 || secPointIndex == -1) {
                       nop();
                    } else if (firstPointIndex % numRows + 1 == 0 && secPointIndex == firstPointIndex + 1) {
                       nop();
                    } else if (lines.contains(new myLine(points.get(firstPointIndex), points.get(secPointIndex), 1))) {
                       nop();
                    } else if (secPointIndex % numRows + 1 == 0 && firstPointIndex == secPointIndex + 1) {
                       nop();
                    } else if (firstPointIndex == secPointIndex + 1 || secPointIndex == firstPointIndex + 1) {

                        p1 = points.get(firstPointIndex);
                        p2 = points.get(secPointIndex);

                        myLine canidate = new myLine(p1, p2, MainActivity.uid);

                        int lineCount = 0;
                        for (myLine line : lines){
                            if (line.equals(canidate))
                                lineCount ++;
                        }

                        if (lineCount == 0){
                            lines.add(canidate);
                            swap = true;
                        }




                    } else if (firstPointIndex == secPointIndex + numRows + 1 || secPointIndex == firstPointIndex + numRows + 1) {
                        p1 = points.get(firstPointIndex);
                        p2 = points.get(secPointIndex);



                        myLine canidate = new myLine(p1, p2, MainActivity.uid);

                        int lineCount = 0;
                        for (myLine line : lines){
                            if (line.equals(canidate))
                                lineCount ++;
                        }

                        if (lineCount == 0){
                            lines.add(canidate);
                            swap = true;
                        }

                    }





                checkBoxes();


                firstPointIndex = -1;
                secPointIndex = -1;

                if (swap){









                    int boxcount = 0;

                    for (myBoxes box : mb){
                        if(box.getId() != -1){
                            boxcount ++;
                        }



                    }




                    if (boxcount > numboxes){
                        numboxes = boxcount;
                    }

                    else{




                        if(MainActivity.uid == 1) {
                            temp = redT;





                        }
                        else{
                            temp = blueT;
                        }

                        swapUid();
                    }




                }
                TextView t = (TextView) this.getRootView().findViewById(R.id.textView2);
                t.setText(temp + "\n Red Score:" + redboxes + "\t Blue Score:" + blueboxes);

                swap = false;
                drawing = false;
                result = true;
                break;
        }
        if (result)
            invalidate();
        return result;

    }



    private void checkBoxes() {
        for (myBoxes box : mb) {
            if (box.getId() != -1) {
                nop();
            }
            else {
                for (myLine line : lines) {
                    if (line.equals(box.getL1())) {
                        box.increment();
                    }
                     if (line.equals(box.getL2())) {
                        box.increment();
                    }
                     if (line.equals(box.getL3())) {
                        box.increment();
                    }
                     if (line.equals(box.getL4())) {
                        box.increment();
                    }
                    int counter =  box.getCounter();
                    if (counter == 4) {
                        box.setCounter(0);
                        if (MainActivity.uid == 0){
                            redboxes++;
                        }
                        else{
                            blueboxes ++;
                        }
                        box.setId(MainActivity.uid);
                    }
                }
                box.setCounter(0);
            }
        }
    }
}









