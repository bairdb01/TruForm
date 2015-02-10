package naddateam.truform;

/**
 * Created by Ben on 2/4/2015.
 */

import java.util.HashMap;

public class UserSettings {
    private static String strictness;
    private static HashMap <Calibration, Integer> CalibratedSettings;
    private static HashMap <Exercise, Integer> loggedData;

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
    public void defaultSettings(){
        UserSettings();
    }

    /**
     * Adds a calibration for an exercise
     */
    public void addCalibration(){


    }
    /******************************************************
     * Setters
     ******************************************************/

    /**
    * Sets the level of strictness
    * @newStricness takes the requested strictness level
    */
    public void setStrictness(String newStrictness) {
        this.strictness = newStrictness;
    }

    /******************************************************
     * Getters
     ******************************************************/

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
    public HashMap<Exercise,Integer> getLoggedData(){
        return loggedData;
    }
}
