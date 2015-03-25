package naddateam.truform.functionality;

import android.app.Activity;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Erik on 3/18/2015.
 * This file is an example for getting the gif to work
 */
public class AnimationActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InputStream stream = null;
        try {
            stream = getAssets().open("piggy.gif");
        } catch (IOException e) {
            e.printStackTrace();
        }
        GifWebView view = new GifWebView(this, "file:///android_asset    /piggy.gif");

        setContentView(view);
    }
}
