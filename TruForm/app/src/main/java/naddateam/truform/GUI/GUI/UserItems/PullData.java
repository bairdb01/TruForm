package naddateam.truform.GUI.GUI.UserItems;

/**
 * Created by Erik on 4/16/2015.
 */
public class PullData {
    private String workout;
    private String eid;

    public PullData(String workoutset, String eidset)
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
