package com.example.elderbox.flyingfish;

import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.elderbox.R;

public class FlyingFishClass extends View {

    // Declare variables for the fish
    private Bitmap fish[] = new Bitmap[2];
    private int fishX = 10;
    private int fishY;
    private int fishSpeed;
    private int canvasWidth, canvasHeight;

    // Touch control flag
    private boolean touch = false;

    // Score and fish life counter
    private int score, lifeCounterOfFish;

    // Yellow bubble properties
    private int yellowX, yellowY, yellowSpeed = 10;
    private Paint yellowPaint = new Paint();

    // Green bubble properties
    private int greenX, greenY, greenSpeed = 30;
    private Paint greenPaint = new Paint();

    // Red bubble properties
    private int redX, redY, redSpeed = 25;
    private Paint redPaint = new Paint();

    // Background image
    private Bitmap backgroundImage;

    // Paint for displaying the score
    private Paint scorePaint = new Paint();

    // Life icons
    private Bitmap life[] = new Bitmap[2];

    public FlyingFishClass(Context context) {
        super(context);

        // Load fish images
        fish[0] = BitmapFactory.decodeResource(getResources(), R.drawable.fish1);
        fish[1] = BitmapFactory.decodeResource(getResources(), R.drawable.fish2);

        // Load background image
        backgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.background_fish);

        // Set properties for yellow bubbles
        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);

        // Set properties for green bubbles
        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);

        // Set properties for red bubbles
        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);

        // Set properties for displaying the score
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(80);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        // Load heart icons
        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_grey);

        // Initialize fish position and counters
        fishY = 550;
        score = 0;
        lifeCounterOfFish = 3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Get the width and height of the canvas
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        // Draw the background image
        canvas.drawBitmap(backgroundImage, 0, 0, null);

        // Calculate the minimum and maximum Y positions for the fish
        int minFishY = fish[0].getHeight();
        int maxFishY = canvasHeight - fish[0].getHeight();

        // Update the fish's Y position based on its speed
        fishY = fishY + fishSpeed;

        // Ensure the fish stays within the valid Y range
        if (fishY < minFishY) {
            fishY = minFishY;
        }
        if (fishY > maxFishY) {
            fishY = maxFishY;
        }

        // Increase the fish's speed
        fishSpeed = fishSpeed + 2;

        // Draw the appropriate fish bitmap based on touch input
        if (touch) {
            canvas.drawBitmap(fish[1], fishX, fishY, null);
            touch = false;
        } else {
            canvas.drawBitmap(fish[0], fishX, fishY, null);
        }


        //yellow Ball
// Decrease yellowX by yellowSpeed
        yellowX = yellowX - yellowSpeed;

// Check if the ball hits the yellow object
        if (hitBallChecker(yellowX, yellowY)) {
            score = score + 10;
            yellowX = -100;
        }

// Reset yellowX if it goes below 0
        if (yellowX < 0) {
            yellowX = canvasWidth + 21;
            yellowY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }

// Draw a circle at yellowX, yellowY with a radius of 45 using yellowPaint
        canvas.drawCircle(yellowX, yellowY, 45, yellowPaint);


        //green Ball

        // Update the green fish's position
        greenX = greenX - greenSpeed;

// Check if the green fish collides with the ball
        if (hitBallChecker(greenX, greenY)) {
            // Increase the score by 20 points
            score = score + 20;
            // Move the green fish off-screen
            greenX = -100;
        }

// If the green fish goes off the left edge of the canvas
        if (greenX < 0) {
            // Reset its position to the right edge of the canvas
            greenX = canvasWidth + 21;
            // Randomly set its vertical position within the valid range
            greenY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }

// Draw the green fish on the canvas
        canvas.drawCircle(greenX, greenY, 35, greenPaint);


        //red Ball

        // Update the red fish's position
        redX = redX - redSpeed;

// Check if the red fish collides with the ball
        if (hitBallChecker(redX, redY)) {
            // Move the red fish off-screen
            redX = -100;

            // Decrease the life counter
            lifeCounterOfFish--;

            // Check if the player has no more lives
            if (lifeCounterOfFish == 0) {
                // Display "Game Over" message
                Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();

                // Start the GameOverActivity with the player's score
                Intent gameOverIntent = new Intent(getContext(), GameOverActivity.class);
                gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                gameOverIntent.putExtra("score", score);
                getContext().startActivity(gameOverIntent);
            }
        }


// Check if the red fish is out of bounds
        if (redX < 0) {
            // Reset the red fish's position
            redX = canvasWidth + 21;
            redY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }

// Draw the red fish's circular shape
        canvas.drawCircle(redX, redY, 55, redPaint);

// Display the player's score
        canvas.drawText("Score: " + score, 20, 60, scorePaint);

// Draw the life counter
        for (int i = 0; i < 3; i++) {
            int x = (int) (580 + life[0].getWidth() * 1.5 * i);
            int y = 30;

            if (i < lifeCounterOfFish) {
                // Draw a full life icon
                canvas.drawBitmap(life[0], x, y, null);
            } else {
                // Draw an empty life icon
                canvas.drawBitmap(life[1], x, y, null);
            }
        }
    }

    public boolean hitBallChecker(int x, int y) {
        // Check if the ball (represented by point (x, y)) is within the fish's bounding box
        if (fishX < x && x < (fishX + fish[0].getWidth()) && fishY < y && y < (fishY + fish[0].getHeight())) {
            return true; // Ball hits the fish
        }
        return false; // Ball does not hit the fish
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touch = true; // User touched the screen
            fishSpeed = -18; // Set fish speed (adjust as needed)
        }
        return true; // Event handled
    }
}