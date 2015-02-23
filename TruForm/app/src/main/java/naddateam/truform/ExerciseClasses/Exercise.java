package naddateam.truform.ExerciseClasses;

import naddateam.truform.functionality.Calibration;

/**
 * Created by Ben on 2/4/2015.
 */
public class Exercise {
    private String name;
    private int targetSets;
    private int targetReps;
    private int maxWeight;
    private String formLocation;
    private static Calibration initialPosition; //One calibration for all instances

    public Exercise() {
        this.name = "NotAName!";
        targetReps = 0;
        targetReps = 0;
    }

    /**
     * Allows the user to set the number of reps for an exercise
     */
    public void setReps(int newReps){
        this.targetReps = newReps;
    }

    /**
     * Allows the user to set the number of sets for an exercise
     */
    public void setSets(int newSets){
        this.targetSets = newSets;
    }

    /**
     * Change the calibration for the exercise
     */
    public void changeCalibration(){
        this.initialPosition.calibrate();
    }

    /**
     * Shows a visual representation of the exercise
     */
    public void showForm(){
        // would return location of proper form img/video
        // gui displays it
    }

    /**
     * Clears the calibration data for an exercise
     */
    public void clearCalibration() {
        this.initialPosition.clearCalibrations();
    }

    public Object clone(Exercise cloneInto) throws CloneNotSupportedException {
        cloneInto.setReps(this.targetReps);
        cloneInto.setSets(this.targetSets);
        cloneInto.changeName(this.name);
        //copyCalibration(cloneInto);
        return super.clone();
    }

    /**
     * Gets the name of the exercise
     * @return the name of the exercise
     */
    public String getName() {
        return this.name;
    }

    /**
     * Allows an exercise to have it's name changed
     * @param newName the new name to change the exercise to
     */
    public void changeName (String newName){
        this.name = newName;
    }

//    public void copyCalibration(Exercise newEx){

//    }

    /**
     * Changes the users maximum weight for an exercise
     * @param newMaxWeight the new maximum weight that was achieved
     */
    public void setMaxWeight(int newMaxWeight) {
        this.maxWeight = newMaxWeight;
    }

}
