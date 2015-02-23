package naddateam.truform.GUI.GUI.workouts;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import naddateam.truform.GUI.GUI.BicepCurl;
import naddateam.truform.R;

public class Workout0 extends ActionBarActivity implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //String [] items = {"0","1","2","3","4","5"};
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_workout0, items);


        setContentView(R.layout.activity_workout0);
        ListView lv = (ListView)findViewById(R.id.listView0);
        //lv.setAdapter(adapter);

        lv.setOnItemClickListener(this);

    }

    @Override
    /**
     * Opens the exercise screen
     */
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), "Pressed" + position, Toast.LENGTH_LONG).show();
        switch(position){
            case(0):
                Intent exercise0 = new Intent(this ,BicepCurl.class);
                startActivity(exercise0);
                break;
            case(1):

                break;

            case(2):

                break;

            case(3):
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_workout0, menu);
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



}
