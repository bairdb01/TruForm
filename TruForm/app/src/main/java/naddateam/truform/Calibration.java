package naddateam.truform;

/**
 * Created by Ben on 2/4/2015.
 */


public class Calibration {
    private int accelX;
    private int accelY;
    private int accelZ;
    private int gyroX;
    private int gyroY;
    private int gyroZ;

    public void Calibration() {
        accelZ = 0;
        accelY = 0;
        accelX = 0;
        gyroX = 0;
        gyroZ = 0;
        gyroY = 0;
    }

    public void setCalibration(int accelX, int accelY, int accelZ, int gyroX, int gyroY, int gyroZ){
        this.accelX = accelX;
        this.accelY = accelY;
        this.accelZ = accelZ;
        this.gyroX = gyroX;
        this.gyroY = gyroY;
        this.gyroZ = gyroZ;
    }


}
