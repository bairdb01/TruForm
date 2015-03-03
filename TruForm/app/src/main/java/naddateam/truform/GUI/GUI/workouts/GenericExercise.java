/**
 * CIS3760
 * Naddateam Truform
 * GenericExercise.java
 * Author: Benjamin Baird
 * Last Modified March 1, 2015
 * Description: Controls the exercise pages that are shown
 *              Includes rest timers, start button, abort, etc
 */

package naddateam.truform.GUI.GUI.workouts;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import naddateam.truform.functionality.CountDown;
import naddateam.truform.ExerciseClasses.Exercise;
import naddateam.truform.ExerciseClasses.Exercises;
import naddateam.truform.R;

public class GenericExercise extends ActionBarActivity implements View.OnClickListener{
    Button startTrack;
    Button abortTrack;
    Button finish;
    TextView sets;
    NumberPicker reps;
    EditText weight;
    int currentSet;
    Exercise curExercise;
    CountDown restTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String exerciseName = "";
        int targetReps = 0;
        int targetSets = 0;

        //Retrieving data passed from previous activity
        Bundle variables = getIntent().getExtras();
        if (variables != null) {
           exerciseName = variables.getString("exName");
        }

        //Setting the countdown timer
        restTimer = new CountDown();
        // Creates an exercise object to change the title, track sets and reps, etc
        Exercises exCreator = new Exercises();
        curExercise = exCreator.createExercise(exerciseName,targetReps,targetSets);
        setTitle(exerciseName);
        currentSet = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_exercise);

        //Assign buttons to variables
        startTrack = (Button) findViewById(R.id.startBut);
        abortTrack = (Button) findViewById(R.id.abortBut);
        finish = (Button) findViewById(R.id.finBut);
        sets = (TextView) findViewById(R.id.textSets);
        reps = (NumberPicker) findViewById(R.id.numberPickerReps);
        weight = (EditText) findViewById(R.id.numberWeight);

        //set listeners for buttons
        startTrack.setOnClickListener(this);
        abortTrack.setOnClickListener(this);
        finish.setOnClickListener(this);
        reps.setOnClickListener(this);


        //Setting the numberPickers' range
        reps.setMinValue(1);
        reps.setMaxValue(99);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_generic_exercise, menu);
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
    /**
     * Makes the start/abort/finish buttons perform stuff
     */
    public void onClick(View v) {
        switch(v.getId()) {
            case(R.id.startBut):
                //Toast.makeText(getApplicationContext(),"Start",Toast.LENGTH_SHORT);
                // Putting abort button in place of start button
                startTrack.setEnabled(false);
                startTrack.setVisibility(View.INVISIBLE);
                abortTrack.setEnabled(true);
                abortTrack.setVisibility(View.VISIBLE);
                break;
            case(R.id.finBut):
                //Toast.makeText(getApplicationContext(),"Fin",Toast.LENGTH_SHORT);
                currentSet++;
                sets.setText(String.valueOf(currentSet));
                //Grab the weights and sets here

                weight.setText(String.valueOf(0));
                reps.setValue(reps.getMinValue());
                restTimer.startTimer((TextView) findViewById(R.id.restTime));
                break;
            case(R.id.abortBut):
                //Toast.makeText(getApplicationContext(),"Abort",Toast.LENGTH_SHORT);
                //Putting start button in place of abort button
                abortTrack.setEnabled(false);
                abortTrack.setVisibility(View.INVISIBLE);
                startTrack.setEnabled(true);
                startTrack.setVisibility(View.VISIBLE);
                break;
        }

    }
}