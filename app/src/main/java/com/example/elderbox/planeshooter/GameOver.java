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

    TextView tvName, tvScore, tvPersonalBest;

    int pastScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plane_game_over);

        int score1 = getIntent().getExtras().getInt("score");

        SharedPreferences pref = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        String playerName = pref.getString("name", "");
        SharedPreferences.Editor editor = pref.edit();

        tvName = findViewById(R.id.tvName);
        tvScore = findViewById(R.id.tvScore);
        tvPersonalBest = findViewById(R.id.tvPersonalBest);

        tvName.setText(playerName);
        tvScore.setText(String.valueOf(score1));

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://10.0.2.2/login/GetData2.php");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);
                    connection.connect();
                    DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("playername=").append(URLEncoder.encode(playerName, "UTF-8"));
                    outputStream.writeBytes(stringBuilder.toString());
                    outputStream.flush();
                    outputStream.close();

                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        InputStream inputStream = connection.getInputStream();
                        BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
                        String line;
                        while ((line = bufReader.readLine()) != null) {
                            JSONArray dataJson = new JSONArray(line);
                            int i = dataJson.length() - 1;
                            JSONObject test = dataJson.getJSONObject(i);
                            pastScore = test.getInt("score1");
                            if (score1 > pastScore) {
                                pastScore = score1;
                                updateScoreInDatabase(playerName, pastScore);
                            }
                        }
                        inputStream.close();
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
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    URL url = new URL("http://10.0.2.2/login/SendData2.php");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);
                    connection.connect();

                    DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("playername=").append(URLEncoder.encode(playerName, "UTF-8")).append("&");
                    stringBuilder.append("score1=").append(score);
                    outputStream.writeBytes(stringBuilder.toString());
                    outputStream.flush();
                    outputStream.close();

                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
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

    public void restart(View view){
        Intent intent = new Intent(GameOver.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void exit(View view){
        finish();
    }
}