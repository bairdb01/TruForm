package naddateam.truform.GUI.GUI.UserItems;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import naddateam.truform.GUI.GUI.Bluetooth;
import naddateam.truform.GUI.GUI.DataBase;
import naddateam.truform.R;
import naddateam.truform.functionality.ExerciseAnalysis;
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

public class UserDetailsNav extends ActionBarActivity implements View.OnClickListener{
    EditText userWeight;
    EditText userAge;
    EditText userHeight;
    ExerciseAnalysis exerciseAnalysis = new ExerciseAnalysis();
    Bluetooth ble = new Bluetooth();
    ListView lv;
    Button btnSR, btnNew, btnExisting, btnEnter;
    View btnLayout, editLayout;
    TextView title, existingLbl;
    EditText Email, fName, lName, uName, gender, getEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userdetails_layout);

        lv = (ListView) findViewById(R.id.userDetailsListView);
        userWeight = (EditText) findViewById(R.id.weightTxt);
        userAge = (EditText) findViewById(R.id.ageTxt);
        userHeight = (EditText) findViewById(R.id.heightTxt);
        btnSR = (Button) findViewById(R.id.button);
        title = (TextView) findViewById(R.id.title);
        Email = (EditText) findViewById(R.id.emailTxt);
        fName = (EditText) findViewById(R.id.fnameTxt);
        lName = (EditText) findViewById(R.id.lnameTxt);
        uName = (EditText) findViewById(R.id.unameTxt);
        gender = (EditText) findViewById(R.id.genderTxt);
        btnExisting = (Button) findViewById(R.id.btnExisting);
        btnNew = (Button) findViewById(R.id.btnNew);
        btnSR = (Button) findViewById(R.id.button);
        btnLayout = findViewById(R.id.btnLayout);
        editLayout = findViewById(R.id.editLayout);
        existingLbl = (TextView) findViewById(R.id.existingLabel);
        btnEnter = (Button) findViewById(R.id.confirmEmail);
        getEmail = (EditText) findViewById(R.id.getUser);

        btnSR.setOnClickListener(this);
        btnExisting.setOnClickListener(this);
        btnNew.setOnClickListener(this);
        btnEnter.setOnClickListener(this);
    }
        // Checking if previous cache data is available in case workout incomplete



//        try {
//            // Finds all exercises files and writes to the workouts file
//            File file = new File(getFilesDir(), "userDetails");
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
//
//            String line = "";
//
//            line = bufferedReader.readLine();
//            fName.setText(line);
//            line = bufferedReader.readLine();
//            lName.setText(line);
//            line = bufferedReader.readLine();
//            gender.setText(line);
//            line = bufferedReader.readLine();
//            userAge.setText(line);
//            line = bufferedReader.readLine();
//            userHeight.setText(line);
//            line = bufferedReader.readLine();
//            userWeight.setText(line);
//            line = bufferedReader.readLine();
//            Email.setText(line);
//            line = bufferedReader.readLine();
//            uName.setText(line);
//
//        } catch (Exception e) {
//            e.printStackTrace();
////            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
//        }

        @Override
        public void onClick(View v) {
            String btnVal;
            btnVal = btnSR.getText().toString();

            switch(v.getId())
            {
                case (R.id.btnExisting):
                    existingLbl.setVisibility(View.VISIBLE);
                    btnEnter.setVisibility(View.VISIBLE);
                    getEmail.setVisibility(View.VISIBLE);
                    btnExisting.setVisibility(View.INVISIBLE);
                    btnNew.setVisibility(View.INVISIBLE);
                    break;
                case (R.id.confirmEmail):
                    btnLayout.setVisibility(View.INVISIBLE);
                    editLayout.setVisibility(View.VISIBLE);
                    lv.setVisibility(View.VISIBLE);
                    btnSR.setVisibility(View.INVISIBLE);
                    //btnSR.setText("Insert");
                    String retUsers = "";
                    String retStats = "";
                    String[] values = new String[5];

                    try {
                        retUsers = new DataBase(getEmail.getText().toString(), 0).execute().get();
                        retStats = new DataBase(getEmail.getText().toString(), 5).execute().get();
                    } catch (Exception e) {
                        Log.v("error", e.toString());
                    }
                    Log.v("VALUES1: ", retUsers);

                    Log.v("VALUES2: ", retStats);

                    values = retUsers.split(" ");
                    Email.setText(values[0]);
                    fName.setText(values[1]);
                    lName.setText(values[2]);
                    uName.setText(values[3]);
                    values = retStats.split(" ");
                    userHeight.setText(values[1]);
                    userWeight.setText(values[2]);
                    userAge.setText(values[3]);
                    gender.setText(values[4]);
                    break;
                case (R.id.btnNew):
                    btnLayout.setVisibility(View.INVISIBLE);
                    editLayout.setVisibility(View.VISIBLE);
                    lv.setVisibility(View.VISIBLE);
                    btnSR.setVisibility(View.VISIBLE);
                    break;
                case (R.id.button):
                    btnSR.setVisibility(View.INVISIBLE);
                    String retVal = "";
                    try{
                        retVal = new DataBase(Email.getText().toString(), fName.getText().toString(), lName.getText().toString(), uName.getText().toString(), 1).execute().get();
                        new DataBase(Email.getText().toString(), userHeight.getText().toString(), userWeight.getText().toString(), userAge.getText().toString(), gender.getText().toString(), 4).execute();
                        Toast.makeText(getApplicationContext(), retVal, Toast.LENGTH_SHORT).show();
                    }
                    catch(Exception e)
                    {
                        Log.v("error", e.toString());
                    }
                    break;
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
            fileWriter.write(fName.getText().toString());
            fileWriter.write("\r\n");
            fileWriter.write(lName.getText().toString());
            fileWriter.write("\r\n");
            fileWriter.write(gender.getText().toString());
            fileWriter.write("\r\n");
            fileWriter.write(userAge.getText().toString());
            fileWriter.write("\r\n");
            fileWriter.write(userHeight.getText().toString());
            fileWriter.write("\r\n");
            fileWriter.write(userWeight.getText().toString());
            fileWriter.write("\r\n");
            fileWriter.write(Email.getText().toString());
            fileWriter.write("\r\n");
            fileWriter.write(uName.getText().toString());
            fileWriter.write("\r\n");


            fileWriter.flush();
            fileWriter.close();
            //Toast.makeText(this,"Cached",Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onBackPressed();
    }

}
