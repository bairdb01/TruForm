package naddateam.truform.functionality;

import java.util.ArrayList;

/**
 * CIS3760
 * Naddateam Truform
 * Calibration.java
 * Author: Benjamin Baird
 * Last Modified March 1, 2015
 * Description: Stores starting position for an exercise.
 */

public class Calibration {
    private double accelX;
    private double accelY;
    private double accelZ;
    private double gyroX;
    private double gyroY;
    private double gyroZ;

    /**
     * Constructor
     */
    public void CalibrationSet() {
        accelZ = 0;
        accelY = 0;
        accelX = 0;
        gyroX = 0;
        gyroZ = 0;
        gyroY = 0;
    }

    public void calibrate() {
        String store = "";
        ArrayList <Double> calibrations = new ArrayList<>();

        // Receive bluetooth data
        calibrations = getBTData();

        // Store Calibrations? (probably won't need if statement, will be done by event handler?)
        if ( store == "Yes")
            saveCalibration(calibrations);

    }

    /**
     * Sets the initial position of the user
     * @param calis Calibration data (accelerometer + gyroscope)
     * @return 0 if success
     */
    public int saveCalibration(ArrayList<Double> calis) {
        try {
            this.accelX = calis.get(0);
            this.accelY = calis.get(1);
            this.accelZ = calis.get(2);
            this.gyroX = calis.get(3);
            this.gyroY = calis.get(4);
            this.gyroZ = calis.get(5);
        } catch (Exception e) {
            return 1;
        }
        return 0;
    }


    public ArrayList<Double> getBTData(){
        ArrayList<Double> btData = new ArrayList<>();

        // Call BT here and receive a bunch of numbers



        // Put in the numbers
        btData.add(new Double(2.1010));

        return btData;
    }

    public void clearCalibrations() {
        this.CalibrationSet();
    }
}