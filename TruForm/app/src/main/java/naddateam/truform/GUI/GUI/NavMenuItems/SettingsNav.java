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
 * Created by VYMPA on 11/02/2015.
 */
public class SettingsNav extends Fragment {
    View rootview;
    private ListView myListView;
    private String[] strListView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.settings_layout, container, false);
        myListView = (ListView) rootview.findViewById(R.id.listOfSettingItems);

        strListView = getResources().getStringArray(R.array.settingsListItems);
        ArrayAdapter<String> objAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, strListView);
        myListView.setAdapter(objAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent goCalib = new Intent(SettingsNav.this.getActivity(), CalibrationsNav.class);
                    startActivity(goCalib);
                }
                else if (position == 1) {
                    Intent goAudio = new Intent(SettingsNav.this.getActivity(), GeneralNav.class);
                    startActivity(goAudio);
                }
                else if (position == 2) {
                    Intent goRestDef = new Intent(SettingsNav.this.getActivity(), RestoreDefNav.class);
                    startActivity(goRestDef);
                }
                else if (position == 3) {
                    Intent goHelp = new Intent(SettingsNav.this.getActivity(), HelpNav.class);
                    startActivity(goHelp);
                }
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