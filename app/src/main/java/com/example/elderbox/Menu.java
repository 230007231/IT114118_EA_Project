package com.example.elderbox;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.elderbox.aichatbox.ai_MainActivity;
import com.example.elderbox.ranking.Ranking;
import com.example.elderbox.record.Record;


public class Menu extends AppCompatActivity {

    Button login, setting, game, record, ranking, exit;

    DrawerLayout drawerLayout;
    ImageView menu, chatbot;
    LinearLayout home, settings, share, about, logout;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Enable edge-to-edge display (assuming this is a library or utility method)
        setContentView(R.layout.activity_menu); // Set the content view to the menu layout

        // Initialize buttons
        login = findViewById(R.id.btnLogin);
        setting = findViewById(R.id.btnSetting);
        game = findViewById(R.id.playgame);
        record = findViewById(R.id.personalrecord);
        ranking = findViewById(R.id.ranking);
        exit = findViewById(R.id.Exit);
        chatbot = findViewById(R.id.chatbot);

        // Initialize other views
        drawerLayout = findViewById(R.id.drawerLayout);
        menu = findViewById(R.id.menu);
        home = findViewById(R.id.home);
        settings = findViewById(R.id.settings);
        share = findViewById(R.id.share);
        about = findViewById(R.id.about);
        logout = findViewById(R.id.logout);

        // Set click listeners for buttons
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Login.class);
                startActivity(intent);
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Selectpage.class);
                startActivity(intent);
            }
        });

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Record.class);
                startActivity(intent);
            }
        });

        ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Ranking.class);
                startActivity(intent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity(); // Close all activities and exit the app
            }
        });

        // Set click listener for menu icon
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawerLayout); // Open the navigation drawer
            }
        });

        // Set click listener for home icon
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate(); // Recreate the activity (refresh)
            }
        });

        // Set click listener for settings icon
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(Menu.this, SettingsActivity.class); // Redirect to SettingsActivity
            }
        });

        // Set click listener for share icon
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(Menu.this, ShareActivity.class); // Redirect to ShareActivity
            }
        });

        // Set click listener for about icon
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(Menu.this, AboutActivity.class); // Redirect to AboutActivity
            }
        });

        // Set click listener for logout icon
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu.this, "Logout", Toast.LENGTH_SHORT).show(); // Display logout message
            }
        });

        // Set click listener for chatbot icon
        chatbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(Menu.this, ai_MainActivity.class); // Redirect to AI chatbot activity
            }
        });

    }
    // This method is called when the "back" button is pressed.
    public void back(View view) {
        // Create an Intent to navigate from the current activity (Menu) to the Login activity.
        Intent intent = new Intent(Menu.this, Login.class);
        // Start the Login activity.
        startActivity(intent);
        // Finish the current activity (Menu).
        finish();
    }

    // This method opens the navigation drawer.
    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    // This method closes the navigation drawer if it is open.
    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    // This method redirects to another activity.
    public static void redirectActivity(Activity activity, Class secondActivity) {
        // Create an Intent to navigate from the current activity to the specified second activity.
        Intent intent = new Intent(activity, secondActivity);
        // Set the flag to create a new task for the second activity.
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // Start the second activity.
        activity.startActivity(intent);
        // Finish the current activity.
        activity.finish();
    }

    // This method is called when the activity is paused.
    @Override
    protected void onPause() {
        super.onPause();
        // Close the navigation drawer.
        closeDrawer(drawerLayout);
        // Stop any background music (assuming there's a Music class with a stop method).
        Music.stop(this);
    }


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Music.play(this, R.raw.betterday);
    }

}