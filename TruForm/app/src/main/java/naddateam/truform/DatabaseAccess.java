package naddateam.truform;

import java.util.ArrayList;

import naddateam.truform.ExerciseClasses.Exercise;

/**
 * Created by Ben on 2/4/2015.
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
