package naddateam.truform.GUI.GUI.NavMenuItems;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import naddateam.truform.R;

/**
 * CIS3760
 * Naddateam Truform
 * SchedulesNav.java
 * Author: Andrew Huynh
 * This schedule class is actually the "Custom Workouts" item on the Navigation Drawer but the name
 * has only been recently renamed, therefore during ALPHA it'll be called the SchedulesNav for now
 */
public class SchedulesNav extends Fragment {
    View rootview;

    @Override
    /*
     * Basic onCreatView to inflate the rootview layout for schedules, soon to be renamed
     * Custom Workouts
     */
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.schedules_layout, container, false);
        return rootview;
    }
}
