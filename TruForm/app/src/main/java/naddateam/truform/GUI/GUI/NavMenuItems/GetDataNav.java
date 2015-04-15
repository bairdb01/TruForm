/**
 * CIS3760
 * Naddateam Truform
 * Bluetooth.java
 * Source: https://developer.mbed.org/forum/wiki-16691-forum/topic/5134/
 * Last Modified by: Rob Little, Erik Hoffman
 * This File is used to Display basic database functionality
 */

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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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
    TextView title;
    EditText Email, fName, lName, uName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data_nav);

        lv = (ListView)findViewById(R.id.listView0);
        btnSR = (Button)findViewById(R.id.button);
        title = (TextView)findViewById(R.id.textView7);
        Email = (EditText) findViewById(R.id.emailTxt);
        fName = (EditText) findViewById(R.id.fnameTxt);
        lName = (EditText) findViewById(R.id.lnameTxt);
        uName = (EditText) findViewById(R.id.unameTxt);


        btnSR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String btnVal;

                btnVal = btnSR.getText().toString();

                if(btnVal.equals("Insert"))
                {
                    btnSR.setText("Select");
                    new DataBase(title, Email, fName, lName, uName, 1).execute();

                }
                if(btnVal.equals("Select"))
                {
                    btnSR.setText("Receive");
                    new DataBase(title).execute(Email.getText().toString());
                }
            }
        });

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
