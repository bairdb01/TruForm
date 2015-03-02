package naddateam.truform.functionality;

import java.util.ArrayList;

import naddateam.truform.ExerciseClasses.Exercise;

/**
 * CIS3760
 * Naddateam Truform
 * DatabaseAccess.java
 * Author: Benjamin Baird
 * Last Modified March 1, 2015
 * Description: Will allow connections to a database and transfers to store data remotely.
 */

public class DatabaseAccess {
    private String dbLocation;

    public ArrayList<Exercise> getWorkouts() {
        ArrayList<Exercise> allWorkouts = new ArrayList<>();
        return allWorkouts;
    }

    /**
     * Sends the newly created workout to the database
     * @return Error Message if there was an error
     */
    public String sendNewWorkout() {

        return "Workout sent to the database";
    }


    /**
     * Sends the users' stats on each workout to the database
     * @return Error Message if there was an error
     */
    public String sendStats() {
        return "Stats sent to database";
    }
}
