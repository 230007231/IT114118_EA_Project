package com.example.elderbox.record;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.elderbox.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Record extends AppCompatActivity {

    TextView tvName, tvBest1, tvBest2, tvBest3, tvBest4;

    String playerName;
    int scoreSP, scoreSP2, scoreSP3, scoreSP4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        tvName = findViewById(R.id.tvName);
        tvBest1 = findViewById(R.id.tvBest1);
        tvBest2 = findViewById(R.id.tvBest2);
        tvBest3 = findViewById(R.id.tvBest3);
        tvBest4 = findViewById(R.id.tvBest4);


        SharedPreferences pref = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        playerName = pref.getString("name", ""); // if no, set blank as result


        tvName.setText(playerName);


        fetchScoresFromServer(playerName);
    }

    private void fetchScoresFromServer(String playerName) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://10.0.2.2/login/GetScores.php");
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
                        StringBuilder responseStrBuilder = new StringBuilder();
                        String line;
                        while ((line = bufReader.readLine()) != null) {
                            responseStrBuilder.append(line);
                        }
                        inputStream.close();


                        JSONArray dataJson = new JSONArray(responseStrBuilder.toString());
                        JSONObject jsonObject = dataJson.getJSONObject(0);
                        scoreSP = jsonObject.getInt("score1");
                        scoreSP2 = jsonObject.getInt("score2");
                        scoreSP3 = jsonObject.getInt("score3");
                        scoreSP4 = jsonObject.getInt("score4");


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvBest1.setText(String.valueOf(scoreSP));
                                tvBest2.setText(String.valueOf(scoreSP2));
                                tvBest3.setText(String.valueOf(scoreSP3));
                                tvBest4.setText(String.valueOf(scoreSP4));
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

    public void exit(View view) {
        Intent intent = new Intent(Record.this, com.example.elderbox.Menu.class);
        startActivity(intent);
        finish();
    }
}