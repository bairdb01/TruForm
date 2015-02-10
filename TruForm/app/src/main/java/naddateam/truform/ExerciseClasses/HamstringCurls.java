package naddateam.truform.ExerciseClasses;

import naddateam.truform.Calibration;
import naddateam.truform.Exercise;

/**
 * Created by Ben on 2/4/2015.
 */
public class HamstringCurls extends Exercise{
    private String name;
    private int targetSets;
    private int targetReps;
    private String formLocation;
    private static Calibration initialPosition; //One calibration for all instances
    /**
     * Constructor!
     * @param reps target reps for the user to hit
     * @param sets target sets for the user to hit
     */
    public HamstringCurls(int reps, int sets){
        this.targetReps = reps;
        this.targetSets = sets;

        if (initialPosition == null)
            initialPosition = new Calibration();
    }


}