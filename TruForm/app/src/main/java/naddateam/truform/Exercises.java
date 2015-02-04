package naddateam.truform;

import java.util.HashMap;
import java.util.Map;

import naddateam.truform.ExerciseClasses.UserAddedExercise;


/**
 * Created by Ben on 2/4/2015.
 */
public class Exercises {
    private HashMap<Exercise,String> allExercises; // Stores exercises, Key is the exercise name
    private String curExercise; //Current exercise selected?

    /**
     * Shows all the exercises in the system
     * @return HashMap which contains all the exercises available
     */
    public HashMap<Exercise,String> allExerciseNames() {
        // Iterate through map of all exercises
       for (Map.Entry<Exercise, String> entry : allExercises.entrySet()) {
           System.out.println("Key = " + entry.getKey() + " Value = " + entry.getValue());
        }

        return null;
    }

    /**
     * Adds a new exercise to the list of all exercises
     * @return positive integer if an error is returned
     */
    public int addExercise () {
        String name = "Ex.Name";
        String formLocation = "";
        int targetReps = 0;
        int targetSets = 0;

        UserAddedExercise newExercise = new UserAddedExercise(targetSets, targetReps, formLocation, name);
        this.allExercises.put(newExercise, name);

        return 0;
    }
}
