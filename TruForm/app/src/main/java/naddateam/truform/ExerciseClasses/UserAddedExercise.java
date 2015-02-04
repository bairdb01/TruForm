package naddateam.truform.ExerciseClasses;

import naddateam.truform.Calibration;
import naddateam.truform.Exercise;

/**
 * Created by Ben on 2/10/2015.
 */
public class UserAddedExercise extends Exercise {
    private int targetSets;
    private int targetReps;
    private String name;
    private String formLocation;
    Calibration initialPosition;

    public UserAddedExercise(int targetSets, int targetReps, String formLocation, String name){
        this.targetReps = targetReps;
        this.targetSets = targetSets;
        this.formLocation = formLocation;
        this.name = name;
    }

    public void setReps(int newReps){

    }

    public void setSets(int newSets){

    }

    public void changeCalibration(){

    }

    public void showForm(){

    }

}
