package com.example.elderbox.finddifference;

// BackgroundSoundService.java
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.example.elderbox.R;

public class finddifference_backgroundMusic extends Service {
    private MediaPlayer player;

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.opensound); // Replace with your music file
        player.setLooping(true); // Set looping
        player.setVolume(1.0f, 1.0f); // Set volume (range: 0.0 to 1.0)
        player.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.stop();
            player.release();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
