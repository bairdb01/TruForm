package naddateam.truform.GUI.GUI;

import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import naddateam.truform.R;

public class BicepCurl extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bicep_curl);
        startTimer();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bicep_curl, menu);
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

    public void startTimer() {
        new CountDownTimer(30000,10) {
            TextView restTime = (TextView) findViewById(R.id.restTime);
            public void onTick(long millisUntilFinished) {
                long secondsRemaining = millisUntilFinished/1000;
                long decimal = (millisUntilFinished - (secondsRemaining*1000)) / 10;
                restTime.setText("" + secondsRemaining + "." + (int)decimal);
            }

            public void onFinish() {
                restTime.setText("Rest Over!");
            }
        }.start();
    }
}
