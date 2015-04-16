package naddateam.truform.GUI.GUI.UserItems;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import naddateam.truform.GUI.GUI.DataBase;
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
public class TrackStatsNav extends ActionBarActivity implements AdapterView.OnItemClickListener, NumberPicker.OnValueChangeListener{
    ListView lv;
    private TextView tv;
    static Dialog pickTimeDialog ;
    private int day, month, year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trackstats_layout);



        show(); /*THIIIIIIIIIIIIIIIIIISSSSSSSSSSSS IS TO SHOW THE DIALOG THAT POPS UP, GET VALUES HERE TO SET THE DATE*/
    }
    private void dataBaseCall()
    {
        //ArrayList<PullData> pastWorkouts = new ArrayList<PullData>();
        String retVal, startTime, endTime;

        Calendar c = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        String formattedDay = date.format(c.getTime());
        String formattedTime = time.format(c.getTime());

        endTime = formattedDay + "%20" + formattedTime;
        startTime = year + "-" + month + "-" + day + "%20" + "00:00:00";

        // Grab file names and add them to the list of past workouts
        try {
            retVal = new DataBase("test@test.com", startTime, endTime, 3).execute().get();
            String[] data = retVal.split(" ");
            String[] Eid = new String[data.length / 4];
            String[] Form = new String[data.length / 4];
            String[] Weight = new String[data.length / 4];
            String[] Workout = new String[data.length / 4];
            int i, x;
            Log.v("Length: ", data.length + "");
            for(i = 0; i < data.length; i++) {
                Log.v("TOKENS: ", data[i]);
            }
            x = 0;
            for(i = 0; i < data.length; i = i + 4)
            {
                Eid[x] = data[i];
                Form[x] = data[i+1];
                Weight[x] = data[i+2];
                Workout[x] = data[i+3];
                x++;
            }

            for(i = 0; i < x; i++)
            {
                if(!pastWorkouts.contains(Workout[x]))
                {
                    pastWorkouts.add(Workout[x]);
                }
            }

//            File dirFiles = getFilesDir();
//            for (String filename : dirFiles.list()) {
//                if (filename.equals("userDetails") == false)
//                    pastWorkouts.add(filename);
//            }

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
        Toast.makeText(this, String.valueOf(position), Toast.LENGTH_SHORT).show();
        Intent workout = new Intent(this , Workout_History.class);
        String workoutName = lv.getItemAtPosition(position).toString();
        workout.putExtra("workoutName",workoutName); //Sends workout name
        startActivity(workout);
    }

    /*
    * SHOW()
    * This function shows the dialog when you hit this screen.
    * Currently it is void, because it does literally nothing.
    * When you do stuff with it, of course it is needed to be changed.*/
    public void show()
    {

        final Dialog pickTimeDialog = new Dialog(this);

        pickTimeDialog.setTitle("Select a Time");
        pickTimeDialog.setContentView(R.layout.dialoglaylay);

        Button pickCancel = (Button) pickTimeDialog.findViewById(R.id.pickerCancel);
        Button pickSet = (Button) pickTimeDialog.findViewById(R.id.pickerSet);

        final NumberPicker pickDate = (NumberPicker) pickTimeDialog.findViewById(R.id.numberPickDate);
        pickDate.setMaxValue(31); // max value 100
        pickDate.setMinValue(1);   // min value 0
        pickDate.setWrapSelectorWheel(false);
        pickDate.setOnValueChangedListener(this);

        final NumberPicker pickMonth = (NumberPicker) pickTimeDialog.findViewById(R.id.numberPickMonth);
        pickMonth.setMaxValue(12); // max value 100
        pickMonth.setMinValue(1);   // min value 0
        pickMonth.setWrapSelectorWheel(false);
        pickMonth.setOnValueChangedListener(this);

        final NumberPicker pickYear = (NumberPicker) pickTimeDialog.findViewById(R.id.numberPickYear);
        pickYear.setMaxValue(2015); // max value 100
        pickYear.setMinValue(2010);   // min value 0
        pickYear.setWrapSelectorWheel(false);
        pickYear.setOnValueChangedListener(this);

        pickCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                /*CANCEL, do nothing or even have this option?*/
                pickTimeDialog.cancel();
            }
        });
        pickSet.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                /*get values when hitting "set"*/
                day = pickDate.getValue();
                month = pickMonth.getValue();
                year = pickYear.getValue();
                dataBaseCall();
                // i think this is how you get the values
                // recommend, returning an array of these values back to show(); then do stuff with it?
                pickTimeDialog.cancel(); // dismiss the dialog
            }
        });
        pickTimeDialog.show();


    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        //Log.i("value is", "" + newVal);
    }
}
