package naddateam.truform.functionality;

/**
 * Created by Ben on 2/4/2015.
 */
public class ExerciseAnalysis {
    private double goodForm;
    private int numReps;
    private int numSets;

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
}
