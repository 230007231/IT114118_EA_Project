package com.example.elderbox.finddifference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.elderbox.Menu;
import com.example.elderbox.R;
import com.example.elderbox.Selectpage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;


public class finddifference_score_page extends AppCompatActivity {

    int score4;

    private int pastScore4 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.finddifference_activity_score_page);


        // In NextActivity.java
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int receivedScore = extras.getInt("EXTRA_SCORE");
            score4=receivedScore;
        }



        SharedPreferences pref = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String playerName = pref.getString("name", ""); // if no, set blank as result
        SharedPreferences.Editor editor = pref.edit();




        // Assign the 'home' button from the layout to the 'home' variable
        Button home = (Button) findViewById(R.id.home);

// Set a click listener for the 'home' button
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start the 'Menu' activity
                startActivity(new Intent(finddifference_score_page.this, Menu.class));
            }
        });

// Assign the 'select' button from the layout to the 'select' variable
        Button select = (Button) findViewById(R.id.select);

// Set a click listener for the 'select' button
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start the 'Selectpage' activity
                startActivity(new Intent(finddifference_score_page.this, Selectpage.class));
            }
        });




        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Create a URL object with the specified URL
                    URL url = new URL("http://10.0.2.2/login/GetData0.php");

                    // Open a connection to the specified URL
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    // Set the request method to POST
                    connection.setRequestMethod("POST");

                    // Enable output and input streams for the connection
                    connection.setDoOutput(true);
                    connection.setDoInput(true);

                    // Disable caching
                    connection.setUseCaches(false);

                    // Connect to the remote server
                    connection.connect();

                    // Create a DataOutputStream to write data to the connection's output stream
                    DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());

                    // Create a StringBuilder to construct the request parameters
                    StringBuilder stringBuilder = new StringBuilder();

                    // Append the 'playername' parameter to the StringBuilder
                    stringBuilder.append("playername=").append(URLEncoder.encode(playerName, "UTF-8"));

                    // Write the request parameters to the output stream
                    outputStream.writeBytes(stringBuilder.toString());

                    // Flush and close the output stream
                    outputStream.flush();
                    outputStream.close();

                    // Get the response code from the connection
                    int responseCode = connection.getResponseCode();

                    // If the response code is HTTP_OK (200)
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // Get the input stream from the connection
                        InputStream inputStream = connection.getInputStream();

                        // Create a BufferedReader to read the input stream
                        BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);

                        String line;

                        // Read each line from the input stream
                        while ((line = bufReader.readLine()) != null) {
                            // Create a JSONArray from the line
                            JSONArray dataJson = new JSONArray(line);

                            // Get the last element from the JSONArray
                            int i = dataJson.length() - 1;
                            JSONObject test = dataJson.getJSONObject(i);

                            // Retrieve the 'score4' value from the JSONObject
                            pastScore4 = test.getInt("score4");

                            // Compare the current 'score4' with the 'pastScore4'
                            if (score4 > pastScore4) {
                                // Update 'pastScore4' with 'score4'
                                pastScore4 = score4;

                                // Call the 'updateScoreInDatabase' method with the updated score
                                updateScoreInDatabase(playerName, pastScore4);
                            }
                        }

                        // Close the input stream
                        inputStream.close();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

// Start the thread
        thread.start();
    }

    private void updateScoreInDatabase(String playerName, int score) {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    // Create a URL object with the specified URL
                    URL url = new URL("http://10.0.2.2/login/SendData0.php");

                    // Open a connection to the specified URL
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    // Set the request method to POST
                    connection.setRequestMethod("POST");

                    // Enable output and input streams for the connection
                    connection.setDoOutput(true);
                    connection.setDoInput(true);

                    // Disable caching
                    connection.setUseCaches(false);

                    // Connect to the remote server
                    connection.connect();

                    // Create a DataOutputStream to write data to the connection's output stream
                    DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());

                    // Create a StringBuilder to construct the request parameters
                    StringBuilder stringBuilder = new StringBuilder();

                    // Append the 'playername' parameter to the StringBuilder
                    stringBuilder.append("playername=").append(URLEncoder.encode(playerName, "UTF-8")).append("&");

                    // Append the 'score4' parameter to the StringBuilder
                    stringBuilder.append("score4=").append(score);

                    // Write the request parameters to the output stream
                    outputStream.writeBytes(stringBuilder.toString());

                    // Flush and close the output stream
                    outputStream.flush();
                    outputStream.close();

                    // Get the response code from the connection
                    int responseCode = connection.getResponseCode();

                    // If the response code is HTTP_OK (200)
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // Get the input stream from the connection
                        InputStream inputStream = connection.getInputStream();

                        // Create a BufferedReader to read the input stream
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                        StringBuilder response = new StringBuilder();
                        String line;

                        // Read each line from the input stream and append it to the response StringBuilder
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }

                        // Close the reader and input stream
                        reader.close();
                        inputStream.close();

                        // Handle server response if needed
                        String serverResponse = response.toString();
                        // Update UI or log the response if necessary
                    } else {
                        // Handle non-OK response
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // Start the thread
        thread.start();
    }
}

