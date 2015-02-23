package naddateam.truform.functionality;

/**
 * Created by Ben on 2/4/2015.
 */

import java.util.HashMap;

import naddateam.truform.ExerciseClasses.Exercise;
import naddateam.truform.ExerciseClasses.Exercises;

public class UserSettings {
    private static String strictness;
    private static HashMap <Integer, Calibration> CalibratedSettings;
    private static HashMap <Integer, Exercise> loggedData;
    private static Exercises exercises;

    /**
     * Contructor
     */
    public void UserSettings() {
        this.strictness = "";
        this.CalibratedSettings = new HashMap<>();
        this.loggedData = new HashMap<>();
    }

    /**
     * Clears the users calibrations and logged data
     */
    public void clearAllData(){
        UserSettings();
    }

    /**
    * Sets the level of strictness
    * @newStricness takes the requested strictness level
    */
    public void setStrictness(String newStrictness) {
        this.strictness = newStrictness;
    }

    /**
     * Getter for strictness level
     * @return String containing the strictness level
     */
    public String getStrictness() {
        return this.strictness;
    }

    /**
     * Gets the users analysis' on exercises
     * @return loggedData which contains previous analysis' of exercises
     */
    public HashMap<Integer, Exercise> getLoggedData(){
        return loggedData;
    }
}
