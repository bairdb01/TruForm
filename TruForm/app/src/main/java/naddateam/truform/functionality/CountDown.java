/**
 * CIS3760
 * Naddateam Truform
 * CountDown.java
 * Author: Benjamin Baird
 * Last Modified: March 3, 2015
 * Description: Provides a timer that counts down
 */

package naddateam.truform.functionality;

import android.widget.TextView;

/**
 * Created by Ben on 3/3/2015.
 */
public class CountDown {

    public CountDown() {

    }

    /**
     * Rest timer begins
     */
    public void startTimer(final TextView textV) {
        new android.os.CountDownTimer(30000,10) {
            TextView timer = (TextView) textV;
            public void onTick(long millisUntilFinished) {
                long secondsRemaining = millisUntilFinished/1000;
                long decimal = (millisUntilFinished - (secondsRemaining*1000)) / 10;
                timer.setText("" + secondsRemaining + "." + (int) decimal + "s");
            }

            public void onFinish() {
                timer.setText("Rest Over!");
            }
        }.start();
    }
}
