package naddateam.truform.NavMenuItems;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import naddateam.truform.R;

/**
 * Created by VYMPA on 11/02/2015.
 */
public class WelcomeNav extends Fragment {
    View rootview;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.welcome_layout, container, false);
        return rootview;
    }
}
