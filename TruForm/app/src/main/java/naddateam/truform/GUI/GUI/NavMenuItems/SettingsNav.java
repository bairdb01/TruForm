package naddateam.truform.GUI.GUI.NavMenuItems;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import naddateam.truform.GUI.GUI.SettingsItems.AboutNav;
import naddateam.truform.GUI.GUI.SettingsItems.GeneralNav;
import naddateam.truform.GUI.GUI.SettingsItems.CalibrationsNav;
import naddateam.truform.GUI.GUI.SettingsItems.HelpNav;
import naddateam.truform.GUI.GUI.SettingsItems.RestoreDefNav;
import naddateam.truform.R;
/**
 * CIS3760
 * Naddateam Truform
 * SettingsNav.java
 * Author: Andrew Huynh
 * This class is the Settings item in the navigation drawer. It will be responsible for navigating to
 * the settings for the app. This includes sound and audio, calibrations (the action), restoring
 * settings, tutorials and information about the app and who created.
 */
public class SettingsNav extends Fragment {
    View rootview;
    private ListView myListView; /*List view for the different items for the setting's navigate*/
    private String[] strListView; /*String Array to input into the ListView for display*/

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.settings_layout, container, false);
        myListView = (ListView) rootview.findViewById(R.id.listOfSettingItems);

        strListView = getResources().getStringArray(R.array.settingsListItems);
        ArrayAdapter<String> objAdapter = new ArrayAdapter<String>(this.getActivity(),
                R.layout.listviewcloud_text, strListView);
        myListView.setAdapter(objAdapter);

        /*
        * Waits for user input (clicking) of the setting items and will act accordingly.
        * Each will have different intents that will create new intents to that will start new
        * activities when they are clicked.
         */
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*
                * Starts the activity to Calibrate Exercises.
                * It will open a list pop up for users to select which exercises to calibrate
                 */
                if (position == 0) {
                    Intent goCalib = new Intent(SettingsNav.this.getActivity(), CalibrationsNav.class);
                    startActivity(goCalib);
                }
                /*
                * This will start a "Preference Activity" which is basically like a list with prebuilt
                * checkboxes for users can select preference of settings
                 */
                else if (position == 1) {
                    Intent goAudio = new Intent(SettingsNav.this.getActivity(), GeneralNav.class);
                    startActivity(goAudio);
                }
                /*
                * The options to clear specific data, some parts or all the phones
                 */
                else if (position == 2) {
                    Intent goRestDef = new Intent(SettingsNav.this.getActivity(), RestoreDefNav.class);
                    startActivity(goRestDef);
                }
                else if (position == 3) {
                    Intent goHelp = new Intent(SettingsNav.this.getActivity(), HelpNav.class);
                    startActivity(goHelp);
                }
                /*
                * About Navigation, is the about information about the phone
                 */
                else if (position == 4) {
                    Intent goAbout = new Intent(SettingsNav.this.getActivity(), AboutNav.class);
                    goAbout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(goAbout);
                }
            }
        });
        return rootview;
    }
}