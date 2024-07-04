package com.example.elderbox.planeshooter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.elderbox.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plane_main); // Set the content view to the layout defined in activity_plane_main.xml
    }

    public void startGame(View v) {
        Log.i("ImageButton", "clicked"); // Log a message indicating that the ImageButton was clicked
        Intent intent = new Intent(this, StartGame.class); // Create an intent to start the StartGame activity
        startActivity(intent); // Start the activity
        finish(); // Finish the current activity
    }
}
