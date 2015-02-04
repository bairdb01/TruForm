package naddateam.truform;

/**
 * Created by Ben on 2/4/2015.
 */

import java.util.ArrayList;

public class Workouts {
    private int selectedWorkout;
    private ArrayList<String> userWorkouts;

    public String chooseWorkout(int choice) {
        return null;
    }

    /**
     * Create a new workout that is saved. Add exercises, reps, sets
     * @return the new workout that was created
     */
    public ArrayList <Exercise> createWorkout() {
        ArrayList <Exercise> newExercises = new ArrayList<>();
        int numExercises = 0;

        for (int counter = 0; numExercises > counter; counter++) {
            // Check Exercise selected

            // Check number of sets inputted

            // Check number of reps inputted

        }

        return newExercises;
    }

    /**
     * Recommends the user some workouts based on popularity
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
