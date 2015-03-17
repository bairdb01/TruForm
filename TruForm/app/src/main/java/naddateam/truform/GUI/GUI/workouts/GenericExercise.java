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

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import naddateam.truform.functionality.CountDown;
import naddateam.truform.ExerciseClasses.Exercise;
import naddateam.truform.ExerciseClasses.Exercises;
import naddateam.truform.R;

/**
 * Note to self: need to store reps individually for sets
 */
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
    int exNumber;
    String workoutName;
    String exerciseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Initialization of null variables
        exerciseName = "";
        exNumber = 0;
        workoutName = "";
        int completedReps = 0;
        int completedSets = 0;

        //Retrieving data passed from previous activity
        Bundle variables = getIntent().getExtras();
        if (variables != null) {
            exerciseName = variables.getString("exName");
            exNumber = variables.getInt("exNum");
            workoutName = variables.getString("workoutName");
        }

        //Setting the countdown timer
        restTimer = new CountDown();
        // Creates an exercise object to change the title, track sets and reps, etc
        Exercises exCreator = new Exercises();
        curExercise = exCreator.createExercise(exerciseName,completedReps,completedSets);
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

        // Checking if previous cache data is available in case workout incomplete

        try {
            // Finds all exercises files and writes to the workouts file
            File file = new File(getCacheDir(), workoutName + "-exercise" + exNumber);
            //FileInputStream fis = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            if (file == null)
                Toast.makeText(this, "NULL FILE", Toast.LENGTH_SHORT).show();

            String test = "";
            while ((test = bufferedReader.readLine()) != null) {
                if (test.regionMatches(true,0,"reps",0, 4)) {
                    reps.setValue(Integer.parseInt(test.replaceAll("[^0-9]+","")));
                } else if (test.regionMatches(0,"sets",0,4)) {
                    sets.setText(test.replaceAll("[^0-9]+",""));
                } else if (test.regionMatches(0,"weight",0,6)){
                    weight.setText(test.replaceAll("[^0-9]+",""));
                }
                Toast.makeText(this, test, Toast.LENGTH_SHORT).show();
            }




        } catch (Exception e) {
            e.printStackTrace();
        }
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
                //Grab the weights, sets, reps here


                //Reset Reps and start the rest time
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

                // Just goes back to previous screen without saving anything
                super.onBackPressed();
                break;
        }
    }

    @Override
    /**
     * Caches the data of the current exercise when back button is pressed
     */
    public void onBackPressed() {
        // Stores data in a file
        String filename = workoutName + "-exercise"+ Integer.toString(exNumber);
        String text = "text";

        try {
            File file;
            FileWriter fileWriter;
            file = File.createTempFile(filename,null) ;
            fileWriter = new FileWriter(file);

            // Writes exercise name, sets, reps, and weight to cache file
            fileWriter.write(exerciseName);
            fileWriter.write((System.getProperty( "line.separator" )));
            fileWriter.write("set="+sets.getText());
            fileWriter.write((System.getProperty( "line.separator" )));
            fileWriter.write("rep="+reps.getValue());
            fileWriter.write((System.getProperty( "line.separator" )));
            fileWriter.write("weight="+weight.getText());
            fileWriter.write((System.getProperty( "line.separator" )));
            fileWriter.flush();
            fileWriter.close();
            Toast.makeText(this,"Cached",Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }


        super.onBackPressed();
    }
}
