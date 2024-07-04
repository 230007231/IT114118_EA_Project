package com.example.elderbox.planeshooter;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.elderbox.R;

public class StartGame extends Activity {

    // Declare a GameView instance
    GameView gameView;

    // Declare a MediaPlayer instance
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the window title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Set the window flags for fullscreen mode
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Keep the screen on
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // Create a new GameView and set it as the content view
        gameView = new GameView(this);
        setContentView(gameView);

        // Initialize MediaPlayer with a sound resource
        mediaPlayer = MediaPlayer.create(this, R.raw.please_heart);

        // Set the sound to loop indefinitely
        mediaPlayer.setLooping(true);

        // Start playing the sound
        mediaPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Pause the MediaPlayer if it's playing
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Resume playing the sound if it was paused
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }
}
