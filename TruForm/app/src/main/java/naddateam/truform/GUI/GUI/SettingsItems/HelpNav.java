package naddateam.truform.GUI.GUI.SettingsItems;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import naddateam.truform.GUI.GUI.Bluetooth;
import naddateam.truform.GUI.GUI.SettingsItems.HelpTutorials.BicepCurlTut;
import naddateam.truform.GUI.GUI.SettingsItems.HelpTutorials.ChestTut;
import naddateam.truform.GUI.GUI.SettingsItems.HelpTutorials.HowCalibTut;
import naddateam.truform.GUI.GUI.SettingsItems.HelpTutorials.MakeWorkoutTut;
import naddateam.truform.GUI.GUI.SettingsItems.HelpTutorials.SquatsTut;
import naddateam.truform.R;
import naddateam.truform.Tester;

/**
 * CIS3760
 * Naddateam Truform
 * HelpNav.java
 * Author: Andrew Huynh
 * This class is the Help page found under Navigation Drawer > Settings Help
 * It will consist a list of things the users can find information about.
 * Tutorials of how to do certain exercises
 * Tutorial how to create custome work outs
 * Tutorial how to calibrate
 * Bluetooth settings (temporary)
 */

public class HelpNav extends ActionBarActivity implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingshelp_layout);
        ListView lv = (ListView)findViewById(R.id.helpListView);
        lv.setOnItemClickListener(this);
    }

    @Override
