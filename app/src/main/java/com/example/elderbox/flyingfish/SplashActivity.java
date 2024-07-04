package com.example.elderbox.flyingfish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.elderbox.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_start); // Set the content view to the layout "activity_fish_start"

        Thread thread = new Thread(){ // Create a new thread
            @Override
            public void run() {
                try {
                    sleep(1000); // Pause execution for 1000 milliseconds (1 second)
                } catch (Exception e) {
                    e.printStackTrace(); // Print any exceptions that occur
                } finally {
                    Intent intent = new Intent(SplashActivity.this, FishMainActivity.class); // Create an intent to start the FishMainActivity
                    startActivity(intent); // Start the activity
                }
            }
        };
        thread.start(); // Start the thread
    }

    @Override
    protected void onPause() {
        super.onPause(); // Call the superclass onPause method
        finish(); // Finish the activity
    }
}
