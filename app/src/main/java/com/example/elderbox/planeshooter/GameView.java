package com.example.elderbox.planeshooter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import com.example.elderbox.R;

import java.util.ArrayList;

public class GameView extends View {

    // Declare variables
    Bitmap background, tank;
    Rect rect;
    static int dWidth, dHeight;
    ArrayList<Plane> planes, planes2;
    ArrayList<Missile> missiles;
    ArrayList<Explosion> explosions;
    Handler handler;
    Runnable runnable;
    final long UPDATE_MILLIS = 30;
    static int tankWidth, tankHeight;
    Context context;
    int count = 0;
    SoundPool sp;
    int fire = 0, point = 0;
    Paint scorePaint, healthPaint;
    final int TEXT_SIZE = 100;
    int life = 10;

    // Constructor
    public GameView(Context context) {
        super(context);
        this.context = context;

        // Load background and tank images
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        tank = BitmapFactory.decodeResource(getResources(), R.drawable.tank);

        // Get display size
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dWidth = size.x;
        dHeight = size.y;
        rect = new Rect(0, 0, dWidth, dHeight);

        // Initialize lists
        planes = new ArrayList<>();
        planes2 = new ArrayList<>();
        missiles = new ArrayList<>();
        explosions = new ArrayList<>();

        // Create planes
        for (int i = 0; i < 2; i++) {
            Plane plane = new Plane(context);
            planes.add(plane);
            Plane2 plane2 = new Plane2(context);
            planes2.add(plane2);
        }

        // Set up handler
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };

        // Get tank dimensions
        tankWidth = tank.getWidth();
        tankHeight = tank.getHeight();

        // Initialize SoundPool
        sp = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        fire = sp.load(context, R.raw.fire, 1);
        point = sp.load(context, R.raw.point, 1);

        // Set up score and health paints
        scorePaint = new Paint();
        scorePaint.setColor(Color.RED);
        scorePaint.setTextSize(TEXT_SIZE);
        scorePaint.setTextAlign(Paint.Align.LEFT);
        healthPaint = new Paint();
        healthPaint.setColor(Color.GREEN);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(background, null, rect, null);

        // Draw the first set of planes
        for (int i = 0; i < planes.size(); i++) {
            canvas.drawBitmap(planes.get(i).getBitmap(), planes.get(i).planeX, planes.get(i).planeY, null);
            planes.get(i).planeFrame++;
            if (planes.get(i).planeFrame > 14) {
                planes.get(i).planeFrame = 0;
            }
            planes.get(i).planeX -= planes.get(i).velocity;

            // Check if the plane is out of bounds
            if (planes.get(i).planeX < -planes.get(i).getWidth()) {
                planes.get(i).resetPosition();
                life--;

                // Check if player has run out of lives
                if (life == 0) {
                    Intent intent = new Intent(context, GameOver.class);
                    intent.putExtra("score", (count * 10));
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }
            }

            // Draw the second set of planes
            canvas.drawBitmap(planes2.get(i).getBitmap(), planes2.get(i).planeX, planes2.get(i).planeY, null);
            planes2.get(i).planeFrame++;
            if (planes2.get(i).planeFrame > 9) {
                planes2.get(i).planeFrame = 0;
            }
            planes2.get(i).planeX += planes2.get(i).velocity;

            // Check if the second plane is out of bounds
            if (planes2.get(i).planeX > (dWidth + planes2.get(i).getWidth())) {
                planes2.get(i).resetPosition();
                life--;

                // Check if player has run out of lives
                if (life == 0) {
                    Intent intent = new Intent(context, GameOver.class);
                    intent.putExtra("score", (count * 10));
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }
            }
        }

