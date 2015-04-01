package naddateam.truform.functionality;

/**
 * CIS3760
 * Naddateam Truform
 * UserSettings.java
 * Author: Benjamin Baird
 * Last Modified March 1, 2015
 * Description: Manages settings that the user has selected as preferences.
 *              Stores all calibration data and exercise logs
 *
 *
 *
 *
 *              Not used at the moment. For future use.
 *
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
