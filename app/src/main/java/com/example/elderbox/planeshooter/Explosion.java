package com.example.elderbox.planeshooter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.elderbox.R;

public class Explosion {
    // Declare an array of Bitmaps to store explosion frames
    Bitmap explosion[] = new Bitmap[9];
    // Initialize explosion frame index
    int explosionFrame = 0;
    // Declare explosion coordinates
    int explosionX, explosionY;

    // Constructor for the Explosion class
    public Explosion(Context context) {
        // Load explosion frames from resources
        explosion[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.explosion0);
        explosion[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.explosion1);
        explosion[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.explosion2);
        explosion[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.explosion3);
        explosion[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.explosion4);
        explosion[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.explosion5);
        explosion[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.explosion6);
        explosion[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.explosion7);
        explosion[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.explosion8);
    }

    // Get the Bitmap for the specified explosion frame
    public Bitmap getExplosion(int explosionFrame) {
        return explosion[explosionFrame];
    }

    // Get the width of the explosion frame
    public int getExplosionWidth() {
        return explosion[0].getWidth();
    }

    // Get the height of the explosion frame
    public int getExplosionHeight() {
        return explosion[0].getHeight();
    }
}

