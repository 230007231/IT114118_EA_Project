package com.example.elderbox;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private EditText etName, etPassword;
    private String name, password;
    private String URL = "http://10.0.2.2/login/login.php"; // URL for login endpoint
    String result = ""; // Initialize an empty string for the result

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Enable edge-to-edge display (assuming this is a library or utility method)
        setContentView(R.layout.activity_login); // Set the content view to the login layout

        name = password = ""; // Initialize name and password strings
        etName = findViewById(R.id.etName); // Find the EditText view for the username
        etPassword = findViewById(R.id.etPassword); // Find the EditText view for the password

        // Set an OnApplyWindowInsetsListener to adjust padding based on system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void login(View view) {
        name = etName.getText().toString().trim(); // Get and trim the username from user input
        password = etPassword.getText().toString().trim(); // Get and trim the password from user input
        if (!name.equals("") && !password.equals("")) { // If both username and password are not empty
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) { // If the server returns "success"
                        Intent intent = new Intent(Login.this, Menu.class);

                        intent.putExtra("name", name); // Save the player's name

                        // Save the name in SharedPreferences
                        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("name", name);
                        editor.apply();

                        startActivity(intent); // Start the Menu activity
                        finish(); // Finish the current activity
                    } else if (response.equals("failure")) { // If the server returns "failure"
                        Toast.makeText(Login.this, "Invalid Login Id/Password", Toast.LENGTH_SHORT).show(); // Display invalid login ID/password message
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Login.this, error.toString().trim(), Toast.LENGTH_SHORT).show(); // Display error message
                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("name", name); // Add username to request parameters
                    data.put("password", password); // Add password to request parameters
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest); // Add the request to the request queue
        } else {
            Toast.makeText(this, "Fields cannot be empty!", Toast.LENGTH_SHORT).show(); // Display "Fields cannot be empty!" message
        }
    }
}