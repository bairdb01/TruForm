/**
 * CIS3760
 * Naddateam Truform
 * ChestPress.java
 * Author: Benjamin Baird
 * Last Modified March 1, 2015
 * Description: Stores data of chest press
 *              Allows navigation to each exercise
 *              Not used at the moment. For future use.
 */
package naddateam.truform.ExerciseClasses;

import naddateam.truform.functionality.Calibration;

/**
 * Created by Ben on 2/4/2015.
 */
public class ChestPress extends Exercise{
    private String name;
    private int completedSets;
    private int completedReps;
    private String formLocation;
    private static Calibration initialPosition; //One calibration for all instances

    /**
     * Constructor!
     * @param reps target reps for the user to hit
     * @param sets target sets for the user to hit
     */
    public ChestPress(int reps, int sets){
        this.completedReps = reps;
        this.completedSets = sets;
        this.name = "ChestPress";
        if (initialPosition == null) {
            initialPosition = new Calibration();

        }
    }


}