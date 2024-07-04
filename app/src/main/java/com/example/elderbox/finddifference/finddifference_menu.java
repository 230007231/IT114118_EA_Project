package com.example.elderbox.finddifference;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        // Initialize the play button
        ImageView playButton = (ImageView) findViewById(R.id.play);

        // Set an onClickListener for the play button
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the activity for finddifference_stage1 when the play button is clicked
                startActivity(new Intent(finddifference_menu.this, finddifference_stage1.class));
            }
        });
    }

    @Override
    protected void onResume() {
        // Call the superclass method to perform any necessary setup
        super.onResume();

        // Play the specified sound using the Music class
        Music.play(this, R.raw.opensound2);
    }

}