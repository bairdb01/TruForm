package naddateam.truform.GUI.GUI;

import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import naddateam.truform.R;

public class BicepCurl extends ActionBarActivity implements View.OnClickListener{
    Button startTrack;
    Button abortTrack;
    Button finish;
    NumberPicker sets;
    NumberPicker reps;
    NumberPicker weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bicep_curl);

        //Assign buttons to variables
        startTrack = (Button) findViewById(R.id.startBut);
        abortTrack = (Button) findViewById(R.id.abortBut);
        finish = (Button) findViewById(R.id.finBut);
        sets = (NumberPicker) findViewById(R.id.numberPickerSets);
        reps = (NumberPicker) findViewById(R.id.numberPickerReps);
        weight = (NumberPicker) findViewById(R.id.numberPickerWeight);

        //set listeners for buttons
        startTrack.setOnClickListener(this);
        abortTrack.setOnClickListener(this);
        finish.setOnClickListener(this);
        sets.setOnClickListener(this);
        reps.setOnClickListener(this);
        weight.setOnClickListener(this);

        //Setting the numberPickers' range
        reps.setMinValue(1);
        reps.setMaxValue(99);
        sets.setMinValue(1);
        sets.setMaxValue(99);
        weight.setMinValue(1);
        weight.setMaxValue(1000);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bicep_curl, menu);
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

    public void startTimer() {
        new CountDownTimer(30000,10) {
            TextView restTime = (TextView) findViewById(R.id.restTime);
            public void onTick(long millisUntilFinished) {
                long secondsRemaining = millisUntilFinished/1000;
                long decimal = (millisUntilFinished - (secondsRemaining*1000)) / 10;
                restTime.setText("" + secondsRemaining + "." + (int)decimal + "s");
            }

            public void onFinish() {
                restTime.setText("Rest Over!");
            }
        }.start();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case(R.id.startBut):
                Toast.makeText(getApplicationContext(),"Start",Toast.LENGTH_SHORT);
                // Putting abort button in place of start button
                startTrack.setEnabled(false);
                startTrack.setVisibility(View.INVISIBLE);
                abortTrack.setEnabled(true);
                abortTrack.setVisibility(View.VISIBLE);
                break;
            case(R.id.finBut):
                Toast.makeText(getApplicationContext(),"Fin",Toast.LENGTH_SHORT);
                int currentSet = sets.getValue();
                sets.setValue(currentSet+1);
                startTimer();
                break;
            case(R.id.abortBut):
                Toast.makeText(getApplicationContext(),"Abort",Toast.LENGTH_SHORT);
                //Putting start button in place of abort button
                abortTrack.setEnabled(false);
                abortTrack.setVisibility(View.INVISIBLE);
                startTrack.setEnabled(true);
                startTrack.setVisibility(View.VISIBLE);
                break;
        }

    }
}
