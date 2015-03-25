/**
 * CIS3760
 * Naddateam Truform
 * UserAddedExercise.java
 * Author: Benjamin Baird
 * Last Modified March 1, 2015
 * Description: Custom exercises created by the workout data will be stored here
 */

package naddateam.truform.ExerciseClasses;

import naddateam.truform.functionality.Calibration;

/**
 * Created by Ben on 2/10/2015.
 */
public class UserAddedExercise extends Exercise {
    private String name;
    private int completedSets;
    private int completedReps;
    private String formLocation;
    private static Calibration initialPosition; //One calibration for all instances

    public UserAddedExercise(int completedSets, int completedReps, String name){
        this.completedReps = completedReps;
        this.completedSets = completedSets;
        this.name = name;
    }

    public void setFormLocation(String location) {
        this.formLocation = location;
    }

}