/**
 * Opens the exercise screen
 * First Position = Exercise Form Tutorial
 * Second Position = Calibration Tutorial
 * Third Position = Workout Maker Tutorial
 */
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        final AlertDialog.Builder builderContain = new AlertDialog.Builder(this);
        final AlertDialog.Builder builderOne = new AlertDialog.Builder(this);
        final AlertDialog.Builder builderTwo = new AlertDialog.Builder(this);
        final AlertDialog.Builder builderThree = new AlertDialog.Builder(this);
        final AlertDialog.Builder builderFour = new AlertDialog.Builder(this);

        if (position == 0) {
            builderContain.setTitle("Select a category.")
                    .setItems(R.array.calibrationItemsArr, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {


                            /* CHEST AND TRICEPS ---------------------------------------------------
                            * 0 Dumbbell Chest Press
                            * 1 Incline Bench Press
                            * 2 Dumbbell Flies
                            * 3 Incline Flies
                            * 4 Skull Crushers
                            * 5 Dips
                            * 6 Tricep Extensions
                             */
                            if (which == 0) {
                                builderOne.setTitle("Select an Exercise")
                                        .setItems(R.array.ChestAndTriceps, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (which == 0) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, ChestTut.class);
                                                    //  startActivity(goChestTut);
                                                }
                                                else if (which == 1) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, ChestTut.class);
                                                    //  startActivity(goChestTut);
                                                }
                                                else if (which == 2) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, ChestTut.class);
                                                    //  startActivity(goChestTut);
                                                }
                                                else if (which == 3) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, ChestTut.class);
                                                    //  startActivity(goChestTut);
                                                }
                                                else if (which == 4) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, ChestTut.class);
                                                    //  startActivity(goChestTut);
                                                }
                                                else if (which == 5) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, ChestTut.class);
                                                    //  startActivity(goChestTut);
                                                }
                                                else if (which == 6) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, ChestTut.class);
                                                    //  startActivity(goChestTut);
                                                }
                                            }
                                        });
                                builderOne.create().show();
                            }

                            /* LEGS ----------------------------------------------------------------
                            * 0 Squats
                            * 1 Front Squats
                            * 2 Leg Extensions
                            * 3 Hamstring Curls
                            * 4 Lying Hamstring Curls
                            * 5 Dips
                            * 6 Tricep Extensions
                             */
                            else if (which == 1) {
                                builderTwo.setTitle("Select an Exercise")
                                        .setItems(R.array.Legs, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (which == 0) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, ChestTut.class);
                                                    //  startActivity(goChestTut);
                                                }
                                                else if (which == 1) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, ChestTut.class);
                                                    //  startActivity(goChestTut);
                                                }
                                                else if (which == 2) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, ChestTut.class);
                                                    //  startActivity(goChestTut);
                                                }
                                                else if (which == 3) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, ChestTut.class);
                                                    //  startActivity(goChestTut);
                                                }
                                                else if (which == 4) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, ChestTut.class);
                                                    //  startActivity(goChestTut);
                                                }
                                            }
                                        });
                                builderTwo.create().show();
                            }

                            /* BACK AND BICEPS -----------------------------------------------------
                            * 0 Deadlifts
                            * 1 Bentover Rows
                            * 2 Wide Grip Pullbar
                            * 3 Cable Rows
                            * 4 Dumbbell Bicep Curls
                            * 5 Barbell Bicep Curls
                            * 6 Tricep Extensions
                            * 7 Spider Curls
                             */
                            else if (which == 2) {
                                builderThree.setTitle("Select an Exercise")
                                        .setItems(R.array.BackAndBiceps, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (which == 0) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, ChestTut.class);
                                                    //  startActivity(goChestTut);
                                                }
                                                else if (which == 1) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, ChestTut.class);
                                                    //  startActivity(goChestTut);
                                                }
                                                else if (which == 2) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, ChestTut.class);
                                                    //  startActivity(goChestTut);
                                                }
                                                else if (which == 3) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, ChestTut.class);
                                                    //  startActivity(goChestTut);
                                                }
                                                else if (which == 4) {
                                                    Intent goBicepCurlTut = new Intent(HelpNav.this, BicepCurlTut.class);
                                                    startActivity(goBicepCurlTut);
                                                }
                                                else if (which == 5) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, ChestTut.class);
                                                    //  startActivity(goChestTut);
                                                }
                                                else if (which == 6) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, ChestTut.class);
                                                    //  startActivity(goChestTut);
                                                }
                                                else if (which == 7) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, ChestTut.class);
                                                    //  startActivity(goChestTut);
                                                }
                                            }
                                        });
                                builderThree.create().show();
                            }

                                /* SHOULDERS -------------------------------------------------------
                                * 0 Shoulder Press
                                * 1 Lateral Raises
                                * 2 Reverse Flies
                                * 3 Upright Barbell Rows
                                 */
                            else if (which == 3) {
                                builderFour.setTitle("Select an Exercise")
                                        .setItems(R.array.Shoulders, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (which == 0) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, ChestTut.class);
                                                    //  startActivity(goChestTut);
                                                }
                                                else if (which == 1) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, ChestTut.class);
                                                    //  startActivity(goChestTut);
                                                }
                                                else if (which == 2) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, ChestTut.class);
                                                    //  startActivity(goChestTut);
                                                }
                                                else if (which == 3) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, ChestTut.class);
                                                    //  startActivity(goChestTut);
                                                }
                                                else if (which == 4) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, ChestTut.class);
                                                    //  startActivity(goChestTut);
                                                }
                                                else if (which == 5) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, ChestTut.class);
                                                    //  startActivity(goChestTut);
                                                }
                                                else if (which == 6) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, ChestTut.class);
                                                    //  startActivity(goChestTut);
                                                }
                                                else if (which == 7) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, ChestTut.class);
                                                    //  startActivity(goChestTut);
                                                }
                                            }
                                        });
                                builderFour.create().show();
                            }
                        }
                    });
            builderContain.create().show();
        }
        /*
        * How to make a workout
         */
        else if (position == 1) {
            Intent goMakeWorkoutTut = new Intent(HelpNav.this, MakeWorkoutTut.class);
            startActivity(goMakeWorkoutTut);
        }
        /*
        * How to calibrate - Removed for RC
         */
/*        else if (position == 2) {
            Intent goHowToCalibTut = new Intent(HelpNav.this, HowCalibTut.class);
            startActivity(goHowToCalibTut);
        }*/
        /*
        * Bluetooth settings
         */
        else if (position == 2) {
            Intent goBlue = new Intent(HelpNav.this, Bluetooth.class);
            startActivity(goBlue);
        }
        else {
            if (position == 3) {
                Intent test = new Intent(this, Tester.class);
                startActivity(test);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settingshelpnav, menu);
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

    public void onBackPressed() {
        // Stores data in a file

        super.onBackPressed();
    }
}
