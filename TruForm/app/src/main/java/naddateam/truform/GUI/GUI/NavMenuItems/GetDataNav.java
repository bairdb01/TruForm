package naddateam.truform.GUI.GUI.NavMenuItems;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import naddateam.truform.GUI.GUI.Bluetooth;
import naddateam.truform.R;
import naddateam.truform.functionality.ExerciseAnalysis;
import naddateam.truform.functionality.InstanceData;

public class GetDataNav extends ActionBarActivity {
    ExerciseAnalysis exerciseAnalysis = new ExerciseAnalysis();
    Bluetooth stuff = new Bluetooth();
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data_nav);
        exerciseAnalysis.analyzeForm(stuff.dataArr, 4);
        lv = (ListView)findViewById(R.id.listView0);
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer> (
            this,
            android.R.layout.simple_list_item_1,
            exerciseAnalysis.form ) ;
        lv.setAdapter(arrayAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_data_nav, menu);
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
