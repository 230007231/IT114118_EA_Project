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




        TextView totalscore = (TextView) findViewById(R.id.totalscore);
        totalscore.setText(getString(R.string.score5) + String.valueOf(score4));

        Button home = (Button) findViewById(R.id.home);




        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(finddifference_score_page.this, Menu.class));
            }
        });

        Button select = (Button) findViewById(R.id.select);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(finddifference_score_page.this, Selectpage.class));
            }
        });




        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://10.0.2.2/login/GetData0.php");
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
                            pastScore4 = test.getInt("score4");
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
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    URL url = new URL("http://10.0.2.2/login/SendData0.php");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);
                    connection.connect();

                    DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("playername=").append(URLEncoder.encode(playerName, "UTF-8")).append("&");
                    stringBuilder.append("score4=").append(score);
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
}

