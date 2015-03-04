package naddateam.truform.functionality;

import java.util.ArrayList;

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
    private double goodForm;
    private int numReps;
    private int numSets;

    private ArrayList<InstanceData> dataPoints = new ArrayList<InstanceData>();

    /**
     * Begins tracking of users' motions
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
    public void analyzeForm() {
        //Calculations etc...

        this.goodForm = 75.3;
    }

    /**
     * Says if an exercise was performed with good form
     * @return true if good form
     */
    public double isGoodForm() {
        return this.goodForm;
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
