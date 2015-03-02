package naddateam.truform.GUI.GUI.UserItems;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import naddateam.truform.R;
/**
 * CIS3760
 * Naddateam Truform
 * UserDetails.java
 * Author: Andrew Huynh
 * This class is the User Details page that can be found in Nav Drawer > User > User Details
 * Here the user can enter basic user info to calculate their BMI by entering their height and weight
 * More types of entering data will come (have yet to finalize the ideas of what)
 */
public class UserDetailsNav extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        EditText userWeight;
        EditText userName;
        EditText userAge;
        EditText userHeight;
        TextView BMI = null; /* Body Mass Index, determined by a person's height and weight */
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userdetails_layout);

        userWeight = (EditText) findViewById(R.id.userDetailWeightEdit);
        userName = (EditText) findViewById(R.id.userDetailNameEdit);
        userAge = (EditText) findViewById(R.id.userDetailAgeEdit);
        userHeight = (EditText) findViewById(R.id.userDetailHeightEdit);
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

        return super.onOptionsItemSelected(item);
    }
}
