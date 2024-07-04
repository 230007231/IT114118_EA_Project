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

    Button G1, G2, G3, G4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Enable edge-to-edge display (if available)
        setContentView(R.layout.activity_selectpage); // Set the layout for this activity

        // Initialize buttons
        G1 = findViewById(R.id.G1);
        G2 = findViewById(R.id.G2);
        G3 = findViewById(R.id.G3);
        G4 = findViewById(R.id.G4);

        // Set click listeners for each button
        G1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When G1 button is clicked, start MainActivity
                Intent intent = new Intent(Selectpage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        G2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When G2 button is clicked, start SplashActivity
                Intent intent = new Intent(Selectpage.this, SplashActivity.class);
                startActivity(intent);
            }
        });

        G3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When G3 button is clicked, start number_menu activity
                Intent intent = new Intent(Selectpage.this, number_menu.class);
                startActivity(intent);
            }
        });

        G4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When G4 button is clicked, start finddifference_menu activity
                Intent intent = new Intent(Selectpage.this, finddifference_menu.class);
                startActivity(intent);
            }
        });
    }

    // Handle back button press
    public void back(View view) {
        Intent intent = new Intent(Selectpage.this, Menu.class);
        startActivity(intent);
        finish(); // Close this activity
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