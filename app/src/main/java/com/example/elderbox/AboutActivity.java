package com.example.elderbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class AboutActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout home, settings, share, about, logout;
    TextView aboutContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        drawerLayout = findViewById(R.id.drawerLayout);
        menu = findViewById(R.id.menu);
        home = findViewById(R.id.home);
        settings = findViewById(R.id.settings);
        share = findViewById(R.id.share);
        about = findViewById(R.id.about);
        logout= findViewById(R.id.logout);

        aboutContent = findViewById(R.id.aboutContent);




        // Read JSON data from assets
        String jsonContent = readJsonFromAssets();

        if (jsonContent != null) {
            try {
                JSONObject jsonObject = new JSONObject(jsonContent);
                JSONArray groupmatesArray = jsonObject.getJSONArray("Groupmates");

                // Extract groupmates' names and descriptions
                StringBuilder groupmatesInfo = new StringBuilder();
                for (int i = 0; i < groupmatesArray.length(); i++) {
                    JSONObject groupmate = groupmatesArray.getJSONObject(i);
                    String name = groupmate.getString("name");
                    String description = groupmate.getString("description");
                    groupmatesInfo.append(name).append(": ").append(description).append("\n");
                }

                // Set the extracted data to the TextView
                aboutContent.setText(groupmatesInfo.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        // Set an OnClickListener for the "menu" button
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawerLayout); // Call the openDrawer method
            }
        });

        // Set an OnClickListener for the "home" button
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(AboutActivity.this, Menu.class); // Redirect to the Menu activity
            }
        });

        // Set an OnClickListener for the "settings" button
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(AboutActivity.this, SettingsActivity.class);// Redirect to the Settings activity
            }
        });

        // Set an OnClickListener for the "share" button
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(AboutActivity.this, ShareActivity.class);// Redirect to the Share activity
            }
        });

        // Set an OnClickListener for the "about" button
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();// Recreate the current activity
            }
        });

        // Set an OnClickListener for the "logout" button
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a toast message
                Toast.makeText(AboutActivity.this,"Logout",Toast.LENGTH_SHORT).show();
            }
        });
    }
    // Method to open the drawer
    public static void openDrawer(DrawerLayout drawerLayout){
        drawerLayout.openDrawer(GravityCompat.START);
    }

    // Method to close the drawer
    public static void closeDrawer(DrawerLayout drawerLayout){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    // Method to redirect to another activity
    public static void redirectActivity(Activity activity, Class secondActivity){
        Intent intent = new Intent(activity, secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    // onPause method to close the drawer when the activity is paused
    @Override
    protected void onPause(){
        super.onPause();
        closeDrawer(drawerLayout);
    }

    // Read JSON data from assets folder
    private String readJsonFromAssets() {
        try {
            InputStream inputStream = getAssets().open("about.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            return new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}