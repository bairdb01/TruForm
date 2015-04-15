package naddateam.truform.GUI.GUI.SettingsItems.HelpTutorials;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;

import naddateam.truform.functionality.GifWebView;

public class BicepCurlTut extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InputStream stream = null;
        try {
            stream = getAssets().open("db_curl.gif");
        } catch (IOException e) {
            e.printStackTrace();
        }
        GifWebView view = new GifWebView(this, "file:///android_asset/db_curl.gif");

        setContentView(view);
    }
}
