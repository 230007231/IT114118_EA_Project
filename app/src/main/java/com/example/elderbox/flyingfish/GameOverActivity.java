package com.example.elderbox.flyingfish;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
import java.net.URL;
import java.net.URLEncoder;

public class GameOverActivity extends AppCompatActivity {

    // Declare buttons and text views
    private Button playAgainBtn, exitBtn;
    private TextView tvName, tvScore, tvPersonalBest;

    // Initialize a variable to store past score
    private int pastScore2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_game_over);

        // Get the score from the intent extras
        int score2 = getIntent().getExtras().getInt("score");

        // Retrieve player name from shared preferences
        SharedPreferences pref = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String playerName = pref.getString("name", ""); // If no name, set blank as result
        SharedPreferences.Editor editor = pref.edit();

        // Initialize UI elements
        playAgainBtn = findViewById(R.id.play_again_button_id);
        exitBtn = findViewById(R.id.button2);
        tvName = findViewById(R.id.tvName);
        tvScore = findViewById(R.id.tvScore);
        tvPersonalBest = findViewById(R.id.tvPersonalBest);

        // Set player name and score on text views
        tvName.setText(playerName);
        tvScore.setText(String.valueOf(score2));

        // Set click listener for play again button
        playAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameOverActivity.this, FishMainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Set click listener for exit button
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameOverActivity.this, Selectpage.class);
                startActivity(intent);
                finish();
            }
        });

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Create a URL object for the server endpoint
                    URL url = new URL("http://10.0.2.2/login/GetData3.php");

                    // Open an HTTP connection
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);
                    connection.connect();

                    // Set up an output stream to write data to the server
                    DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("playername=").append(URLEncoder.encode(playerName, "UTF-8"));
                    outputStream.writeBytes(stringBuilder.toString());
                    outputStream.flush();
                    outputStream.close();

                    // Get the HTTP response code
                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // Read data from the server
                        InputStream inputStream = connection.getInputStream();
                        BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
                        String line;
                        while ((line = bufReader.readLine()) != null) {
                            // Parse the JSON data
                            JSONArray dataJson = new JSONArray(line);
                            int i = dataJson.length() - 1;
                            JSONObject test = dataJson.getJSONObject(i);
                            pastScore2 = test.getInt("score2");

                            // Update the score if it's higher than the previous best
                            if (score2 > pastScore2) {
                                pastScore2 = score2;
                                updateScoreInDatabase(playerName, pastScore2);
                            }
                        }
                        inputStream.close();

                        // Update the UI on the main thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvPersonalBest.setText(String.valueOf(pastScore2));
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
        // Create a new thread to perform the network operation
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    // Define the URL for the server
                    URL url = new URL("http://10.0.2.2/login/SendData3.php");
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
                    stringBuilder.append("score2=").append(score);
                    outputStream.writeBytes(stringBuilder.toString());
                    outputStream.flush();
                    outputStream.close();

                    // Get the server response code
                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // Read the response from the server
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