package naddateam.truform.ExerciseClasses;

import naddateam.truform.Calibration;
import naddateam.truform.Exercise;

/**
 * Created by Ben on 2/4/2015.
 */
public class ChestPress extends Exercise{
    int targetSets;
    int targetReps;
    private String formLocation;
    private static Calibration initialPosition; //One calibration for all instances

    /**
     * Constructor!
     * @param reps target reps for the user to hit
     * @param sets target sets for the user to hit
     */
    public ChestPress(int reps, int sets){
        this.targetReps = reps;
        this.targetSets = sets;

        if (initialPosition == null)
            initialPosition = new Calibration();
    }

    public void setReps(int newReps){
        this.targetReps = newReps;
    }

    public void setSets(int newSets){
        this.targetSets = newSets;
    }

    public void changeCalibration(){
        this.initialPosition.calibrate();
    }

    public void showForm(){

    }
}