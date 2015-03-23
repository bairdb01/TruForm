package naddateam.truform.GUI.GUI.SettingsItems;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import naddateam.truform.GUI.GUI.Bluetooth;
import naddateam.truform.GUI.GUI.SettingsItems.HelpTutorials.BicepCurlTut;
import naddateam.truform.GUI.GUI.SettingsItems.HelpTutorials.ChestTut;
import naddateam.truform.GUI.GUI.SettingsItems.HelpTutorials.HowCalibTut;
import naddateam.truform.GUI.GUI.SettingsItems.HelpTutorials.MakeWorkoutTut;
import naddateam.truform.GUI.GUI.SettingsItems.HelpTutorials.SquatsTut;
import naddateam.truform.R;
import naddateam.truform.Tester;

/**
 * CIS3760
 * Naddateam Truform
 * HelpNav.java
 * Author: Andrew Huynh
 * This class is the Help page found under Navigation Drawer > Settings Help
 * It will consist a list of things the users can find information about.
 * Tutorials of how to do certain exercises
 * Tutorial how to create custome work outs
 * Tutorial how to calibrate
 * Bluetooth settings (temporary)
 */

public class HelpNav extends ActionBarActivity implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingshelp_layout);
        ListView lv = (ListView)findViewById(R.id.helpListView);
        lv.setOnItemClickListener(this);
    }

    @Override
/**
 * Opens the exercise screen
 * First Position = Exercise Form Tutorial
 * Second Position = Calibration Tutorial
 * Third Position = Workout Maker Tutorial
 */
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (position == 0) {

            builder.setTitle("Select an Exercise")
                    .setItems(R.array.calibrationItemsArr, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            /*
                            * The different selections of what tutorial for exercise they want to
                            * know about
                             */
                            if (which == 0) {
                                Intent goBicepCurlTut = new Intent(HelpNav.this, BicepCurlTut.class);
                                startActivity(goBicepCurlTut);
                            }
                            else if (which == 1) {
                                Intent goChestTut = new Intent(HelpNav.this, ChestTut.class);
                                startActivity(goChestTut);
                            }
                            else if (which == 2) {
                                Intent goSquatTut = new Intent(HelpNav.this, SquatsTut.class);
                                startActivity(goSquatTut);
                            }
                        }
                    });
            builder.create().show();
        }
        /*
        * How to make a workout
         */
        else if (position == 1) {
            Intent goMakeWorkoutTut = new Intent(HelpNav.this, MakeWorkoutTut.class);
            startActivity(goMakeWorkoutTut);
        }
        /*
        * How to calibrate
         */
        else if (position == 2) {
            Intent goHowToCalibTut = new Intent(HelpNav.this, HowCalibTut.class);
            startActivity(goHowToCalibTut);
        }
        /*
        * Bluetooth settings
         */
        else if (position == 3) {
            Intent goBlue = new Intent(HelpNav.this, Bluetooth.class);
            startActivity(goBlue);
        }
        else {
            if (position == 4) {
                Intent test = new Intent(this, Tester.class);
                startActivity(test);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settingshelpnav, menu);
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
