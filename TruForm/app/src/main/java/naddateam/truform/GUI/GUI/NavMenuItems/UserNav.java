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

import java.lang.annotation.Target;

import naddateam.truform.GUI.GUI.UserItems.CustomWorkNav;
import naddateam.truform.GUI.GUI.UserItems.TrackStatsNav;
import naddateam.truform.GUI.GUI.UserItems.UserDetailsNav;
import naddateam.truform.R;

/**
 * CIS3760
 * Naddateam Truform
 * UserNav.java
 * Author: Andrew Huynh
 * This is the User Item inside the navigation drawer
 */
public class UserNav extends Fragment {
    View rootview;
    private ListView myListView; /*List view for the different items for the setting's navigate*/
    private String[] strListView; /*String Array to input into the ListView for display*/

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.user_layout, container, false);
        myListView = (ListView) rootview.findViewById(R.id.userListView);

        strListView = getResources().getStringArray(R.array.userListItems);
        ArrayAdapter<String> objAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, strListView);
        myListView.setAdapter(objAdapter);

        /*
        * Waits for user input (clicking) of the setting items and will act accordingly.
        * Each will have different intents that will create new intents to that will start new
        * activities when they are clicked.
         */
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    /*
                    * Opens another window for users to input their personal details
                     */
                    Intent goUserDetail = new Intent(UserNav.this.getActivity(), UserDetailsNav.class);
                    startActivity(goUserDetail);
                }
                else if (position == 1) {
                    /*
                    * Opens to the Past Statistics page
                     */
                    Intent trackedStats = new Intent(UserNav.this.getActivity(), TrackStatsNav.class);
                    startActivity(trackedStats);
                }
                else if (position == 2) {
                    /*
                    * Opens to the custome workout page
                     */
                    Intent customWorkouts = new Intent(UserNav.this.getActivity(), CustomWorkNav.class);
                    startActivity(customWorkouts);
                }
            }
        });
        return rootview;
    }
}
