package naddateam.truform.functionality;

import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.TextView;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
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

public class InstanceData {

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

    public void setInstanceData(String dataText) {
        /*Gather the bluetooth data here*/


        if(type == "AC:") {
            accelX = val1;
            accelY = val2;
            accelZ = val3;
        }
        else
        {
            gyroX = val1;
            gyroY = val2;
            gyroZ = val3;
        }
    }
}
