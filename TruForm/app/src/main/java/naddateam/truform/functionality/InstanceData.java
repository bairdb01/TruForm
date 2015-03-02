package naddateam.truform.functionality;

/**
 * CIS3760
 * Naddateam Truform
 * Calibration.java
 * Author: Erik Hoffman
 * Last Modified March 1, 2015
 * Description: Class file for the data points returned by the device
 */
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

    public void setInstanceData(String datatext) {
        /*Gather the bluetooth data here*/
    }


}
