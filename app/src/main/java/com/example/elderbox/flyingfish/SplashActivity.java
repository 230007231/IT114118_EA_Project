package com.example.elderbox.flyingfish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.elderbox.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_start);

        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1000);
                }catch (Exception e) {
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashActivity.this, FishMainActivity.class);
                    startActivity(intent);
                }
            }
        };
        thread.start();
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}