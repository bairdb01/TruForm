package naddateam.truform.GUI.GUI.NavMenuItems;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import naddateam.truform.GUI.GUI.Bluetooth;
import naddateam.truform.GUI.GUI.BluetoothLeUart;
import naddateam.truform.R;
import naddateam.truform.functionality.ExerciseAnalysis;
import naddateam.truform.functionality.InstanceData;

public class GetDataNav extends ActionBarActivity {
    ExerciseAnalysis exerciseAnalysis = new ExerciseAnalysis();
    Bluetooth ble = new Bluetooth();
    ListView lv;
    Button btnSR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data_nav);

        lv = (ListView)findViewById(R.id.listView0);
        btnSR = (Button)findViewById(R.id.button);

        btnSR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothLeUart Comm = ble.getmService();
                byte[] value;
                String message = "G";
                String btnVal;

                btnVal = btnSR.getText().toString();

                if(btnVal.equals("Receive"))
                {
                    ble.dataArr.clear();
                    value = message.getBytes();
                    Comm.writeRXCharacteristic(value);
                    btnSR.setText("Stop");
                    exerciseAnalysis.form.clear();
                }
                if(btnVal.equals("Stop"))
                {
                    message = "N";
                    value = message.getBytes();
                    Comm.writeRXCharacteristic(value);
                    btnSR.setText("Receive");
                    exerciseAnalysis.analyzeForm(ble.dataArr, 5);
                    displayList();
                }
            }
        });

    }

    private void displayList()
    {
        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                exerciseAnalysis.form );
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
