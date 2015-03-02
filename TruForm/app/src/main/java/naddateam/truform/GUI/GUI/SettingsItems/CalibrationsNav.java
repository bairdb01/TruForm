package naddateam.truform.GUI.GUI.SettingsItems;

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
        if (position == 0) {
            Intent goGenericCalib = new Intent(CalibrationsNav.this, CalibrationAction.class);
            startActivity(goGenericCalib);
        }
        else if (position == 1) {
            Intent goGenericCalib = new Intent(CalibrationsNav.this, CalibrationAction.class);
            startActivity(goGenericCalib);
        }
        else if (position == 2) {
            Intent goGenericCalib = new Intent(CalibrationsNav.this, CalibrationAction.class);
            startActivity(goGenericCalib);
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
