package naddateam.truform.functionality;

import android.util.Log;
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
    public static ArrayList<String> form = new ArrayList<String>();

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
        int UPPER_BOUND = 90;
        int i = 0;
        int j = 0;
        /*Flags*/
        int goingUp = 0;
        int sittingBottom = 0;
        int zeroes = 0;

        ArrayList<Integer> numZeroesBottom = new ArrayList<Integer>();
        ArrayList<Integer> numZeroesTop = new ArrayList<Integer>();

        ArrayList<Integer> up = new ArrayList<Integer>();
        ArrayList<Integer> down = new ArrayList<Integer>();

        ArrayList<Integer> gyroZArr = new ArrayList<Integer>();
        int gyroZ;
        int totalGyroZUp = 0;
        int totalGyroZDown = 0;

        InstanceData instance;

        for(i = 0; i < dataPoints.size(); i++)
        {
            if(dataPoints.get(i).getType() == 1) {
                instance = dataPoints.get(i);
                gyroZArr.add(instance.getZ());
            }
        }
        i = 0;
        while( (i < gyroZArr.size()) && ( j < numReps ) )
        {
            gyroZ = gyroZArr.get(i);


            if ((gyroZ > 1) && (goingUp == 0)) { /*If you start to move upwards*/
                if(totalGyroZDown < 0) /*If a downwards curl was just finished and not reset yet*/
                {
                    numZeroesBottom.add(zeroes);
                    zeroes = 0;
                    down.add(totalGyroZDown*-1);
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
                zeroes += 1;
            }
            else if ((gyroZ < -1) && (goingUp == 1)) { /*If you begin to go down*/
                //if(numZeroes[j] <= 3) /*Don't continue to count zeroes if you didn't wait long at the top*/
                    //numZeroes[j] = 0;
                numZeroesTop.add(zeroes);
                zeroes = 0;
                up.add(totalGyroZUp); /*Now have the data for the up half of the curl, store*/
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
                        //numZeroes[j] += 1;
                        zeroes += 1;
                        numZeroesBottom.add(zeroes);
                        down.add(totalGyroZDown*-1);
                        totalGyroZDown = 0;
                        goingUp = 0;
                        j++;
                    }
                    else /*It already reset it, so second+ zeroes*/
                    {
                        //numZeroes[j-1] += 1;
                        zeroes += 1;
                        numZeroesBottom.set(j-1,zeroes);
                    }
                }

            }
            i++;
        }

        int waitB = 0;
        int waitT = 0;
        /*Now we have the arrays of gyroZ data and can examine the curls*/
        /*Reference:
        4 is good form but there was too long of a wait at the bottom/top
        3 is good form but there was too long of a wait at the top of the curl
        2 is good form but there was too long of a wait at the bottom of the curl
        1 is good form for a rep
        10 is somewhat close form (not good)
        20 is somewhat close form but too long bott
        30 is somewhat close form but too long top
        40 is somewhat close form but too long both
        -1 is bad form
        -2 is bad form with too long at the bottom
        -3 is bad form with too long at the top
        -4 is bad form with too long at both
          */
        for(i = 0; i<j; i++)
        {
            waitB = 0;
            waitT = 0;
            if(numZeroesBottom.get(i) > 3)
            {
                waitB = 1;
            }
            if(numZeroesTop.get(i) > 3)
            {
                waitT = 1;
            }

            if((up.get(i) >= LOWER_BOUND) && (up.get(i) <= UPPER_BOUND) && (down.get(i) >= LOWER_BOUND) && (down.get(i) <= UPPER_BOUND))
            {
                if((waitB == 0) && (waitT == 0))
                    this.form.add("Good form.");
                else if((waitB == 1) && (waitT == 0))
                    this.form.add("Good form, but you waited too long at the bottom of the curl.");
                else if((waitB == 0) && (waitT == 1))
                    this.form.add("Good form, but you waited too long at the top of the curl.");
                else
                    this.form.add("Good form, but you waited too long at the bottom and the top of the curl.");
            }
            else if((up.get(i) < 50) || (down.get(i) < 50))
            {
                if((waitB == 0) && (waitT == 0))
                    this.form.add("Your form was bad, try lifting higher.");
                else if((waitB == 1) && (waitT == 0))
                    this.form.add("Your form was bad, and you waited too long at the bottom of the curl.");
                else if((waitB == 0) && (waitT == 1))
                    this.form.add("Your form was bad, and you waited too long at the top of the curl.");
                else
                    this.form.add("Your form was bad, and you waited too long at the bottom and the top of the curl.");
            }
            else if(((up.get(i) > 50) && (up.get(i) < LOWER_BOUND)) || ((down.get(i) > 50) && (down.get(i) < LOWER_BOUND)))
            {
                if((waitB == 0) && (waitT == 0))
                    this.form.add("Your form was close, try lifting higher.");
                else if((waitB == 1) && (waitT == 0))
                    this.form.add("Your form was close, and you waited too long at the bottom of the curl.");
                else if((waitB == 0) && (waitT == 1))
                    this.form.add("Your form was close, and you waited too long at the top of the curl.");
                else
                    this.form.add("Your form was close, and you waited too long at the bottom and the top of the curl.");
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
