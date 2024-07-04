package com.example.elderbox.flyingfish;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import com.example.elderbox.R;
import com.example.elderbox.flyingfish.FlyingFishClass;

import java.util.Timer;
import java.util.TimerTask;

public class FishMainActivity extends AppCompatActivity {

    private FlyingFishClass gameView; // Reference to the game view
    private Handler handler = new Handler(); // Handler for updating the game view
    private final static long Interval = 30; // Update interval in milliseconds
    private MediaPlayer mediaPlayer; // MediaPlayer for playing background music

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new FlyingFishClass(this); // Create a new game view
        setContentView(gameView); // Set the content view to the game view

        // Set up a timer to update the game view at regular intervals
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gameView.invalidate(); // Invalidate the game view to trigger redraw
                    }
                });
            }
        }, 0, Interval);

        // Initialize MediaPlayer with background music
        mediaPlayer = MediaPlayer.create(this, R.raw.vast_blue);
        mediaPlayer.setLooping(true); // Loop the music
        mediaPlayer.start(); // Start playing the music
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause(); // Pause the music when the activity is paused
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start(); // Resume playing the music when the activity is resumed
        }
    }
}
