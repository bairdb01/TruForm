/**
 * CIS3760
 * Naddateam Truform
 * Exercises.java
 * Author: Benjamin Baird
 * Last Modified March 1, 2015
 * Description: Stores data on all exercises to use in the app
 */

package naddateam.truform.ExerciseClasses;

import java.util.HashMap;
import java.util.Map;

import naddateam.truform.ExerciseClasses.ChestPress;
import naddateam.truform.ExerciseClasses.Exercise;
import naddateam.truform.ExerciseClasses.HammerCurls;
import naddateam.truform.ExerciseClasses.HamstringCurls;
import naddateam.truform.ExerciseClasses.UserAddedExercise;


/**
 * Created by Ben on 2/4/2015.
 */
public class Exercises {
    private static HashMap<String,Exercise> allExercises; // Stores exercises, Key is the exercise name
    private String curExercise; //Current exercise selected?

    /**
     * Shows all the exercises in the system
     * @return HashMap which contains all the exercises available
     */
    public HashMap<Exercise,String> allExerciseNames() {
        // Iterate through map of all exercises
       for (Map.Entry<String, Exercise> entry : allExercises.entrySet()) {
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
//        String formLocation = "";
        int targetReps = 0;
        int targetSets = 0;

        UserAddedExercise newExercise = new UserAddedExercise(targetSets, targetReps, name);
//        newExercise.setFormLocation();
        this.allExercises.put(name, newExercise);

        return 0;
    }

    /**
     * Grabs an exercise object out of the list of all exercises, given a exercise name
     * @param nameEx name of exercise you want
     * @return Exercise object with name matching the one given
     */
    public Exercise getExercise(String nameEx) {
        return this.allExercises.get(nameEx);
    }

    /**
     * Factory method that creates Exercise objects
     * @return a new Exercise object
     */
    public Exercise createExercise(String name, int reps, int sets) {
        Exercise createdEx = new Exercise();
        switch(name) {
            case("ChestPress"):
                createdEx = new ChestPress(reps,sets);
                break;
            case("HammerCurls"):
                createdEx = new HammerCurls(reps, sets);
                break;
            case("HamstringCurls"):
                createdEx = new HamstringCurls(reps,sets);
                break;
            case("UserAddedExercise"):
                createdEx = new UserAddedExercise(reps,sets, name);
                break;
        }

        return createdEx;
    }
}
