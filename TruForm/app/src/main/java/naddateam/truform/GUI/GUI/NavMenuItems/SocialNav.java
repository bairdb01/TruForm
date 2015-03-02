/**
 * CIS3760
 * Naddateam Truform
 * AboutNav.java
 * Author: Erik Hoffman
 * Description: Java file for the social network page
*/

package naddateam.truform.GUI.GUI.NavMenuItems;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import naddateam.truform.GUI.GUI.workouts.Workout0;
import naddateam.truform.GUI.GUI.workouts.Workout1;
import naddateam.truform.R;

public class SocialNav extends Fragment implements View.OnClickListener {
    View rootview;
    ImageButton facebookButton;
    ImageButton twitterButton;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.socialnet_layout, container, false);
        facebookButton = (ImageButton) rootview.findViewById(R.id.facebookButton);
        twitterButton = (ImageButton) rootview.findViewById(R.id.twitterButton);

        facebookButton.setOnClickListener(this);
        twitterButton.setOnClickListener(this);

        return rootview;
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.facebookButton:
                /*Intent facebook = new Intent(this.getActivity(), facebook.class);
                this.startActivity(facebook);*/
                break;
            case R.id.twitterButton:
                /*Intent twitter = new Intent(this.getActivity(), twitter.class);
                this.startActivity(twitter);*/
                break;
        }
    }
}
