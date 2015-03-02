package naddateam.truform.GUI.GUI.SettingsItems;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import naddateam.truform.R;
/**
 * CIS3760
 * Naddateam Truform
 * RestoreDefNav.java
 * Author: Andrew Huynh
 * This class is the Restore to Defaults for Nav Drawer > Settings > Reset to Defaults
 * There are different types we thought of. Clearing calibrations, clearing user info and clearing
 * everything up to the point when they got the app. There is currectly no functionality
 */
public class RestoreDefNav extends ActionBarActivity implements AdapterView.OnItemClickListener{

    @Override
    /*
    * Creates Basic view with a populated listview so users can click what they want
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.restoredef_layout);
        ListView lv = (ListView)findViewById(R.id.restoreDefList);

        /*
        * Listening for user input
         */
        lv.setOnItemClickListener(this);

    }

    @Override
    /*
    * This is an alert dialog, that will pop to confiorm the user about the clearing the user.
    * It will warn the user what they are attempting to do with working confirmation and cancel
    * button. There is no actual functionality in backend
     */
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder( this);

        if (position == 0) {
            //Set dialog here
            alertDialogBuilder.setTitle("Warning");
            //Set message
            alertDialogBuilder.setMessage("By selecting confirm will clear all workout calibrations. Do you wish to continue?")
                    .setCancelable(false)
                    .setPositiveButton("Confirm",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            Toast.makeText(getApplicationContext(), "Calibrations Cleared", Toast.LENGTH_SHORT).show();
                            /*

                            * CODE
                            * HERE
                            * TO APPLY CLEAR ALL CALIBRATIONS
                            *
                            * */
                        }
                    })
                    .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        else if (position == 1) {
            //Set dialog here
            alertDialogBuilder.setTitle("Warning");
            //Set message
            alertDialogBuilder.setMessage("By selecting confirm will clear all user information entered. Do you wish to continue?")
                    .setCancelable(false)
                    .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(getApplicationContext(), "User Info Cleared", Toast.LENGTH_SHORT).show();
                            /*

                            * CODE
                            * HERE
                            * TO APPLY CLEAR ALL USER SETTINGS
                            *
                            * */
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        else {
            //Set dialog here
            alertDialogBuilder.setTitle("Warning");
            //Set message
            alertDialogBuilder.setMessage("By selecting confirm will clear everything on this App to its original state. Do you wish to continue?")
                    .setCancelable(false)
                    .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(getApplicationContext(), "Reset to Defaults", Toast.LENGTH_SHORT).show();
                            /*

                            * CODE
                            * HERE
                            * TO APPLY CLEAR EVERYTHING
                            *
                            * */
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_restoredef, menu);
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
