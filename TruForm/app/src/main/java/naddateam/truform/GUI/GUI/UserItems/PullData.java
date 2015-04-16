package naddateam.truform.GUI.GUI.UserItems;

/**
 * Created by Erik on 4/16/2015.
 */
public class PullData {
    private static String workout;
    private static String eid;

    public void Constructor(String workoutset, String eidset)
    {
        this.workout = workoutset;
        this.eid = eidset;
    }
    public String getWorkout()
    {
        return this.workout;
    }

    public String getEid()
    {
        return this.eid;
    }
}
