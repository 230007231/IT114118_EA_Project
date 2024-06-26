package com.example.elderbox.finddifference;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.elderbox.Music;
import com.example.elderbox.R;


public class finddifference_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.finddifference_activity_menu);

        ImageView playbutton = (ImageView) findViewById(R.id.playbutton);

        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(finddifference_menu.this, finddifference_stage1.class));
            }
        });

        /*Intent svc = new Intent(finddifference_menu.this, finddifference_backgroundMusic.class);
        startService(svc);*/

    }

    /*@Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop the background music service if needed
        Intent svc = new Intent(finddifference_menu.this, finddifference_backgroundMusic.class);
        stopService(svc);
    }*/

    /*@Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        Music.stop(this);
    }*/


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Music.play(this, R.raw.opensound);
    }


}