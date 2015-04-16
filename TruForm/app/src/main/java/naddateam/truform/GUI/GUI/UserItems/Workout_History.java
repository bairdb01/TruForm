package naddateam.truform.GUI.GUI.UserItems;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;

import naddateam.truform.GUI.GUI.DataBase;
import naddateam.truform.R;


public class Workout_History extends ActionBarActivity  implements AdapterView.OnItemClickListener{

    ListView lv;
    String workoutName;
    ArrayList<workoutInfo> info = new ArrayList<workoutInfo>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout__history);
        lv = (ListView)findViewById(R.id.exercise_history);
        int i, j;

        TrackStatsNav trackStatsNav = new TrackStatsNav();

        //Retrieving data passed from previous activity
        Bundle variables = getIntent().getExtras();
        if (variables != null) {
            workoutName = variables.getString("workoutName");
        }
        setTitle(workoutName);
        info = trackStatsNav.getInfo();

        double[] formPercent = new double[info.size()];
        int[] reps = new int[info.size()];
        String Name = new String();

        for(i = 0; i < info.size(); i++)
        {
            String[] form = info.get(i).getForm().split("_");
            for(j = 0; j < form.length; j++)
            {
                if(form[j].equals("1"))
                {
                    formPercent[i] = formPercent[i] + 1;
                }
                else if(form[j].equals("2"))
                {
                    formPercent[i] = formPercent[i] + .5;
                }
                else if(form[j].equals("3"))
                {
                    formPercent[i] = formPercent[i] + 0;
                }
            }
            formPercent[i] = (formPercent[i]/((double)form.length)) * 100;
            reps[i] = form.length;
            Log.v("PERCENTS!: ", formPercent[i] + "");
        }

        DecimalFormat numberFormat = new DecimalFormat("#.00");

        //Read workout file
        //ArrayList allExercise = new ArrayList();
//        try {
//            File file = new File(getFilesDir(), workoutName);
//            BufferedReader bf = new BufferedReader(new FileReader(file));
//            //Get exercise data from file
//            String line = "test";
//            while ((line = bf.readLine()) != null) {
//                //if (line.matches(".*\\d+.*")){
//                    //Toast.makeText(this, "added line", Toast.LENGTH_SHORT).show();
//                    allExercise.add(line);
//                //}
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            Toast.makeText(this,"Error loading workout file",Toast.LENGTH_SHORT);
//        }

//        for (int i = 0; i < allExercise.size(); i ++) {
//            Toast.makeText(this, String.valueOf(allExercise.get(i)),Toast.LENGTH_SHORT).show();
//        }

        if(info.get(0).getEid().equals("1"))
        {
            Name = "Bicep Curls";
        }
        else if(info.get(0).getEid().equals("2"))
        {
            Name = "Lateral Raises";
        }
        else if(info.get(0).getEid().equals("3"))
        {
            Name = "Tricep Kickbacks";
        }

        // Populate list
        String [] exList = new String[info.size()];
        for(i = 0; i < info.size(); i++)
        {
            exList[i] = Name + ":\nReps: " + reps[i] + "  Weight: " + info.get(i).getWeight() + "  Form: " + numberFormat.format(formPercent[i]) + "%";
        }
        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.listviewcloud_text, exList));
        lv.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_workout__history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
