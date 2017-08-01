package com.epindustries.hackdayapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Patrick Flathers on 4/8/17.
 */

public class MainActivity extends AppCompatActivity {

    public static int uid;


    private android.app.FragmentManager fragMan;
    private android.app.FragmentTransaction fragmentTransaction;

    private int currFrag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PixelGridView pix = new PixelGridView(this);
        pix.setNumColumns(4);
        pix.setNumRows(6);
        pix.createPoints();
       setContentView(R.layout.activity_main);
        setFrags();
    }


    protected void onResume(){
        super.onResume();
    }


    private void setFrags(){
        fragMan = getFragmentManager();
        setHomeFrag();
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




