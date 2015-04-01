package naddateam.truform.GUI.GUI.UserItems;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import naddateam.truform.GUI.GUI.SettingsItems.HelpTutorials.MakeWorkoutTut;
import naddateam.truform.R;
/**
 * CIS3760
 * Naddateam Truform
 * CustomWorkNav.java
 * Author: Andrew Huynh
 * This will be the UI where users can select a list of exercises to create their own workout.
 * It is found under Navigation Drawer > User > Custom workout
 * This will be fully implemented for GOLD. It will open a list where they can select, and it will
 * compile to one workout and they can set the reps/set and name of the workout. They can also post
 * it to the homescreen.
 */
public class CustomWorkNav extends ActionBarActivity implements View.OnClickListener{
    Button helpBut;
    Button newCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customwork_layout);
        newCustom = (Button) findViewById(R.id.thisIsAButton1);
        helpBut = (Button) findViewById(R.id.thisIsAButton2);

        newCustom.setOnClickListener(this);
        helpBut.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*
        * Inflate the menu; this adds items to the action bar if it is present.
         */
        getMenuInflater().inflate(R.menu.menu_customworknav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /* Handle action bar item clicks here. The action bar will
        * automatically handle clicks on the Home/Up button, so long
        * as you specify a parent activity in AndroidManifest.xml.
        */
        int id = item.getItemId();

        /*
        *noinspection SimplifiableIfStatement
         */
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case (R.id.thisIsAButton1):
                Toast.makeText(getApplicationContext(), "Help", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.thisIsAButton2):
                Toast.makeText(getApplicationContext(), "Help", Toast.LENGTH_SHORT).show();
                Intent goCustomTut = new Intent(CustomWorkNav.this, MakeWorkoutTut.class);
                startActivity(goCustomTut);
                break;
        }
    }
}
