package naddateam.truform.GUI.GUI.SettingsItems;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

import naddateam.truform.GUI.GUI.Bluetooth;
import naddateam.truform.GUI.GUI.SettingsItems.HelpTutorials.BicepCurlTut;
import naddateam.truform.GUI.GUI.SettingsItems.HelpTutorials.ChestTut;
import naddateam.truform.GUI.GUI.SettingsItems.HelpTutorials.HowCalibTut;
import naddateam.truform.GUI.GUI.SettingsItems.HelpTutorials.MakeWorkoutTut;
import naddateam.truform.GUI.GUI.SettingsItems.HelpTutorials.SquatsTut;
import naddateam.truform.R;
import naddateam.truform.Tester;
import naddateam.truform.functionality.GifWebView;

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
        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.listviewcloud_text,
                new String[]{
                        "Exercise Form Tutorials",
                }));
        lv.setOnItemClickListener(this);
        /*
        String[] woList = new String[pastWorkouts.size()];
        pastWorkouts.toArray(woList);
        lv = (ListView) findViewById(R.id.pastWorkouts);
        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.listviewcloud_text, helpListItems));
        */
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
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
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

                                                    //alertDialogBuilder.setTitle("Dumbbell Chest Press");
                                                    //Set message
                                                    /*alertDialogBuilder.setMessage(getString(R.string.tutDumbChestPress))
                                                            .setCancelable(false)
                                                            .setPositiveButton("Confirm",new DialogInterface.OnClickListener() {
                                                                public void onClick(DialogInterface dialog,int id) {
                                                                }
                                                            })
                                                            .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                                                                public void onClick(DialogInterface dialog,int id) {
                                                                    dialog.cancel();
                                                                }
                                                            });
                                                    AlertDialog alertDialog = alertDialogBuilder.create();
                                                    alertDialog.show();*/
                                                    alertDialogBuilder.setTitle("Dumbbell Chest Press");
                                                    alertDialogBuilder.setCancelable(false);
                                                    alertDialogBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                        }
                                                    });
                                                    alertDialogBuilder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog,int id) {
                                                            dialog.cancel();
                                                        }
                                                    });
                                                    InputStream stream = null;
                                                    try {
                                                        stream = getAssets().open("db_benchpress.gif");
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                    GifWebView view = new GifWebView(HelpNav.this, "file:///android_asset/db_benchpress.gif");
                                                    alertDialogBuilder.setView(view);
                                                    alertDialogBuilder.create().show();
                                                }
                                                else if (which == 1) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, InclineChestTut.class);
                                                    //  startActivity(InclineChestTut);
                                                    alertDialogBuilder.setTitle("Incline Bench Press");
                                                    alertDialogBuilder.setCancelable(false);
                                                    alertDialogBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                        }
                                                    });
                                                    alertDialogBuilder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog,int id) {
                                                            dialog.cancel();
                                                        }
                                                    });
                                                    InputStream stream = null;
                                                    try {
                                                        stream = getAssets().open("bb_incline_benchpress.gif");
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                    GifWebView view = new GifWebView(HelpNav.this, "file:///android_asset/bb_incline_benchpress.gif");
                                                    alertDialogBuilder.setView(view);
                                                    alertDialogBuilder.create().show();
                                                }
                                                else if (which == 2) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, DumbFliesChestTut.class);
                                                    //  startActivity(DumbFliesChestTut);
                                                    alertDialogBuilder.setTitle("Dumbbell Flies");
                                                    alertDialogBuilder.setCancelable(false);
                                                    alertDialogBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                        }
                                                    });
                                                    alertDialogBuilder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog,int id) {
                                                            dialog.cancel();
                                                        }
                                                    });
                                                    InputStream stream = null;
                                                    try {
                                                        stream = getAssets().open("db_fly.gif");
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                    GifWebView view = new GifWebView(HelpNav.this, "file:///android_asset/db_fly.gif");
                                                    alertDialogBuilder.setView(view);
                                                    alertDialogBuilder.create().show();
                                                }
                                                else if (which == 3) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, IncFliesChestTut.class);
                                                    //  startActivity(IncFliesChestTut);
                                                    alertDialogBuilder.setTitle("Incline Flies");
                                                    alertDialogBuilder.setCancelable(false);
                                                    alertDialogBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                        }
                                                    });
                                                    alertDialogBuilder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog,int id) {
                                                            dialog.cancel();
                                                        }
                                                    });
                                                    InputStream stream = null;
                                                    try {
                                                        stream = getAssets().open("db_incline_fly.gif");
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                    GifWebView view = new GifWebView(HelpNav.this, "file:///android_asset/db_incline_fly.gif");
                                                    alertDialogBuilder.setView(view);
                                                    alertDialogBuilder.create().show();
                                                }
                                                else if (which == 4) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, SkullChestTut.class);
                                                    //  startActivity(SkullChestTut);
                                                    alertDialogBuilder.setTitle("Skull Crushers");
                                                    alertDialogBuilder.setCancelable(false);
                                                    alertDialogBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                        }
                                                    });
                                                    alertDialogBuilder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog,int id) {
                                                            dialog.cancel();
                                                        }
                                                    });
                                                    InputStream stream = null;
                                                    try {
                                                        stream = getAssets().open("bb_skull_crushers.gif");
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                    GifWebView view = new GifWebView(HelpNav.this, "file:///android_asset/bb_skull_crushers.gif");
                                                    alertDialogBuilder.setView(view);
                                                    alertDialogBuilder.create().show();
                                                }
                                                else if (which == 5) {
                                                    //  Dips
                                                    alertDialogBuilder.setTitle("Dips");
                                                    alertDialogBuilder.setCancelable(false);
                                                    alertDialogBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                        }
                                                    });
                                                    alertDialogBuilder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog,int id) {
                                                            dialog.cancel();
                                                        }
                                                    });
                                                    InputStream stream = null;
                                                    try {
                                                        stream = getAssets().open("cb_tricep_dip.gif");
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                    GifWebView view = new GifWebView(HelpNav.this, "file:///android_asset/cb_tricep_dip.gif");
                                                    alertDialogBuilder.setView(view);
                                                    alertDialogBuilder.create().show();
                                                }
                                                else if (which == 6) {
                                                    //  Intent goChestTut = new Intent(HelpNav.this, TricepExtTut.class);
                                                    // startActivity(TricepExtTut);
                                                    alertDialogBuilder.setTitle("Tricep Kickback");
                                                    alertDialogBuilder.setCancelable(false);
                                                    alertDialogBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                        }
                                                    });
                                                    alertDialogBuilder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog,int id) {
                                                            dialog.cancel();
                                                        }
                                                    });
                                                    InputStream stream = null;
                                                    try {
                                                        stream = getAssets().open("db_kickback.gif");
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                    GifWebView view = new GifWebView(HelpNav.this, "file:///android_asset/db_kickback.gif");
                                                    alertDialogBuilder.setView(view);
                                                    alertDialogBuilder.create().show();
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
        else if (position == 1) {
                Intent test = new Intent(this, Tester.class);
                startActivity(test);
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
