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

public class ExerciseAnalysis extends GetDataNav {
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
        int sittingBottom = 0;

        int[] numZeroes = null;

        int[] up = null;
        int[] down = null;

        ArrayList<Integer> gyroZArr = null;
        int gyroZ = 0;
        int totalGyroZUp = 0;
        int totalGyroZDown = 0;
        int type = 0;

        InstanceData instance;

        for(i = 0; i < dataPoints.size(); i++)
        {
            if(type == 1) {
                instance = dataPoints.get(i);
                gyroZArr.add(i, instance.getZ());
            }
        }

        while( (i < gyroZArr.size()) && ( j < numReps ) )
        {
            gyroZ = gyroZArr.get(i);


            if ((gyroZ > 1) && (goingUp == 0)) { /*If you start to move upwards*/
                if(totalGyroZDown < 0) /*If a downwards curl was just finished and not reset yet*/
                {
                    down[j] = totalGyroZDown*-1;
                    totalGyroZDown = 0;
                    if(sittingBottom == 0) /*Hasn't counted a complete rep (they began the new curl fast) yet so count it*/
                    {
                        j++;
                    }

                }
                sittingBottom = 0;
                goingUp = 1; /*Set the flag for going upwards*/
                totalGyroZUp += gyroZ;
            }
            else if ((gyroZ > 1) && (goingUp == 1)) { /*If you continue to go upwards*/
                totalGyroZUp += gyroZ;

            }
            else if((gyroZ >= -1) && (gyroZ <= 1) && (goingUp == 1)) /*Sitting at the top of the curl*/
            {
                numZeroes[j] += 1;
            }
            else if ((gyroZ < -1) && (goingUp == 1)) { /*If you begin to go down*/
                if(numZeroes[j] <= 3) /*Don't continue to count zeroes if you didn't wait long at the top*/
                    numZeroes[j] = 0;
                up[j] = totalGyroZUp; /*Now have the data for the up half of the curl, store*/
                totalGyroZUp = 0; /*And reset this for next*/
                goingUp = 0; /*No longer going up*/
                totalGyroZDown += gyroZ;
            }
            else if ((gyroZ < -1) && (goingUp == 0)) { /*If you continue to go downwards*/
                totalGyroZDown += gyroZ;
            }
            else { /*Otherwise the user is not really moving*/
                if((totalGyroZDown < 0) || (j > 0)) /*You've already completed a rep*/
                {
                    sittingBottom = 1;
                    if(totalGyroZDown < 0) /*You're at the bottom of the curl and it hasn't increased the rep yet
				subsequent zeroes found on new reps will not trigger a new rep, only the first one*/
                    {
                        numZeroes[j] += 1;
                        down[j] = totalGyroZDown*-1;
                        totalGyroZDown = 0;
                        goingUp = 0;
                        j++;
                    }
                    else /*It already reset it, so second+ zeroes*/
                    {
                        numZeroes[j-1] += 1;
                    }
                }

            }
            i++;
        }

        /*Now we have the arrays of gyroZ data and can examine the curls*/
        /*Reference:
        1 is good form for a rep
        0 is somewhat close form (not good)
        -1 is bad form
        -2 is too long of a rest
          */
        i = 0;
        for(i = 0; i<j; i++)
        {
            if(numZeroes[i] > 3)
            {
                this.form.add(i,-2);
            }
            else if((up[i] >= LOWER_BOUND) && (up[i] <= UPPER_BOUND) && (down[i] >= LOWER_BOUND) && (down[i] <= UPPER_BOUND))
            {
                this.form.add(i,1);
            }
            else if((up[i] < 40) || (down[i] < 40))
            {
                this.form.add(i,-1);
            }
            else if(((up[i] > 40) && (up[i] < LOWER_BOUND)) || ((down[i] > 40) && (down[i] < LOWER_BOUND)))
            {
                this.form.add(i,0);
            }
        }

        this.numGoodReps = j;
        return;

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
