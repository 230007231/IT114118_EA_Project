package com.example.elderbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.elderbox.calculation.number_main;
import com.example.elderbox.calculation.number_menu;
import com.example.elderbox.finddifference.Std;
import com.example.elderbox.finddifference.finddifference_menu;
import com.example.elderbox.flyingfish.SplashActivity;
import com.example.elderbox.planeshooter.MainActivity;

public class Selectpage extends AppCompatActivity {

    Button G1,G2,G3,G4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_selectpage);


        G1 = findViewById(R.id.G1);

        G1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Selectpage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        G2 = findViewById(R.id.G2);

        G2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Selectpage.this, SplashActivity.class);
                startActivity(intent);
            }
        });

        G3 = findViewById(R.id.G3);

        G3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Selectpage.this, number_menu.class);
                startActivity(intent);
            }
        });

        G4 = findViewById(R.id.G4);

        G4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Selectpage.this, finddifference_menu.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        Music.stop(this);
    }


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Music.play(this, R.raw.betterday);
    }

}