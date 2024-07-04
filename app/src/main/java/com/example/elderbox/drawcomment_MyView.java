package com.example.elderbox;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class drawcomment_MyView extends View {

    private Paint myPainBrush; // Paint object for drawing
    private Path path; // Path to store the drawing
    private float xPos, yPos; // Current touch position
    private float visible; // Not sure what this is for

    public drawcomment_MyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Initialize the Paint object
        myPainBrush = new Paint();
        path = new Path();

        // Set paint properties
        myPainBrush.setColor(Color.RED); // Set color to red
        myPainBrush.setAntiAlias(true); // Enable anti-aliasing
        myPainBrush.setStrokeWidth(5f); // Set stroke width
        myPainBrush.setStyle(Paint.Style.STROKE); // Set style to stroke

        // Other initialization (not sure what visible is used for)
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw the path using the Paint object
        canvas.drawPath(path, myPainBrush);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        xPos = event.getX(); // Get X position of touch
        yPos = event.getY(); // Get Y position of touch

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // When touch starts, move to the initial position
                path.moveTo(xPos, yPos);
                return true;
            case MotionEvent.ACTION_MOVE:
                // When touch moves, add a line to the path
                path.lineTo(xPos, yPos);
                break;
            case MotionEvent.ACTION_UP:
                // When touch ends, do nothing
                break;
            default:
                return false;
        }
        // Invalidate the view to trigger redraw
        invalidate();
        return true;
    }

    public void reset() {
        // Reset the path (clear the drawing)
        path.reset();
        // Invalidate the view to update the display
        invalidate();
    }
}
