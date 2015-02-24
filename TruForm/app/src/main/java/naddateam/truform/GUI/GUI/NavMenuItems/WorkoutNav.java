package naddateam.truform.GUI.GUI.NavMenuItems;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import naddateam.truform.GUI.GUI.workouts.Workout0;
import naddateam.truform.GUI.GUI.workouts.Workout1;
import naddateam.truform.GUI.GUI.workouts.Workout2;
import naddateam.truform.GUI.GUI.workouts.Workout3;
import naddateam.truform.GUI.GUI.workouts.Workout4;
import naddateam.truform.GUI.GUI.workouts.Workout5;
import naddateam.truform.GUI.GUI.workouts.Workout6;
import naddateam.truform.R;

/**
 * Created by VYMPA on 11/02/2015.
 */
public class WorkoutNav extends Fragment implements View.OnClickListener {
    View rootview;
    ImageButton imageButton0;
    ImageButton imageButton1;
    ImageButton imageButton2;
    ImageButton imageButton3;
    ImageButton imageButton4;
    ImageButton imageButton5;
    ImageButton imageButton6;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.workout_layout, container, false);
        //Sets the view to workout


        //Finds buttons by their ids
        imageButton0 = (ImageButton) rootview.findViewById(R.id.imageButton0);
        imageButton1 = (ImageButton) rootview.findViewById(R.id.imageButton1);
        imageButton2 = (ImageButton) rootview.findViewById(R.id.imageButton2);
        imageButton3 = (ImageButton) rootview.findViewById(R.id.imageButton3);
        imageButton4 = (ImageButton) rootview.findViewById(R.id.imageButton4);
        imageButton5 = (ImageButton) rootview.findViewById(R.id.imageButton5);
        imageButton6 = (ImageButton) rootview.findViewById(R.id.imageButton6);

//        //Sets listeners for each button
        imageButton0.setOnClickListener(this);
        imageButton1.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
        imageButton3.setOnClickListener(this);
        imageButton4.setOnClickListener(this);
        imageButton5.setOnClickListener(this);
        imageButton6.setOnClickListener(this);

        return rootview;
    }


    public void onClick(View view) {
        Toast.makeText(this.getActivity().getApplicationContext(), "Pressed", Toast.LENGTH_LONG).show();
        switch(view.getId()){
            case R.id.imageButton0:
                Intent workout0 = new Intent(this.getActivity(), Workout0.class);
                this.startActivity(workout0);
                break;
            case R.id.imageButton1:
                Intent workout1 = new Intent(this.getActivity(), Workout1.class);
                this.startActivity(workout1);
                break;
            case R.id.imageButton2:
                Intent workout2 = new Intent(this.getActivity(), Workout2.class);
                this.startActivity(workout2);
                break;
            case R.id.imageButton3:
                Intent workout3 = new Intent(this.getActivity(), Workout3.class);
                this.startActivity(workout3);
                break;
            case R.id.imageButton4:
                Intent workout4 = new Intent(this.getActivity(), Workout4.class);
                this.startActivity(workout4);
                break;
            case R.id.imageButton5:
                Intent workout5 = new Intent(this.getActivity(), Workout5.class);
                this.startActivity(workout5);
                break;
            case R.id.imageButton6:
                Intent workout6 = new Intent(this.getActivity(), Workout6.class);
                this.startActivity(workout6);
                break;

        }
    }

}
