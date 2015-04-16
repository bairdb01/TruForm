package naddateam.truform.GUI.GUI.UserItems;

/**
 * Created by Robbie on 2015-04-16.
 */
public class workoutInfo {
    private String Form, Weight, Eid;

    public workoutInfo(String Form, String Weight, String Eid)
    {
        this.Form = Form;
        this.Weight = Weight;
        this.Eid = Eid;
    }

    public String getForm()
    {
        return Form;
    }

    public String getWeight()
    {
        return Weight;
    }

    public String getEid(){return Eid;}
}
