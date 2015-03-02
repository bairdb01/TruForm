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
 * Created by VYMPA on 11/02/2015.
 */
public class UserNav extends Fragment {
    View rootview;
    private ListView myListView;
    private String[] strListView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.user_layout, container, false);
        myListView = (ListView) rootview.findViewById(R.id.userListView);

        strListView = getResources().getStringArray(R.array.userListItems);
        ArrayAdapter<String> objAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, strListView);
        myListView.setAdapter(objAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent goUserDetail = new Intent(UserNav.this.getActivity(), UserDetailsNav.class);
                    startActivity(goUserDetail);
                }
                else if (position == 1) {
                    Intent trackedStats = new Intent(UserNav.this.getActivity(), TrackStatsNav.class);
                    startActivity(trackedStats);
                }
                else if (position == 2) {
                    Intent customWorkouts = new Intent(UserNav.this.getActivity(), CustomWorkNav.class);
                    startActivity(customWorkouts);
                }
            }
        });
        return rootview;
    }
}
