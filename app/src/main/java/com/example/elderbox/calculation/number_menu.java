package com.example.elderbox.calculation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.elderbox.Music;
import com.example.elderbox.R;


public class number_menu extends AppCompatActivity {

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
                Intent intent = new Intent(number_menu.this, number_MainActivity.class);
                startActivity(intent);
            }
        });



    }


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Music.play(this, R.raw.opensound2);
    }


}