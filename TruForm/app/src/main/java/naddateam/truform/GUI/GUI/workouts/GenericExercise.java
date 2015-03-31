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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import naddateam.truform.GUI.GUI.Bluetooth;
import naddateam.truform.GUI.GUI.BluetoothLeUart;
import naddateam.truform.functionality.CountDown;
import naddateam.truform.ExerciseClasses.Exercise;
import naddateam.truform.ExerciseClasses.Exercises;
import naddateam.truform.R;
import naddateam.truform.functionality.ExerciseAnalysis;

/**
 * Note to self: Currently overwrites last reps/sets/weight completed on exit of screen
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
    ArrayList <String> repsDone;
    ArrayList <String> weightDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Initialization of null variables
        exerciseName = "";
        exNumber = 0;
        workoutName = "";
        int completedReps = 0;
        int completedSets = 0;
        repsDone = new ArrayList<String>();
        weightDone = new ArrayList<String>();

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

            if (file.exists())
                Toast.makeText(this, "FILE EXISTS", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "NULL FILE", Toast.LENGTH_SHORT).show();

//            FileInputStream fis = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                Toast.makeText(this, line, Toast.LENGTH_LONG).show();
                //if (test.regionMatches(true,0,"rep",0, 3)) {
                    //reps.setValue(Integer.parseInt(test.replaceAll("[^0-9]+", "")));
                if (line.regionMatches(0,"set",0,3)) {
                    sets.setText(line.replaceAll("[^0-9]+","")); //Need to get this to match
                    currentSet = Integer.parseInt(line.replaceAll("[^0-9]+",""));
                } else if (line.regionMatches(0,"weight",0,6)){
                    weight.setText(line.replaceAll("[^0-9]+",""));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(this, "End of Creation", Toast.LENGTH_SHORT).show();
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
        Bluetooth ble = new Bluetooth();
        ExerciseAnalysis exerciseAnalysis = new ExerciseAnalysis();
        BluetoothLeUart Comm = ble.getmService();
        byte[] value;
        String message = "G";
        String btnVal;
        switch(v.getId()) {
            case(R.id.startBut):
                //Toast.makeText(getApplicationContext(),"Start",Toast.LENGTH_SHORT);
                // Putting abort button in place of start button
                startTrack.setEnabled(false);
                startTrack.setVisibility(View.INVISIBLE);
                abortTrack.setEnabled(true);
                abortTrack.setVisibility(View.VISIBLE);
                finish.setEnabled(true);
                finish.setVisibility(View.VISIBLE);

                ble.dataArr.clear();
                value = message.getBytes();
                Comm.writeRXCharacteristic(value);
                //btnSR.setText("Stop");
                exerciseAnalysis.form.clear();

                break;
            case(R.id.finBut):
                //Toast.makeText(getApplicationContext(),"Fin",Toast.LENGTH_SHORT);
                currentSet++;
                sets.setText(String.valueOf(currentSet));
                finish.setEnabled(false);
                finish.setVisibility(View.INVISIBLE);
                startTrack.setEnabled(true);
                startTrack.setVisibility(View.VISIBLE);
                abortTrack.setEnabled(false);
                abortTrack.setVisibility(View.INVISIBLE);

                //Grab the weights, sets, reps here
                String repString = Integer.toString(reps.getValue());
                repsDone.add(repString);
                weightDone.add(weight.getText().toString());

                message = "N";
                value = message.getBytes();
                Comm.writeRXCharacteristic(value);
                //btnSR.setText("Receive");
                exerciseAnalysis.analyzeForm(ble.dataArr, 5);

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
        String filename = workoutName + "-exercise" + exNumber;
        String text = "text";

        try {
            File file;
            FileWriter fileWriter;
            file = new File(getCacheDir(), filename) ;
            fileWriter = new FileWriter(file);

            // Writes exercise name, sets, reps, and weight to cache file
            fileWriter.write(exerciseName);
            fileWriter.write("\r\n");
            fileWriter.write("set="+sets.getText());
            fileWriter.write("\r\n");

            // Writes all the reps per set
            for (int i = 0; i < repsDone.size(); i++) {
                fileWriter.write("reps="+repsDone.get(i));
                fileWriter.write("\tweight="+weightDone.get(i));
                fileWriter.write("\r\n");
            }

            // Writes all the weights per set
//            for (int i = 0; i < weightDone.size(); i++) {
//                fileWriter.write("weight="+weightDone.get(i));
//                fileWriter.write("\r\n");
//            }

            fileWriter.flush();
            fileWriter.close();
            //Toast.makeText(this,"Cached",Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }


        super.onBackPressed();
    }
}
