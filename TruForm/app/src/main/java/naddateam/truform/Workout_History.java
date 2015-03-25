package naddateam.truform;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;


public class Workout_History extends ActionBarActivity  implements AdapterView.OnItemClickListener{

    ListView lv;
    String workoutName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout__history);
        lv = (ListView)findViewById(R.id.exercise_history);


        //Retrieving data passed from previous activity
        Bundle variables = getIntent().getExtras();
        if (variables != null) {
            workoutName = variables.getString("workoutName");
        }
        setTitle(workoutName);

        //Read workout file
        ArrayList allExercise = new ArrayList();
        try {
            File file = new File(getFilesDir(), workoutName);
            BufferedReader bf = new BufferedReader(new FileReader(file));
            //Get exercise data from file
            String line = "test";
            while ((line = bf.readLine()) != null) {
                //if (line.matches(".*\\d+.*")){
//                    Toast.makeText(this, "added line", Toast.LENGTH_SHORT).show();
                    allExercise.add(line);
                //}
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this,"Error loading workout file",Toast.LENGTH_SHORT);
        }

//        for (int i = 0; i < allExercise.size(); i ++) {
//            Toast.makeText(this, String.valueOf(allExercise.get(i)),Toast.LENGTH_SHORT).show();
//        }

        // Populate list
        String [] exList = new String[allExercise.size()];
        allExercise.toArray(exList);
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, exList));
        lv.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_workout__history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