        for (int i = 0; i < missiles.size(); i++) {
            // Update missile position
            if (missiles.get(i).y > -missiles.get(i).getMissileHeight()) {
                missiles.get(i).y -= missiles.get(i).mVelocity;
                canvas.drawBitmap(missiles.get(i).missile, missiles.get(i).x, missiles.get(i).y, null);

                // Check for collision with plane
                if (missiles.get(i).x >= planes.get(0).planeX &&
                        (missiles.get(i).x + missiles.get(i).getMissileWidth()) <= (planes.get(0).planeX + planes.get(0).getWidth()) &&
                        missiles.get(i).y >= planes.get(0).planeY &&
                        missiles.get(i).y <= (planes.get(0).planeY + planes.get(0).getHeight())) {
                    // Create explosion
                    Explosion explosion = new Explosion(context);
                    explosion.explosionX = planes.get(0).planeX + planes.get(0).getWidth() / 2 - explosion.getExplosionWidth() / 2;
                    explosion.explosionY = planes.get(0).planeY + planes.get(0).getHeight() / 2 - explosion.getExplosionHeight() / 2;
                    explosions.add(explosion);

                    // Reset plane position
                    planes.get(0).resetPosition();
                    count++;

                    // Remove missile
                    missiles.remove(i);

                    // Play sound effect
                    if (point != 0) {
                        sp.play(point, 1, 1, 0, 0, 1);
                    }

                } else if (missiles.get(i).x >= planes.get(1).planeX && (missiles.get(i).x + missiles.get(i).getMissileWidth())
                        <= (planes.get(1).planeX + planes.get(1).getWidth()) && missiles.get(i).y >= planes.get(1).planeY &&
                        missiles.get(i).y <= (planes.get(1).planeY + planes.get(1).getHeight())) {
                    // Create an explosion at the center of the second plane
                    Explosion explosion = new Explosion(context);
                    explosion.explosionX = planes.get(1).planeX + planes.get(1).getWidth() / 2 - explosion.getExplosionWidth() / 2;
                    explosion.explosionY = planes.get(1).planeY + planes.get(1).getHeight() / 2 - explosion.getExplosionHeight() / 2;
                    explosions.add(explosion);

                    // Reset the position of the second plane
                    planes.get(1).resetPosition();

                    // Increment the count
                    count++;

                    // Remove the missile
                    missiles.remove(i);

                    // Play a sound effect if the point is not zero
                    if (point != 0) {
                        sp.play(point, 1, 1, 0, 0, 1);
                    }


                } else if (missiles.get(i).x >= planes2.get(0).planeX && (missiles.get(i).x + missiles.get(i).getMissileWidth())
                        <= (planes2.get(0).planeX + planes2.get(0).getWidth()) && missiles.get(i).y >= planes2.get(0).planeY &&
                        missiles.get(i).y <= (planes2.get(0).planeY + planes2.get(0).getHeight())) {
                    // Create an explosion at the center of the collided plane
                    Explosion explosion = new Explosion(context);
                    explosion.explosionX = planes2.get(0).planeX + planes2.get(0).getWidth() / 2 - explosion.getExplosionWidth() / 2;
                    explosion.explosionY = planes2.get(0).planeY + planes2.get(0).getHeight() / 2 - explosion.getExplosionHeight() / 2;
                    explosions.add(explosion);

                    // Reset the position of the collided plane
                    planes2.get(0).resetPosition();

                    // Increment the collision count
                    count++;

                    // Remove the missile
                    missiles.remove(i);

                    // Play a sound effect if points are nonzero
                    if (point != 0) {
                        sp.play(point, 1, 1, 0, 0, 1);
                    }


                } else if (missiles.get(i).x >= planes2.get(1).planeX &&
                        (missiles.get(i).x + missiles.get(i).getMissileWidth()) <= (planes2.get(1).planeX + planes2.get(1).getWidth()) &&
                        missiles.get(i).y >= planes2.get(1).planeY &&
                        missiles.get(i).y <= (planes2.get(1).planeY + planes2.get(1).getHeight())) {

                    // Create an explosion at the center of the second plane
                    Explosion explosion = new Explosion(context);
                    explosion.explosionX = planes2.get(1).planeX + planes2.get(1).getWidth() / 2 - explosion.getExplosionWidth() / 2;
                    explosion.explosionY = planes2.get(1).planeY + planes2.get(1).getHeight() / 2 - explosion.getExplosionHeight() / 2;
                    explosions.add(explosion);

                    // Reset the position of the second plane
                    planes2.get(1).resetPosition();

                    // Increment the count (assuming it's a score or something)
                    count++;

                    // Play a sound effect if the 'point' value is not zero
                    if (point != 0) {
                        sp.play(point, 1, 1, 0, 0, 1);
                    }

                }
            } else {
                // Remove the missile at index 'i'
                missiles.remove(i);
            }
        }
        for (int j = 0; j < explosions.size(); j++) {
            // Draw the explosion bitmap at the specified position
            canvas.drawBitmap(explosions.get(j).getExplosion(explosions.get(j).explosionFrame),
                    explosions.get(j).explosionX, explosions.get(j).explosionY, null);

            // Increment the explosion frame
            explosions.get(j).explosionFrame++;

            // Remove the explosion if it has completed all frames
            if (explosions.get(j).explosionFrame > 8) {
                explosions.remove(j);
            }
        }
        // Draw the tank bitmap
        canvas.drawBitmap(tank, (dWidth / 2 - tankWidth / 2), dHeight - tankHeight, null);

// Display the player's score
        canvas.drawText("Pt: " + (count * 10), 0, TEXT_SIZE, scorePaint);

// Draw the health bar
        canvas.drawRect(dWidth - 110, 10, dWidth - 110 + 10 * life, TEXT_SIZE, healthPaint);

// Schedule the next frame update
        handler.postDelayed(runnable, UPDATE_MILLIS);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Get the X and Y coordinates of the touch event
        float touchX = event.getX();
        float touchY = event.getY();

        // Get the action type of the touch event
        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            // Check if the touch is within the tank area
            if (touchX >= (dWidth / 2 - tankWidth / 2) && touchX <= (dWidth / 2 + tankWidth / 2) && touchY >= (dHeight - tankHeight)) {
                // Log a message indicating that the tank is tapped
                Log.i("Tank", "is tapped");

                // Add a missile if the maximum limit is not reached
                if (missiles.size() < 5) {
                    Missile m = new Missile(context);
                    missiles.add(m);

                    // Play the fire sound effect (if available)
                    if (fire != 0) {
                        sp.play(fire, 1, 1, 0, 0, 1);
                    }
                }
            }
        }

        // Return true to indicate that the touch event is handled
        return true;
    }
}