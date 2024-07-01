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

    private Paint myPainBrush;
    private Path path;

    private  float xPos , yPos;

    private  float visible;








    public  drawcomment_MyView(Context context, AttributeSet attrs){
        super(context,attrs);

        myPainBrush = new Paint();
        path = new Path();

        myPainBrush.setColor(Color.RED);
        myPainBrush.setAntiAlias(true);
        myPainBrush.setStrokeWidth(5f);
        myPainBrush.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected  void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(path, myPainBrush);
    }

    @Override
    public  boolean onTouchEvent(MotionEvent event) {
        xPos = event.getX();
        yPos = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(xPos, yPos);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(xPos, yPos);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }
        invalidate();
        return true;


    }
    public  void  reset(){
        path.reset();
        invalidate();
    }

}
