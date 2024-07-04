package com.example.elderbox;

import android.content.Context;
import android.media.MediaPlayer;

public class Music {
    private static MediaPlayer mp = null;

    public static void play(Context context, int resource) {
        stop(context); // Stop any currently playing music
        mp = MediaPlayer.create(context, resource); // Create a new MediaPlayer instance with the specified resource
        mp.setLooping(true); // Set the MediaPlayer to loop the music
        mp.start(); // Start playing the music
    }

    public static void stop(Context context) {
        // TODO Auto-generated method stub
        if (mp != null) { // If the MediaPlayer is not null (i.e., music is playing)
            mp.stop(); // Stop the music
            mp.release(); // Release the MediaPlayer resources
            mp = null; // Set the MediaPlayer reference to null
        }
    }
}

