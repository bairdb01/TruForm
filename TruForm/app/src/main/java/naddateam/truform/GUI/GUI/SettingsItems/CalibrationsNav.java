package naddateam.truform.GUI.GUI.SettingsItems;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import naddateam.truform.R;

public class CalibrationsNav extends ActionBarActivity implements AdapterView.OnItemClickListener{

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calibrations_layout);
        ListView lv = (ListView)findViewById(R.id.calibrationListView);

        //String [] items = {"0","1","2","3","4","5"};
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_workout0, items);


        lv.setOnItemClickListener(this);

        }

@Override
/**
 * Opens the exercise screen
 */
public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            Toast.makeText(getApplicationContext(), "Bicep Curl", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calibrations, menu);
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
