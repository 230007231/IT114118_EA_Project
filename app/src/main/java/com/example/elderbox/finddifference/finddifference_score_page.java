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

    int score4; // Declare an integer variable called "score4"
    private int pastScore4 = 0; // Declare a private integer variable called "pastScore4" and initialize it to 0

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Enable edge-to-edge display (assuming "EdgeToEdge" is a custom utility class)
        setContentView(R.layout.finddifference_activity_score_page); // Set the content view to the specified layout

        // In NextActivity.java
        Bundle extras = getIntent().getExtras(); // Get the extras from the intent
        if (extras != null) {
            int receivedScore = extras.getInt("EXTRA_SCORE"); // Retrieve an integer extra with the key "EXTRA_SCORE"
            score4 = receivedScore; // Assign the received score to the "score4" variable
        }

        SharedPreferences pref = getSharedPreferences("MyPrefs", MODE_PRIVATE); // Get a reference to shared preferences with the name "MyPrefs"
        String playerName = pref.getString("name", ""); // Retrieve a string value associated with the key "name" (default value is an empty string)
        SharedPreferences.Editor editor = pref.edit(); // Get an editor to modify the shared preferences

        TextView totalscore = (TextView) findViewById(R.id.totalscore); // Find the TextView with the ID "totalscore"
        totalscore.setText(getString(R.string.score5) + String.valueOf(score4)); // Set the text of the TextView to a formatted string containing "score5" and the value of "score4"

        Button home = (Button) findViewById(R.id.home); // Find the Button with the ID "home"
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(finddifference_score_page.this, Menu.class)); // Start the Menu activity when the button is clicked
            }
        });

        Button select = (Button) findViewById(R.id.select); // Find the Button with the ID "select"
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(finddifference_score_page.this, Selectpage.class)); // Start the Selectpage activity when the button is clicked
            }
        });

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Create a URL object for the server endpoint
                    URL url = new URL("http://10.0.2.2/login/GetData0.php");

                    // Open an HTTP connection
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);
                    connection.connect();

                    // Prepare the data to be sent
                    DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("playername=").append(URLEncoder.encode(playerName, "UTF-8"));
                    outputStream.writeBytes(stringBuilder.toString());
                    outputStream.flush();
                    outputStream.close();

                    // Get the HTTP response code
                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // Read the response data
                        InputStream inputStream = connection.getInputStream();
                        BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
                        String line;
                        while ((line = bufReader.readLine()) != null) {
                            // Parse the JSON data
                            JSONArray dataJson = new JSONArray(line);
                            int i = dataJson.length() - 1;
                            JSONObject test = dataJson.getJSONObject(i);
                            pastScore4 = test.getInt("score4");

                            // Compare scores and update if necessary
                            if (score4 > pastScore4) {
                                pastScore4 = score4;
                                updateScoreInDatabase(playerName, pastScore4);
                            }
                        }
                        inputStream.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void updateScoreInDatabase(String playerName, int score) {
        // Create a new thread to perform the network operation
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    // Define the URL for the server
                    URL url = new URL("http://10.0.2.2/login/SendData0.php");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);
                    connection.connect();

                    // Prepare the data to be sent
                    DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("playername=").append(URLEncoder.encode(playerName, "UTF-8")).append("&");
                    stringBuilder.append("score4=").append(score);
                    outputStream.writeBytes(stringBuilder.toString());
                    outputStream.flush();
                    outputStream.close();

                    // Get the response code from the server
                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // Read the server response
                        InputStream inputStream = connection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
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
        thread.start();
    }
}