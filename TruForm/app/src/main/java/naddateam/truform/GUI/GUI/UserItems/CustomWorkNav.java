package naddateam.truform.GUI.GUI.UserItems;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import naddateam.truform.R;
/**
 * CIS3760
 * Naddateam Truform
 * CustomWorkNav.java
 * Author: Andrew Huynh
 * This will be the UI where users can select a list of exercises to create their own workout.
 * It is found under Navigation Drawer > User > Custom workout
 * This will be fully implemented for GOLD. It will open a list where they can select, and it will
 * compile to one workout and they can set the reps/set and name of the workout. They can also post
 * it to the homescreen.
 */
public class CustomWorkNav extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customwork_layout);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*
        * Inflate the menu; this adds items to the action bar if it is present.
         */
        getMenuInflater().inflate(R.menu.menu_customworknav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /* Handle action bar item clicks here. The action bar will
        * automatically handle clicks on the Home/Up button, so long
        * as you specify a parent activity in AndroidManifest.xml.
        */
        int id = item.getItemId();

        /*
        *noinspection SimplifiableIfStatement
         */
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
