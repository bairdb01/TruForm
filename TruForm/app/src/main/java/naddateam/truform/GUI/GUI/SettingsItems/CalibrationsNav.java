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
import android.widget.Toast;

import naddateam.truform.GUI.GUI.SettingsItems.CalibrationPkg.CalibrationAction;
import naddateam.truform.R;
/**
 * CIS3760
 * Naddateam Truform
 * CalibrationsNav.java
 * Author: Andrew Huynh
 * This is the Calibrations navigation for Settings > Calibration in the drawer. It will consist
 * of the dialog for the users to select a specific workout to calibrate. At the moment it does
 * not do the actual calibrating, just navigate to the placeholding page.
 */
public class CalibrationsNav extends ActionBarActivity implements AdapterView.OnItemClickListener{

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calibrations_layout); /*Display the layout*/
        ListView lv = (ListView)findViewById(R.id.calibrationListView); /*Populate layout with
                                                                        listview*/
        lv.setOnItemClickListener(this);
    }

@Override
/*
* For the list, waits for input by the user. Depending what item the user hits, it will open
* a new blank activity. Right now it only opens a generic calibrations page with no functionality.
 */
public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    AlertDialog.Builder builder = new AlertDialog.Builder(this);

    /* CHEST AND TRICEPS ---------------------------------------------------------------------------
    * 0 Dumbbell Chest Press
    * 1 Incline Bench Press
    * 2 Dumbbell Flies
    * 3 Incline Flies
    * 4 Skull Crushers
    * 5 Dips
    * 6 Tricep Extensions
     */
    if (position == 0) {
        builder.setTitle("Select an Exercise")
                .setItems(R.array.ChestAndTriceps, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            Intent goGenericCalib = new Intent(CalibrationsNav.this,
                                    CalibrationAction.class);
                            startActivity(goGenericCalib);
                        }
                        else if (which == 1) {
                            Intent goGenericCalib = new Intent(CalibrationsNav.this,
                                    CalibrationAction.class);
                            startActivity(goGenericCalib);
                        }
                        else if (which == 2) {
                            Intent goGenericCalib = new Intent(CalibrationsNav.this,
                                    CalibrationAction.class);
                            startActivity(goGenericCalib);
                        }
                        else if (which == 3) {
                            Intent goGenericCalib = new Intent(CalibrationsNav.this,
                                    CalibrationAction.class);
                            startActivity(goGenericCalib);
                        }
                        else if (which == 4) {
                            Intent goGenericCalib = new Intent(CalibrationsNav.this,
                                    CalibrationAction.class);
                            startActivity(goGenericCalib);
                        }
                        else if (which == 5) {
                            Intent goGenericCalib = new Intent(CalibrationsNav.this,
                                    CalibrationAction.class);
                            startActivity(goGenericCalib);
                        }
                        else if (which == 6) {
                            Intent goGenericCalib = new Intent(CalibrationsNav.this,
                                    CalibrationAction.class);
                            startActivity(goGenericCalib);
                        }
                    }
                });
        builder.create().show();
    }

    /* LEGS ----------------------------------------------------------------------------------------
    * 0 Squats
    * 1 Front Squats
    * 2 Leg Extensions
    * 3 Hamstring Curls
    * 4 Lying Hamstring Curls
    * 5 Dips
    * 6 Tricep Extensions
     */
    else if (position == 1) {
        builder.setTitle("Select an Exercise")
                .setItems(R.array.Legs, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            Intent goGenericCalib = new Intent(CalibrationsNav.this,
                                    CalibrationAction.class);
                            startActivity(goGenericCalib);
                        }
                        else if (which == 1) {
                            Intent goGenericCalib = new Intent(CalibrationsNav.this,
                                    CalibrationAction.class);
                            startActivity(goGenericCalib);
                        }
                        else if (which == 2) {
                            Intent goGenericCalib = new Intent(CalibrationsNav.this,
                                    CalibrationAction.class);
                            startActivity(goGenericCalib);
                        }
                        else if (which == 3) {
                            Intent goGenericCalib = new Intent(CalibrationsNav.this,
                                    CalibrationAction.class);
                            startActivity(goGenericCalib);
                        }
                        else if (which == 4) {
                            Intent goGenericCalib = new Intent(CalibrationsNav.this,
                                    CalibrationAction.class);
                            startActivity(goGenericCalib);
                        }
                    }
                });
        builder.create().show();
    }

    /* BACK AND BICEPS -----------------------------------------------------------------------------
    * 0 Deadlifts
    * 1 Bentover Rows
    * 2 Wide Grip Pullbar
    * 3 Cable Rows
    * 4 Dumbbell Bicep Curls
    * 5 Barbell Bicep Curls
    * 6 Tricep Extensions
    * 7 Spider Curls
     */
    else if (position == 2) {
        builder.setTitle("Select an Exercise")
                .setItems(R.array.BackAndBiceps, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            Intent goGenericCalib = new Intent(CalibrationsNav.this,
                                    CalibrationAction.class);
                            startActivity(goGenericCalib);
                        }
                        else if (which == 1) {
                            Intent goGenericCalib = new Intent(CalibrationsNav.this,
                                    CalibrationAction.class);
                            startActivity(goGenericCalib);
                        }
                        else if (which == 2) {
                            Intent goGenericCalib = new Intent(CalibrationsNav.this,
                                    CalibrationAction.class);
                            startActivity(goGenericCalib);
                        }
                        else if (which == 3) {
                            Intent goGenericCalib = new Intent(CalibrationsNav.this,
                                    CalibrationAction.class);
                            startActivity(goGenericCalib);
                        }
                        else if (which == 4) {
                            Intent goGenericCalib = new Intent(CalibrationsNav.this,
                                    CalibrationAction.class);
                            startActivity(goGenericCalib);
                        }
                        else if (which == 5) {
                            Intent goGenericCalib = new Intent(CalibrationsNav.this,
                                    CalibrationAction.class);
                            startActivity(goGenericCalib);
                        }
                        else if (which == 6) {
                            Intent goGenericCalib = new Intent(CalibrationsNav.this,
                                    CalibrationAction.class);
                            startActivity(goGenericCalib);
                        }
                        else if (which == 7) {
                            Intent goGenericCalib = new Intent(CalibrationsNav.this,
                                    CalibrationAction.class);
                            startActivity(goGenericCalib);
                        }
                    }
                });
        builder.create().show();
    }


    /* SHOULDERS ----------------------------------------------------------------------------------
    * 0 Shoulder Press
    * 1 Lateral Raises
    * 2 Reverse Flies
    * 3 Upright Barbell Rows
     */
    else if (position == 3) {
        builder.setTitle("Select an Exercise")
                .setItems(R.array.Shoulders, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            Intent goGenericCalib = new Intent(CalibrationsNav.this,
                                    CalibrationAction.class);
                            startActivity(goGenericCalib);
                        }
                        else if (which == 1) {
                            Intent goGenericCalib = new Intent(CalibrationsNav.this,
                                    CalibrationAction.class);
                            startActivity(goGenericCalib);
                        }
                        else if (which == 2) {
                            Intent goGenericCalib = new Intent(CalibrationsNav.this,
                                    CalibrationAction.class);
                            startActivity(goGenericCalib);
                        }
                        else if (which == 3) {
                            Intent goGenericCalib = new Intent(CalibrationsNav.this,
                                    CalibrationAction.class);
                            startActivity(goGenericCalib);
                        }
                    }
                });
        builder.create().show();
    }
}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calibrations, menu);
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
