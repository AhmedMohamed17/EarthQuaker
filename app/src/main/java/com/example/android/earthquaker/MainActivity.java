package com.example.android.earthquaker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    TextView mag, time, first, second, date;
    final ArrayList<earth> earthquakes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mag = findViewById(R.id.mag);
        time = findViewById(R.id.time);
        date = findViewById(R.id.date);
        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        if (isNetworkAvailable()) {
            Toast.makeText(this, "please wait untill the data is extracted \n time depends on your connection speed ", Toast.LENGTH_LONG).show();

// Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";

// Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                JSONObject object = new JSONObject(response);
                                JSONArray features = object.getJSONArray("features");
                                for (int i = 0; i < features.length(); i++) {

                                    Double mag = object.getJSONArray("features").getJSONObject(i).getJSONObject("properties").getDouble("mag");
                                    Long tim = object.getJSONArray("features").getJSONObject(i).getJSONObject("properties").getLong("time");
                                    SimpleDateFormat fmtD = new SimpleDateFormat("dd-MM-yyyy");
                                    Date date = new Date(tim);
                                    String datee = fmtD.format(date);
                                    SimpleDateFormat fmtt = new SimpleDateFormat("hh:mm a");
                                    String timee = fmtt.format(date);
                                    String url = object.getJSONArray("features").getJSONObject(i).getJSONObject("properties").getString("url");
                                    String place = object.getJSONArray("features").getJSONObject(i).getJSONObject("properties").getString("place");
                                    String first = "";
                                    String second = "";
                                    if (place.contains("of")) {
                                        String[] parts = place.split("of");
                                        first = parts[0] + " of";
                                        second = parts[1];
                                    } else {
                                        first = " near the ";
                                        second = place;
                                    }
                                    earthquakes.add(new earth("" + mag, first, second, datee, timee, url));


                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) { // we need to check here for any errors
                Log.e("error",error.getMessage());

                }
            });

// Add the request to the RequestQueue.
            queue.add(stringRequest);

            // Find a reference to the {@link ListView} in the layout
            // Find a reference to the {@link ListView} in the layout
            ListView earthquakeListView = (ListView) findViewById(R.id.main);
            final my_adapter adapter = new my_adapter(this, earthquakes);
            earthquakeListView.setAdapter(adapter);
            earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(earthquakes.get(i).getUrl())));
                }
            });
        }
        else
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("check network connectivity");
                builder.setMessage("app can't work without network connectivity please check your connectivity and re run the app");
                builder.setPositiveButton("close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        finish();

                    }
                });
                builder.show();

            }
    }
    private boolean isNetworkAvailable() {
        Log.d("x", "isNetworkAvailable: ");
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
