package naddateam.truform.functionality;

import android.widget.Toast;

import java.util.ArrayList;

import naddateam.truform.GUI.GUI.NavMenuItems.GetDataNav;
import naddateam.truform.functionality.InstanceData;
import android.widget.ListView;
import naddateam.truform.R;

/**
 * CIS3760
 * Naddateam Truform
 * ExerciseAnalysis.java
 * Author: Benjamin Baird and Erik Hoffman
 * Last Modified March 1, 2015
 * Description: Analyzing of data from device will be performed here.
 *
 */

public class ExerciseAnalysis {
    private static int numGoodReps;
    public static ArrayList<Integer> form = new ArrayList<Integer>();


    //private ArrayList<InstanceData> dataPoints = new ArrayList<InstanceData>();

    /**
     * Begins tracking of users' motions0
     */
    public void beginTracking(){
        //Initialize bluetooth tracking
    }

    /**
     * Cancels tracking of an exercise
     */
    public void abortExercise () {
        // Stop bluetooth tracking
    }

    /**
     * Checks whether the users form is correct based off an algorithm
     */
    public void analyzeForm(ArrayList<InstanceData> dataPoints, int numReps) {


        /*Constants*/
        int LOWER_BOUND = 64;
        int UPPER_BOUND = 85;
        int i = 0;
        int j = 0;
        /*Flags*/
        int goingUp = 0;

        int[] numZeroes = null;

        double[] up[];
        double[] down[];

        double prevX = 0;
        double prevY = 0;
        double prevZ = 0;

        double accelX = 0;
        double accelY = 0;
        double accelZ = 0;
        double gyroX = 0;
        double gyroY = 0;
        double gyroZ = 0;
        double startingAccelX = 0;
        double startingAccelY = 0;
        double totalGyroZUp = 0;
        double totalGyroZDown = 0;
        int type = 0;
        InstanceData instance = new InstanceData();

        while((i < 50) || (j < numReps)) {
            instance = dataPoints.get(i);
            i++;
            type = instance.getType();
            if (type == 0) {
                accelX = instance.getX();
                accelY = instance.getY();
                accelZ = instance.getZ();
            }
            else {
                gyroX = instance.getX();
                gyroY = instance.getY();
                gyroZ = instance.getZ();
            }

            if ((i % 2 == 0) && (i > 0)) /*On all even values (besides 0) we will have the accel and gyro data at a given time*/ {
                if ((gyroZ > 1) && (goingUp == 0)) { /*If you start to move upwards*/
                    goingUp = 1; /*Set the flag for going upwards*/
                    totalGyroZUp += gyroZ;
                }
                else if ((gyroZ > 1) && (goingUp == 1)) { /*If you continue to go upwards*/
                    totalGyroZUp += gyroZ;

                }
                else if ((gyroZ < -1) && (goingUp == 1)) { /*If you begin to go down*/
                    up[j] = totalGyroZUp; /*Now have the data for the up half of the curl, store*/
                    goingUp = 0;
                    totalGyroZDown += gyroZ;
                }
                else if ((gyroZ < -1) && (goingUp == 0)) { /*If you continue to go downwards*/
                    totalGyroZDown += gyroZ;
                }
                else { /*Otherwise the user is not really moving*/
                    if (goingUp == 1) {
                        //numZeroes[j] += 1;
                    }
                    else /*If you are at the bottom of the curl*/ {
                        if (j > 0) /*If you are on a higher rep than the first (you can wait before going it's fine bro)*/ {
                            //numZeroes[j] += 1;
                            down[j] = totalGyroZDown;
                            j++;
                            //System.out.println("Rep completed, totalGyroUp: " + totalGyroZUp + " totalGyroDown: " + totalGyroZDown);
                        }
                    }
                }


            }

        }

        /*Now we have the arrays of gyroZ data and can examine the curls*/
        /*Reference:
        1 is good form for a rep
        0 is somewhat close form (not good)
        -1 is bad form
        -2 is too long of a rest
          */
        i = 0;
        for(i = 0; i<numReps; i++)
        {
            if(numZeroes[j] > 3)
            {
                this.form.add(-2);
            }
            else if((up[i] >= LOWER_BOUND) && (up[i] <= UPPER_BOUND) && (down[i] >= LOWER_BOUND) && (down[i] <= UPPER_BOUND))
            {
                this.form.add(1);
            }
            else if((up[i] > 40) && (up[i] < LOWER_BOUND) && (down[i] > 40) && (down[i] < LOWER_BOUND))
            {
                this.form.add(0);
            }
            else
            {
                this.form.add(-1);
            }
        }

        this.numGoodReps = j;

    }


    /**
     * Estimates how many calories were burned performing an exercise
     */
    public void caloriesBurned() {

    }

    public int getNumReps() {
        return 0;
    }

    public int getNumSets() {
        return 0;
    }

}
