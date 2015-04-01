package naddateam.truform.GUI.GUI.NavMenuItems;

import android.os.AsyncTask;
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

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import naddateam.truform.GUI.GUI.Bluetooth;
import naddateam.truform.GUI.GUI.BluetoothLeUart;
import naddateam.truform.GUI.GUI.DataBase;
import naddateam.truform.R;
import naddateam.truform.functionality.ExerciseAnalysis;
import naddateam.truform.functionality.InstanceData;

public class GetDataNav extends ActionBarActivity {
    ExerciseAnalysis exerciseAnalysis = new ExerciseAnalysis();
    Bluetooth ble = new Bluetooth();
    ListView lv;
    Button btnSR;
    JSONObject json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data_nav);

        lv = (ListView)findViewById(R.id.listView0);
        btnSR = (Button)findViewById(R.id.button);



        btnSR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*BluetoothLeUart Comm = ble.getmService();
                byte[] value;
                String message = "G";*/
                String retVal = "";
                String retVal2 = "";
                String btnVal;

                btnVal = btnSR.getText().toString();

                if(btnVal.equals("Receive"))
                {
                    /*ble.dataArr.clear();
                    value = message.getBytes();
                    Comm.writeRXCharacteristic(value);*/
                    btnSR.setText("Stop");
                    //exerciseAnalysis.form.clear();



                }
                if(btnVal.equals("Stop"))
                {
                    /*message = "N";
                    value = message.getBytes();
                    Comm.writeRXCharacteristic(value);*/
                    btnSR.setText("Receive");
                    //exerciseAnalysis.analyzeForm(ble.dataArr);
                    displayList();
                    new DataBase().execute();
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
