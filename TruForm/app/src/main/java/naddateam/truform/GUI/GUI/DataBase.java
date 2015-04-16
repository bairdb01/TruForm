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
import java.text.SimpleDateFormat;

/**
 * Created by Robbie on 2015-04-01.
 */
public class DataBase extends AsyncTask<String, Void, String> {
    private TextView title;
    private String fName, lName, uName;
    private int flag, eid, workout;
    private String email, form, time, weight, timeStart, timeEnd, height, age, gender, retString;

    public DataBase(String email, int flag)
    {
        this.email = email;
        this.flag = flag;
    }
    public DataBase(String email, String fName, String lName, String uName, int flag)
    {
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.uName = uName;
        this.flag = flag;
    }
    public DataBase(String email, int eid, String form, String time, String weight, int workout, int flag)
    {
        this.email = email;
        this.eid = eid;
        this.form = form;
        this.time = time;
        this.weight = weight;
        this.workout = workout;
        this.flag = flag;
    }
    public DataBase(String email, String timeStart, String timeEnd, int flag)
    {
        this.email = email;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.flag = flag;
    }
    public DataBase(String email, String height, String weight, String age, String gender, int flag)
    {
        this.email = email;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.gender = gender;
        this.flag = flag;
    }
    public DataBase(int flag)
    {
        this.flag = flag;
    }

    @Override
    protected String doInBackground(String... arg0) {
        String link;
        //selecting the user data from the db
        if(flag == 0) {
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
        //insert the user data into the db
        else if (flag == 1)
        {
            link = "http://131.104.49.65/insert.php?email=" + email + "&fname=" + fName + "&lname=" + lName + "&uname=" + uName;
            link = link.replace(" ", "");
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
        //insert the workout data into the db
        else if (flag == 2)
        {
            Log.v("VALUE OF TIME", time);
            link = "http://131.104.49.65/inPast.php?email=" + email + "&eid=" + eid + "&form=" + form + "&time=" + time + "&weight=" + weight + "&workout=" + workout;
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
        //select the the previous workout data from the db
        else if (flag == 3)
        {
            link = "http://131.104.49.65/selectPast.php?email=" + email + "&tstart=" + timeStart + "&tend=" + timeEnd;
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
        //insert User stats into the db
        else if (flag == 4)
        {
            link = "http://131.104.49.65/inStats.php?email=" + email + "&height=" + height + "&weight=" + weight + "&age=" + age + "&gender=" + gender;
            link = link.replace(" ", "");
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
        //select the user stats from the db
        else if (flag == 5)
        {
            link = "http://131.104.49.65/selectStats.php?email=" + email;
            link = link.replace(" ", "");
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
        else if (flag == 6)
        {
            link = "http://131.104.49.65/selectWorkout.php";
            link = link.replace(" ", "");
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
        if(flag == 0 || flag == 5 || flag == 3) {
            retString = result;
        }
    }
}
