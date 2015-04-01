package naddateam.truform.GUI.GUI.UserItems;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import naddateam.truform.GUI.GUI.workouts.GenericExercise;
import naddateam.truform.R;

/**
 * CIS3760
 * Naddateam Truform
 * TrackStatsNav.java
 * Author: Benjamin Baird
 * Allows the user to be able to view their past workouts
 *
 *
 *
 *
 *
 * Bugs: Currently crashes when item is clicked, sends a null position or something of that sort
 */
public class TrackStatsNav extends ActionBarActivity implements AdapterView.OnItemClickListener{
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trackstats_layout);

        ArrayList pastWorkouts = new ArrayList();

        // Grab file names and add them to the list of past workouts
        try {
            File dirFiles = getFilesDir();
            for (String filename : dirFiles.list()) {
                if (filename.equals("userDetails") == false)
                    pastWorkouts.add(filename);
            }

            String[] woList = new String[pastWorkouts.size()];
            pastWorkouts.toArray(woList);

            lv = (ListView) findViewById(R.id.pastWorkouts);
            lv.setAdapter(new ArrayAdapter<String>(this, R.layout.listviewcloud_text, woList));
            lv.setOnItemClickListener(this);
        } catch (Exception e) {
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_trackstatsnav, menu);
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

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Opens the exercise
        Toast.makeText(this, String.valueOf(position) ,Toast.LENGTH_SHORT).show();
        Intent workout = new Intent(this , Workout_History.class);
        String workoutName = lv.getItemAtPosition(position).toString();
        workout.putExtra("workoutName",workoutName); //Sends workout name
        startActivity(workout);
    }
}
