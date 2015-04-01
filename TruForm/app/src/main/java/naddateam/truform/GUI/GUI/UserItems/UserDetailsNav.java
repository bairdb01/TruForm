package naddateam.truform.GUI.GUI.UserItems;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import naddateam.truform.R;
/**
 * CIS3760
 * Naddateam Truform
 * UserDetails.java
 * Author: Andrew Huynh & Benjamin Baird
 * This class is the User Details page that can be found in Nav Drawer > User > User Details
 * Here the user can enter basic user info to calculate their BMI by entering their height and weight
 * More types of entering data will come (have yet to finalize the ideas of what)
 */

/**
 * Need to create a background service/handler to check when height/weight is modified.
 *      Or just make a button to update
 */

public class UserDetailsNav extends ActionBarActivity {
    EditText userWeight;
    EditText userName;
    EditText userAge;
    EditText userHeight;
//    TextView BMI = null; /* Body Mass Index, determined by a person's height and weight */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userdetails_layout);

        userWeight = (EditText) findViewById(R.id.userDetailWeightEdit);
        userName = (EditText) findViewById(R.id.userDetailNameEdit);
        userAge = (EditText) findViewById(R.id.userDetailAgeEdit);
        userHeight = (EditText) findViewById(R.id.userDetailHeightEdit);

        // Checking if previous cache data is available in case workout incomplete

        try {
            // Finds all exercises files and writes to the workouts file
            File file = new File(getFilesDir(), "userDetails");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String line = "";
            line = bufferedReader.readLine();
            userWeight.setText(line);
            line = bufferedReader.readLine();
            userName.setText(line);
            line = bufferedReader.readLine();
            userAge.setText(line);
            line = bufferedReader.readLine();
            userHeight.setText(line);

        } catch (Exception e) {
            e.printStackTrace();
//            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_userdetails, menu);
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
    /**
     * Saves the user data in a file when they press the hardware button
     */
    public void onBackPressed() {
        // Stores data in a file
        String filename ="userDetails";

        try {
            File file;
            FileWriter fileWriter;
            file = new File(getFilesDir(), filename) ;
            fileWriter = new FileWriter(file);

            // Writes user details
            fileWriter.write(userWeight.getText().toString());
            fileWriter.write("\r\n");
            fileWriter.write(userName.getText().toString());
            fileWriter.write("\r\n");
            fileWriter.write(userAge.getText().toString());
            fileWriter.write("\r\n");
            fileWriter.write(userHeight.getText().toString());


            fileWriter.flush();
            fileWriter.close();
            //Toast.makeText(this,"Cached",Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onBackPressed();
    }

}
