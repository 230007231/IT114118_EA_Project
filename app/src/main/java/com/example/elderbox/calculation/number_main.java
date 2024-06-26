package com.example.elderbox.calculation;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.elderbox.R;


public class number_main extends AppCompatActivity {

    ImageView Start ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.number_activity_menu);

        Start = (ImageView) findViewById(R.id.Start);

        Start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(number_main.this, number_menu.class);
                startActivity(intent);
            }
        });

        Intent svc = new Intent(this, backgroundMusic.class);
        startService(svc);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop the background music service if needed
        Intent svc = new Intent(this, backgroundMusic.class);
        stopService(svc);
    }

}