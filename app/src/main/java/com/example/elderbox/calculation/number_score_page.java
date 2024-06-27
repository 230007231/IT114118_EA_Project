package com.example.elderbox.calculation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.elderbox.R;
import com.example.elderbox.Selectpage;

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


public class number_score_page extends AppCompatActivity {

    int score3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.number_activity_score_page);


        // In NextActivity.java
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int receivedScore = extras.getInt("EXTRA_SCORE");
            score3=receivedScore;
        }


        TextView totalscore = (TextView) findViewById(R.id.totalscore);
        totalscore.setText("分數: " + String.valueOf(score3));

        Button home = (Button) findViewById(R.id.home);





        SharedPreferences pref = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int scoreSP3 = pref.getInt("scoreSP3", 0);
        String playerName = pref.getString("name", ""); // if no, set blank as result
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("score3", score3);
        if(score3 > scoreSP3){
            scoreSP3 = score3;
            editor.putInt("scoreSP3", scoreSP3);
            editor.apply();
        }






        int finalScoreSP = scoreSP3;
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                try {
                    URL url = new URL("http://10.0.2.2/login/SendData1.php");
                    // 開始宣告 HTTP 連線需要的物件，這邊通常都是一綑的
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);
                    connection.connect();

                    DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("playername=").append(URLEncoder.encode(playerName, "UTF-8")).append("&");
                    stringBuilder.append("score3=").append(finalScoreSP).append("&");
                    outputStream.writeBytes(stringBuilder.toString());
                    outputStream.flush();
                    outputStream.close();

                    // 在這裡可以處理服務器的響應（可選）
                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // 读取服务器的响应数据
                        InputStream inputStream = connection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                        String line;
                        StringBuilder response = new StringBuilder();
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        reader.close();
                        inputStream.close();

                        // 在这里可以处理服务器的响应数据
                        String serverResponse = response.toString();
                        // 根据需要进行处理，例如更新UI等
                        // ...

                    }

                } catch (ProtocolException e) {
                    throw new RuntimeException(e);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });







        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(number_score_page.this, number_menu.class));
            }
        });

        Button select = (Button) findViewById(R.id.select);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(number_score_page.this, Selectpage.class));
            }
        });
        thread1.start();

    }
}