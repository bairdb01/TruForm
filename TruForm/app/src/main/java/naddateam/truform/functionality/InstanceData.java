package naddateam.truform.functionality;

/**
 * CIS3760
 * Naddateam Truform
 * Calibration.java
 * Author: Erik Hoffman
 * Last Modified March 1, 2015
 * Description: Class file for the data points returned by the device
 */

import android.util.Log;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class InstanceData {
    private int X;
    private int Y;
    private int Z;
    private int type;

    public void constructInstance() {
        Z = 0;
        Y = 0;
        X = 0;
        type = 0;
    }

    /*sets the stuff*/
    public void setInstanceData(String dataText) {
        /*Gather the bluetooth data here*/
        /*String format (for parsing)
        AC: 123, 123, 123
        GY: 18, 20, -32
         */

        Scanner scanner = new Scanner(dataText);
        scanner.useDelimiter(" ");
        String typeString = scanner.next();
        int val1 = Integer.parseInt(scanner.next());
        int val2 = Integer.parseInt(scanner.next());
        int val3 = Integer.parseInt(scanner.next());

        this.X = val1;
        this.Y = val2;
        this.Z = val3;

        if(typeString.equals("Ac:")) {
            this.type = 0;
        }
        else
        {
            this.type = 1;
        }
    }

    public int getX(){
        return this.X;
    }
    public int getY(){
        return this.Y;
    }
    public int getZ(){
        return this.Z;
    }
    public int getType(){
        return this.type;
    }
}
