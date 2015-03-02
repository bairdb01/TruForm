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
 * WelcomeNav.java
 * Author: Andrew Huynh
 * This is the homepage. It doesn't have anything but will soon in Gold release. At the moment
 * this is not a priority.
 */
public class WelcomeNav extends Fragment {
    View rootview;

    @Override
    /*
    * Inflates the the homescreen which is the welcome_layout. Nothing much else yet.
     */
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.welcome_layout, container, false);
        return rootview;
    }
}
