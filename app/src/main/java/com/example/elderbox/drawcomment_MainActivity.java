package com.example.elderbox;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.elderbox.R;

public class drawcomment_MainActivity extends AppCompatActivity {

    // Declare an instance of custom view class
    public drawcomment_MyView myView;

    // Declare a button for resetting the drawing
    public Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display
        EdgeToEdge.enable(this);

        // Set the content view to the specified layout resource
        setContentView(R.layout.drawcomment_activity_main);

        // Initialize the custom view by finding its ID in the layout
        myView = findViewById(R.id.myDrawingArea);

        // Initialize the reset button by finding its ID in the layout
        btnReset = findViewById(R.id.btnReset);

        // Set an onClickListener for the reset button
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the reset method on the custom view
                myView.reset();
            }
        });
    }
}