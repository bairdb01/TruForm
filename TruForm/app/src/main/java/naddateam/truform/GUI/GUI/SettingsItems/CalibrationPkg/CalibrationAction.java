
package naddateam.truform.GUI.GUI.SettingsItems.CalibrationPkg;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import naddateam.truform.GUI.GUI.SettingsItems.HelpTutorials.HowCalibTut;
import naddateam.truform.R;

/**
 * CIS3760
 * Naddateam Truform
 * CalibrationAction.java
 * Author: Andrew Huynh
 * This is the main calibration page that users use to calibrate specific work outs. So far, in its
 * initial stages, it is very primitive and simple. There may be more options, buttons or other
 * types of views to see how the calibration works.
 * The users hit the button "Calibrate" to start
 * calibrating, and it will activate a timer first which will countdown to notify the user to start
 * calibration.
 * The help button will open the "How to Calibrate" tutorial, same as the one found in
 * settings > help > calibration tutorial.
 * The confirm will go back to the select to calibrate list, and save the info to the system.
 */
public class CalibrationAction extends ActionBarActivity implements View.OnClickListener{
    Button startCalibBtn;
    Button confirmCalibBtn;
    Button needHelpCalibBtn;
    Button stopCalibBtn;
    TextView countdownText;
    TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.calibrateaction_layout);

        //Assign buttons to variables
        startCalibBtn = (Button) findViewById(R.id.calibActionStart);
        stopCalibBtn = (Button) findViewById(R.id.calibActionStop);
        confirmCalibBtn = (Button) findViewById(R.id.calibActionConfirm);
        needHelpCalibBtn = (Button) findViewById(R.id.calibActionHelp);
        countdownText = (TextView) findViewById(R.id.calibActionCountdown);
        statusText = (TextView) findViewById(R.id.calibActionStatus);

        //set listeners for buttons
        startCalibBtn.setOnClickListener(this);
        stopCalibBtn.setOnClickListener(this);
        confirmCalibBtn.setOnClickListener(this);
        needHelpCalibBtn.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calibrationaction, menu);
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
    /**
     * Makes the start/abort/finish buttons perform stuff
     */
    public void onClick(View v) {
        switch(v.getId()) {
            case(R.id.calibActionStart):
                Toast.makeText(getApplicationContext(),"Calibrating...",Toast.LENGTH_SHORT).show();
                startCalibBtn.setEnabled(false);
                startCalibBtn.setVisibility(View.INVISIBLE);
                stopCalibBtn.setEnabled(true);
                stopCalibBtn.setVisibility(View.VISIBLE);
                break;
            case(R.id.calibActionStop):
                Toast.makeText(getApplicationContext(),"Stopping Calibration...",Toast.LENGTH_SHORT).show();
                stopCalibBtn.setEnabled(false);
                stopCalibBtn.setVisibility(View.INVISIBLE);
                startCalibBtn.setEnabled(true);
                startCalibBtn.setVisibility(View.VISIBLE);
                break;
            case(R.id.calibActionHelp):
                Toast.makeText(getApplicationContext(), "Help", Toast.LENGTH_SHORT).show();
                Intent goHowToCalibTut = new Intent(CalibrationAction.this, HowCalibTut.class);
                startActivity(goHowToCalibTut);
                break;
            case(R.id.calibActionConfirm):
                Toast.makeText(getApplicationContext(),"Confirmed Calibration",Toast.LENGTH_SHORT).show();
                super.finish();
                break;
        }
    }
}
