/*
CIS3760
Naddateam Truform
AboutNav.java
Author: Erik Hoffman
Java file for the social network page
*/

package naddateam.truform.GUI.GUI.NavMenuItems;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import naddateam.truform.R;

public class SocialNav extends Fragment {
    View rootview;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.socialnet_layout, container, false);
        return rootview;
    }
}
