package naddateam.truform.ExerciseClasses;

import naddateam.truform.Calibration;
import naddateam.truform.Exercise;

/**
 * Created by Ben on 2/10/2015.
 */
public class UserAddedExercise extends Exercise {
    private String name;
    private int targetSets;
    private int targetReps;
    private String formLocation;
    private static Calibration initialPosition; //One calibration for all instances

    public UserAddedExercise(int targetSets, int targetReps, String name){
        this.targetReps = targetReps;
        this.targetSets = targetSets;
        this.name = name;
    }

    public void setFormLocation(String location) {
        this.formLocation = location;
    }

}