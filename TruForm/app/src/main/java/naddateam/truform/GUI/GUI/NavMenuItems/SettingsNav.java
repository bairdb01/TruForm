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
import android.widget.Toast;

import naddateam.truform.GUI.GUI.SettingsItems.CalibrationsNav;
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

        strListView = getResources().getStringArray(R.array.my_data_list);
        ArrayAdapter<String> objAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, strListView);
        myListView.setAdapter(objAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
<<<<<<< HEAD
=======
                // Just to make something happen (TEST)
               // Toast.makeText(SettingsNav.this.getActivity(), "hello " + position, Toast.LENGTH_SHORT).show();
>>>>>>> 38581ec15c98091c4e84d10738c4781997928004
                if (position == 0){
                    Intent goCalib = new Intent(SettingsNav.this.getActivity(), CalibrationsNav.class);
                    startActivity(goCalib);
                }
<<<<<<< HEAD
                else if (position == 2){
                    Intent goRestDef = new Intent(SettingsNav.this.getActivity(), RestoreDefNav.class);
                    startActivity(goRestDef);
=======
                else if(position == 4){
                    Toast.makeText(SettingsNav.this.getActivity(), "About", Toast.LENGTH_SHORT).show();
                    Intent goAbout = new Intent(SettingsNav.this.getActivity(), About.class);
                    startActivity(goAbout);
>>>>>>> 38581ec15c98091c4e84d10738c4781997928004
                }
            }


        });
        return rootview;
    }
}

/*
previous

 public class SettingsNav extends Fragment {
    View rootview;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.settings_layout, container, false);
        return rootview;
    }
}*/