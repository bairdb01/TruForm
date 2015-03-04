package naddateam.truform.functionality;

/**
 * CIS3760
 * Naddateam Truform
 * Calibration.java
 * Author: Erik Hoffman
 * Last Modified March 1, 2015
 * Description: Class file for the data points returned by the device
 */

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class InstanceData {
    private double accelX;
    private double accelY;
    private double accelZ;
    private double gyroX;
    private double gyroY;
    private double gyroZ;

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
        /*String format (for parsing)
        AC: 123, 123, 123
        GY: 18, 20, -32
         */
        Scanner scanner = new Scanner(dataText);
        scanner.useDelimiter(" ");
        String type = scanner.next();
        int val1 = Integer.parseInt(scanner.next());
        int val2 = Integer.parseInt(scanner.next());
        int val3 = Integer.parseInt(scanner.next());

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
