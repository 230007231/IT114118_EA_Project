package com.example.elderbox.flyingfish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

public class GameOverActivity extends AppCompatActivity {

    private Button playAgainBtn, exitBtn;
    TextView tvName, tvScore, tvPersonalBest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_game_over);

        int score2 = getIntent().getExtras().getInt("score");

        SharedPreferences pref = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int scoreSP2 = pref.getInt("scoreSP2", 0);
        String playerName = pref.getString("name", ""); // if no, set blank as result
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("score2", score2);
        if(score2 > scoreSP2){
            scoreSP2 = score2;
            editor.putInt("scoreSP2", scoreSP2);
            editor.commit();
        }

        playAgainBtn = findViewById(R.id.play_again_button_id);
        exitBtn = findViewById(R.id.button2);

        tvName = findViewById(R.id.tvName);
        tvScore = findViewById(R.id.tvScore);
        tvPersonalBest = findViewById(R.id.tvPersonalBest);

        tvName.setText(""+playerName);
        tvScore.setText(""+score2);
        tvPersonalBest.setText(""+scoreSP2);


        int finalScoreSP = scoreSP2;
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                try {
                    URL url = new URL("http://10.0.2.2/login/SendData3.php");
                    // 開始宣告 HTTP 連線需要的物件，這邊通常都是一綑的
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);
                    connection.connect();

                    DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("name").append(URLEncoder.encode(String.valueOf(tvName), "UTF-8")).append("&");
                    stringBuilder.append("score2").append(finalScoreSP).append("&");
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




        playAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FishMainActivity.class);
                startActivity(intent);
            }
        });

            exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Selectpage.class);
                startActivity(intent);
            }
        });
    }
}