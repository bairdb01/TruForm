package naddateam.truform.functionality;

import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.TextView;

import naddateam.truform.R;
import naddateam.truform.Tester;

/**
 * CIS3760
 * Naddateam Truform
 * Calibration.java
 * Author: Erik Hoffman & Rob Little & Benjamin Baird
 * Last Modified March 3, 2015
 * Description: Class file for the data points returned by the device
 */
public class InstanceData  extends ActionBarActivity {
    private double accelX;
    private double accelY;
    private double accelZ;
    private double gyroX;
    private double gyroY;
    private double gyroZ;
    public String test1;

    public void constructInstance() {
        accelZ = 0;
        accelY = 0;
        accelX = 0;
        gyroX = 0;
        gyroZ = 0;
        gyroY = 0;
    }

    public void setInstanceData(String datatext) {
        /*Gather the bluetooth data here*/
        String test1 = datatext;
        Tester test = new Tester();

        TextView lv = (TextView) findViewById(R.id.TextViewBT);
        lv.setText(test1);
    }


}
