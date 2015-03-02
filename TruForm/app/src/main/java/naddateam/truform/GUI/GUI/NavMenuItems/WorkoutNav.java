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
import naddateam.truform.R;


/**
 * CIS3760
 * Naddateam Truform
 * WorkoutNav.java
 * Author: Benjamin Baird
 * Last Modified March 1, 2015
 * Description: Controller for the layout that contains the shown list of workouts
 */

public class WorkoutNav extends Fragment implements View.OnClickListener {
    View rootview;
    ImageButton imageButton0;
    ImageButton imageButton1;
    ImageButton imageButton2;
    ImageButton imageButton3;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.workout_layout, container, false);

        //Finds buttons by their ids
        imageButton0 = (ImageButton) rootview.findViewById(R.id.imageButton0);
        imageButton1 = (ImageButton) rootview.findViewById(R.id.imageButton1);
        imageButton2 = (ImageButton) rootview.findViewById(R.id.imageButton2);
        imageButton3 = (ImageButton) rootview.findViewById(R.id.imageButton3);


//        //Sets listeners for each button
        imageButton0.setOnClickListener(this);
        imageButton1.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
        imageButton3.setOnClickListener(this);


        return rootview;
    }


    /**
     * Provides listener functionality for buttons
     * @param view
     */
    public void onClick(View view) {
        //Toast.makeText(this.getActivity().getApplicationContext(), "Pressed", Toast.LENGTH_LONG).show();
        Intent workout0 = new Intent(this.getActivity(), Workout0.class);
        switch(view.getId()){

            case R.id.imageButton0:
                String workoutName0 = "Chest And Triceps";
                workout0.putExtra("workoutName", workoutName0); // Pass data to next activity
                this.startActivity(workout0);
                break;
            case R.id.imageButton1:
//                Intent workout1 = new Intent(this.getActivity(), Workout1.class);
                String workoutName1 = "Back And Biceps";
                workout0.putExtra("workoutName",workoutName1); // Pass data to next activity
                this.startActivity(workout0);
                break;
            case R.id.imageButton2:
//                Intent workout2 = new Intent(this.getActivity(), Workout2.class);
                String workoutName2 = "Legs";
                workout0.putExtra("workoutName",workoutName2); // Pass data to next activity
                this.startActivity(workout0);
                break;
            case R.id.imageButton3:
//                Intent workout3 = new Intent(this.getActivity(), Workout3.class);
                String workoutName3 = "Shoulders";
                workout0.putExtra("workoutName",workoutName3); // Pass data to next activity
                this.startActivity(workout0);
                break;
        }
    }

}
