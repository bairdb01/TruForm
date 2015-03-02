/**
 * CIS3760
 * Naddateam Truform
 * Workout0.java
 * Author: Benjamin Baird
 * Last Modified March 1, 2015
 * Description: Controller for the workout page (displays all exercises for a workout)
 *              Allows navigation to each exercise
 */


package naddateam.truform.GUI.GUI.workouts;


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

import naddateam.truform.GUI.GUI.GenericExercise;
import naddateam.truform.R;

public class Workout0 extends ActionBarActivity implements AdapterView.OnItemClickListener{
    ListView lv;
    static String workoutName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Retrieving data passed from previous activity
        Bundle variables = getIntent().getExtras();
        if (variables != null) {
            workoutName = variables.getString("workoutName");
        }
        setTitle(workoutName);
        String[] workout = {"Empty"};

        if (workoutName.equals("Legs")) {
            workout = getResources().getStringArray(R.array.Legs);
        } else if (workoutName.equals("Back And Biceps")) {
            workout = getResources().getStringArray(R.array.BackAndBiceps);
        } else if (workoutName.equals("Chest And Triceps")) {
            workout = getResources().getStringArray(R.array.ChestAndTriceps);
        } else if (workoutName.equals("Shoulders")) {
            workout = getResources().getStringArray(R.array.Shoulders);
        }
        setContentView(R.layout.activity_workout0);
        lv = (ListView)findViewById(R.id.listView0);
//        ArrayAdapter<String> itemsAdapter =
//                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,workout);
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,workout));
        lv.setOnItemClickListener(this);
    }

    @Override
    /**
     * Opens the exercise screen
     */
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(getApplicationContext(), "Pressed" + position, Toast.LENGTH_LONG).show();
        String exerciseName;
//        switch(position){
//            case(0):

                // Opens the exercise
                Intent exercise0 = new Intent(this ,GenericExercise.class);
                exerciseName = lv.getItemAtPosition(position).toString();
                exercise0.putExtra("exName",exerciseName); // Pass data to next activity
                startActivity(exercise0);

//                break;
//            case(1):
//                Intent exercise1 = new Intent(this ,GenericExercise.class);
//                exerciseName = lv.getItemAtPosition(0).toString();
//                exercise1.putExtra("exName",exerciseName); // Pass data to next activity
//                startActivity(exercise1);
//                break;
//            case(2):
//                Intent exercise2 = new Intent(this ,GenericExercise.class);
//                exerciseName = lv.getItemAtPosition(0).toString();
//                exercise2.putExtra("exName",exerciseName); // Pass data to next activity
//                startActivity(exercise2);
//                break;
//            case(3):
//                Intent exercise3 = new Intent(this ,GenericExercise.class);
//                exerciseName = lv.getItemAtPosition(0).toString();
//                exercise3.putExtra("exName",exerciseName); // Pass data to next activity
//                startActivity(exercise3);
//                break;
//        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_workout0, menu);
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

}
