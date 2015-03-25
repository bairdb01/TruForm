package naddateam.truform.functionality;

<<<<<<< HEAD
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.TextView;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import naddateam.truform.R;
import naddateam.truform.Tester;

=======
>>>>>>> 2a26fa4edf5e3ed5a84ab9d17f2d796f0fbecaaa
/**
 * CIS3760
 * Naddateam Truform
 * Calibration.java
 * Author: Erik Hoffman
 * Last Modified March 1, 2015
 * Description: Class file for the data points returned by the device
 */
<<<<<<< HEAD

public class InstanceData {

    private double accelX;
    private double accelY;
    private double accelZ;
    private double gyroX;
    private double gyroY;
    private double gyroZ;
    public String test1;
=======

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class InstanceData {
    private double X;
    private double Y;
    private double Z;
    private int type;


        if(type == "AC:") {
            this.type = 0;
        }
        else
        {
            this.type = 1;
        }
    }

    public double getX(){
        return this.X;
    }
    public double getY(){
        return this.Y;
    }
    public double getZ(){
        return this.Z;
    }
    public int getType(){
        return this.type;
    }
}
