/**
 * CIS3760
 * Naddateam Truform
 * DataBase.java
 * Last Modified by: Rob Little
 * This File is used to connect to the Database
 */

package naddateam.truform.GUI.GUI;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.sql.Time;

/**
 * Created by Robbie on 2015-04-01.
 */
public class DataBase extends AsyncTask<String, Void, String> {
    private TextView title;
    private EditText Email, fName, lName, uName;
    private int flag;

    public DataBase(TextView title)
    {
        this.title = title;
        this.flag = 0;
    }
    public DataBase(TextView title, EditText Email, EditText fName, EditText lName, EditText uName, int flag)
    {
        this.title = title;
        this.Email = Email;
        this.fName = fName;
        this.lName = lName;
        this.uName = uName;
        this.flag = flag;
    }
    public DataBase(String email, String eid, String form, Time time)
    {
        
    }

    @Override
    protected String doInBackground(String... arg0) {
        String link;
        if(flag == 0) {
            String email = (String)arg0[0];
            link = "http://131.104.49.65/show.php?email=" + email;
            try {
                URL url = new URL(link);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                Log.v("Testing", sb.toString());
                in.close();
                return sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (flag == 1)
        {
            String email = (String)arg0[0];
            String first = (String)arg0[1];
            String last = (String)arg0[2];
            String usr = (String)arg0[3];
            link = "http://131.104.49.65/db_config.php?email=" + email + "&fname=" + first + "&lname=" + last + "&uname=" + usr;

            try {
                URL url = new URL(link);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                Log.v("Testing", sb.toString());
                in.close();
                return sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result){
        if(flag == 0) {
            this.title.setText(result);
        }
    }
}
