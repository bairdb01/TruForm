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


import android.content.Context;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

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

        // Selects the workout based on name
        // Should be changed to access the Workouts/Exercises class
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
//        Array<String> itemsAdapter =
//                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,workout);
        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.listviewcloud_text,workout));
        lv.setOnItemClickListener(this);
    }

    @Override
    /**
     * Opens the exercise screen
     */
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(getApplicationContext(), "Pressed" + position, Toast.LENGTH_LONG).show();
        String exerciseName;

        // Opens the exercise
        Intent exercise0 = new Intent(this ,GenericExercise.class);
        exerciseName = lv.getItemAtPosition(position).toString();
        exercise0.putExtra("exName",exerciseName); // Pass exercise name
        exercise0.putExtra("exNum",position); //Sends which exercise number was clicked
        exercise0.putExtra("workoutName",workoutName); //Sends workout name
        startActivity(exercise0);
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
        // Saves the user details when the actionbar back button is pressed
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        // Writes the cached data from the exercises to a single non-volatile file
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String woFilename = workoutName + "-stats-" + date;
        File file;
        FileWriter fileWriter;

        try {
            // Finds all exercises files and writes to the workouts file
            file = new File(getFilesDir(), woFilename) ; // Saves to app internal storage location
            fileWriter = new FileWriter(file);

            // Writes exercises name, sets, reps, and weight from cache file to permanent file
            for (int exNumber = 0; exNumber < lv.getCount(); exNumber ++) {
                try {
                    // Open exercise data
                    //Toast.makeText(this,"Start for", Toast.LENGTH_SHORT).show();
                    String exFilename = workoutName + "-exercise" + exNumber;
                    File exData = new File(getCacheDir(),exFilename);
//                    Toast.makeText(this,exFilename, Toast.LENGTH_SHORT).show();
                    BufferedReader bf = new BufferedReader(new FileReader(exData));
//                    Toast.makeText(this,"BF", Toast.LENGTH_SHORT).show();


                    String line = "empty";
                    while ((line = bf.readLine()) != null) {
                        //Toast.makeText(this,line, Toast.LENGTH_SHORT).show();
                        fileWriter.write(line);
                        fileWriter.write("\r\n");
                    }
                    //Toast.makeText(this,"End while", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
//                    Toast.makeText(this,"Error reading exercise data", Toast.LENGTH_SHORT).show();
                }
            }
            //Toast.makeText(this,"Flush and Close", Toast.LENGTH_SHORT).show();
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
//            Toast.makeText(this,"Error writing", Toast.LENGTH_SHORT).show();
        }
        deleteCache(this);
        super.onBackPressed();
    }


    /**
     * Taken from: http://stackoverflow.com/questions/6898090/how-to-clear-cache-android
     * @param context Just use this or reference an activity
     */
    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {}
    }

    /**
     * Provided by http://stackoverflow.com/questions/6898090/how-to-clear-cache-android
     * @param dir
     * @return true if the directory was deleted
     */
    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}
