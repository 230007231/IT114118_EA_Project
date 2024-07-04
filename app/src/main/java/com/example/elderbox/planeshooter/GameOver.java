package com.example.elderbox.planeshooter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.elderbox.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GameOver extends Activity {

    // Declare TextViews for player name, score, and personal best
    TextView tvName, tvScore, tvPersonalBest;

    // Initialize pastScore to 0
    int pastScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plane_game_over);

        // Get the score from the intent extras
        int score1 = getIntent().getExtras().getInt("score");

        // Retrieve player name from SharedPreferences
        SharedPreferences pref = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String playerName = pref.getString("name", "");
        SharedPreferences.Editor editor = pref.edit();

        // Find TextViews by their IDs
        tvName = findViewById(R.id.tvName);
        tvScore = findViewById(R.id.tvScore);
        tvPersonalBest = findViewById(R.id.tvPersonalBest);

        // Set player name and score in TextViews
        tvName.setText(playerName);
        tvScore.setText(String.valueOf(score1));

        // Create a thread to handle network operations
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Create a URL for the data retrieval
                    URL url = new URL("http://10.0.2.2/login/GetData2.php");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);
                    connection.connect();

                    // Write player name to the output stream
                    DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("playername=").append(URLEncoder.encode(playerName, "UTF-8"));
                    outputStream.writeBytes(stringBuilder.toString());
                    outputStream.flush();
                    outputStream.close();

                    // Get the response code
                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // Read data from the input stream
                        InputStream inputStream = connection.getInputStream();
                        BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
                        String line;
                        while ((line = bufReader.readLine()) != null) {
                            JSONArray dataJson = new JSONArray(line);
                            int i = dataJson.length() - 1;
                            JSONObject test = dataJson.getJSONObject(i);
                            pastScore = test.getInt("score1");
                            if (score1 > pastScore) {
                                // Update pastScore if the current score is higher
                                pastScore = score1;
                                updateScoreInDatabase(playerName, pastScore);
                            }
                        }
                        inputStream.close();

                        // Update the UI on the main thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvPersonalBest.setText(String.valueOf(pastScore));
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void updateScoreInDatabase(String playerName, int score) {
        // Create a new thread to handle network operations
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    // Define the URL for sending data
                    URL url = new URL("http://10.0.2.2/login/SendData2.php");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);
                    connection.connect();

                    // Write player name and score to the output stream
                    DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("playername=").append(URLEncoder.encode(playerName, "UTF-8")).append("&");
                    stringBuilder.append("score1=").append(score);
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

                        // Process the server response (you can handle it as needed)
                        String serverResponse = response.toString();
                    } else {
                        // Handle other response codes (if necessary)
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void restart(View view){
        Intent intent = new Intent(GameOver.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void exit(View view){
        finish();
    }
}