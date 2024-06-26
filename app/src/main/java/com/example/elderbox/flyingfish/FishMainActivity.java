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

    private FlyingFishClass gameView;
    private Handler handler = new Handler();
    private final static long Interval = 30;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new FlyingFishClass(this);
        setContentView(gameView);


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gameView.invalidate();
                    }
                });
            }
        }, 0, Interval);


        // Initialize MediaPlayer
        mediaPlayer = MediaPlayer.create(this, R.raw.vast_blue);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }


}