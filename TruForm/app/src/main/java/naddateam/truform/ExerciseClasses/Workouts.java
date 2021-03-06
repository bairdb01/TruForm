package naddateam.truform.ExerciseClasses;

/**
 * CIS3760
 * Naddateam Truform
 * Workouts.java
 * Author: Benjamin Baird
 * Last Modified March 1, 2015
 * Description: The central point of workouts. Contains all workouts created and returns workouts(s)
 *              on method calls.
 *
 *
 *
 *
 *                            Not used at the moment. For future use.
 */
import java.util.ArrayList;
import java.util.HashMap;

import naddateam.truform.ExerciseClasses.Exercise;
import naddateam.truform.ExerciseClasses.Exercises;

public class Workouts {
    private int selectedWorkout;
    private HashMap<String, ArrayList<Exercise>> userWorkouts;
    private Exercises allExercises;

    public Workouts(){
        this.selectedWorkout = 0;
        this.userWorkouts = new HashMap<>();
    }

    /**
     * Allows selection of a workout from all the workouts
     * @param choice the chosen workout's name
     * @return the chosen workout with all exercises
     */
    public ArrayList selectWorkout(String choice) {
        return userWorkouts.get(choice);
    }

    /**
     * Create a new workout that is saved. Add exercises, reps, sets
     * @return the new workout that was created
     */
    public ArrayList <Exercise> createWorkout() {
        ArrayList <Exercise> newExercises = new ArrayList<>();
        int numExercises = 0;


        // Goes through the GUI and adds the exercise to the workout
        for (int counter = 0; numExercises > counter; counter++) {
            // Check Exercise selected
            //GUI would return which exercises were selected **************************
            String exName = "Example";
            int sets = 0;
            int reps = 0;
            allExercises.getExercise(exName);

            // Check number of sets inputted
            sets = 1;   //************************ grab from gui

            // Check number of reps inputted
            reps = 1;   //*********************** Grab from gui

            // Add exercise to the workout list
            newExercises.add(allExercises.createExercise(exName,sets, reps));
        }

        return newExercises;
    }

    /**
     * Recommends the user some workouts based on popularity and their preferences
     * @return
     */
    public ArrayList<Exercise> recommendedWorkouts() {
        ArrayList <Exercise> recWorkouts = new ArrayList<>();


        return recWorkouts;
    }

    /**
     * Recommends the user some activites to try
     */
    public ArrayList<String> recommendedActivites () {
        ArrayList<String> recActivites = new ArrayList<>();
        // Do some analysis based on exercises, bmi, etc
        // Add activities to the list

        return recActivites;
    }
}
