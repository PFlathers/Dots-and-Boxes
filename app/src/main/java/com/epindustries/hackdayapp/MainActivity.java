package com.epindustries.hackdayapp;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static int uid;


    private android.app.FragmentManager fragMan;
    private android.app.FragmentTransaction fragmentTransaction;
    private boolean start = true;
    private int currFrag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PixelGridView pix = new PixelGridView(this);
        pix.setNumColumns(4);
        pix.setNumRows(6);
        pix.createPoints();
       // setContentView(pix);


       setContentView(R.layout.activity_main);
        setFrags();



    }

    protected void onResume(){
        super.onResume();



        View decorView = getWindow().getDecorView();

       // int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOptions);


        return;



    }

    private void setFrags(){
        fragMan = getFragmentManager();
        if (start){
            setHomeFrag();
        }

    }

    private void setHomeFrag() {
        fragmentTransaction = fragMan.beginTransaction();
        Fragment main = new MainFragment();

        fragmentTransaction.replace(R.id.placeholder, main, "MAIN");
        fragmentTransaction.commit();


    }


    public void startgame(View view){

        fragmentTransaction = fragMan.beginTransaction();
        Fragment main = new GameFragment();

        fragmentTransaction.replace(R.id.placeholder, main, "GAME");
        fragmentTransaction.commit();
        currFrag = 1;

    }

    protected void onWindowFocusChanged() {


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        if (currFrag == 1){
            fragmentTransaction = fragMan.beginTransaction();
            Fragment main = new MainFragment();

            fragmentTransaction.replace(R.id.placeholder, main, "MAIN");
            fragmentTransaction.commit();
            currFrag = 0;

        }
        else{
            super.finish();


        }


    }



}




