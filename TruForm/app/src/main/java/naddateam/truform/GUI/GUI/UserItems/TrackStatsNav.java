package naddateam.truform.GUI.GUI.UserItems;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import naddateam.truform.R;
/**
 * CIS3760
 * Naddateam Truform
 * TrackStatsNav.java
 * Author: Andrew Huynh
 * This class will be the Past Statistics found in Navigation Drawer > User > Tracked Statistics
 * This page consists of all past tracked user data users have performed. That will be able
 * Total sets, total reps, percentage of form and able to compare.
 */
public class TrackStatsNav extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trackstats_layout);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_trackstatsnav, menu);
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
}
