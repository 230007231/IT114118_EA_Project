package com.example.elderbox.finddifference;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.elderbox.R;

public class finddifference_stage1 extends AppCompatActivity {

    // Declare and initialize the restartButton variable of type Button
    private Button restartButton;

    // Initialize the score variable to 0
    int score = 0;

    // Initialize boolean variables a, b, c, d, and e to true
    boolean a = true;
    boolean b = true;
    boolean c = true;
    boolean d = true;
    boolean e = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finddifference_activity_stage1);

        // Find the restart button in the layout and assign it to the 'restartButton' variable
        Button restartButton = findViewById(R.id.restar);

// Find the image views in the layout and assign them to their respective variables
        ImageView ans11 = (ImageView) findViewById(R.id.ans11);
        ImageView ans12 = (ImageView) findViewById(R.id.ans12);
        ImageView ans21 = (ImageView) findViewById(R.id.ans21);
        ImageView ans22 = (ImageView) findViewById(R.id.ans22);
        ImageView ans31 = (ImageView) findViewById(R.id.ans31);
        ImageView ans32 = (ImageView) findViewById(R.id.ans32);
        ImageView ans41 = (ImageView) findViewById(R.id.ans41);
        ImageView ans42 = (ImageView) findViewById(R.id.ans42);
        ImageView ans51 = (ImageView) findViewById(R.id.ans51);
        ImageView ans52 = (ImageView) findViewById(R.id.ans52);

// Find the score text view in the layout and assign it to the 'score1' variable
        TextView score1 = (TextView)findViewById(R.id.score);


        //region 1st
        // Set an onClickListener for the ans11 ImageView
        ans11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the image resource of ans11 and ans12 to circlestyle3
                ans11.setImageResource(R.drawable.circlestyle3);
                ans12.setImageResource(R.drawable.circlestyle3);

                // If 'a' is true
                if (a == true) {
                    // Increase the score by 10
                    score += 10;
                    // Set 'a' to false
                    a = false;
                    // Update the score1 TextView with the updated score
                    score1.setText("分數: " + String.valueOf(score));
                }
            }
        });

// Set an onClickListener for the restartButton
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If the score is greater than or equal to 50
                if (score >= 50) {
                    // Create an intent to start the finddifference_stage2 activity
                    Intent intent = new Intent(finddifference_stage1.this, finddifference_stage2.class);
                    // Pass the score as an extra to the intent
                    intent.putExtra("EXTRA_SCORE", score);
                    // Start the activity
                    startActivity(intent);
                } else {
                    // Create an AlertDialog to display a message
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(finddifference_stage1.this);
                    dlgAlert.setMessage(R.string.message);
                    dlgAlert.setTitle(R.string.hint);
                    dlgAlert.setPositiveButton("OK", null); // OK button with no action
                    dlgAlert.setCancelable(true); // Allow the dialog to be canceled by tapping outside
                    dlgAlert.create().show();
                }
            }
        });

// Set an onClickListener for the ans12 ImageView
        ans12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the image resource of ans11 and ans12 to circlestyle3
                ans11.setImageResource(R.drawable.circlestyle3);
                ans12.setImageResource(R.drawable.circlestyle3);

                // If 'a' is true
                if (a == true) {
                    // Increase the score by 10
                    score += 10;
                    // Set 'a' to false
                    a = false;
                    // Update the score1 TextView with the updated score
                    score1.setText(getString(R.string.score5) + String.valueOf(score));
                }
            }
        });

// Set an onClickListener for the ans21 ImageView
        ans21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the image resource of ans21 and ans22 to circlestyle2
                ans21.setImageResource(R.drawable.circlestyle2);
                ans22.setImageResource(R.drawable.circlestyle2);

                // If 'b' is true
                if (b == true) {
                    // Increase the score by 10
                    score += 10;
                    // Set 'b' to false
                    b = false;
                    // Update the score1 TextView with the updated score
                    score1.setText(getString(R.string.score5) + String.valueOf(score));
                }
            }
        });

// Set an onClickListener for the ans22 ImageView
        ans22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the image resource of ans21 and ans22 to circlestyle2
                ans21.setImageResource(R.drawable.circlestyle2);
                ans22.setImageResource(R.drawable.circlestyle2);

                // If 'b' is true
                if (b == true) {
                    // Increase the score by 10
                    score += 10;
                    // Set 'b' to false
                    b = false;
                    // Update the score1 TextView with the updated score
                    score1.setText(getString(R.string.score5) + String.valueOf(score));
                }
            }
        });
        //endregion
        //region 3rd
        // Set an onClickListener for the ans31 ImageView
        ans31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the image resource of ans31 and ans32 to circlestyle1
                ans31.setImageResource(R.drawable.circlestyle1);
                ans32.setImageResource(R.drawable.circlestyle1);

                // If 'c' is true
                if (c == true) {
                    // Increase the score by 10
                    score += 10;
                    // Set 'c' to false
                    c = false;
                    // Update the score1 TextView with the updated score
                    score1.setText(getString(R.string.score5) + String.valueOf(score));
                }
            }
        });

// Set an onClickListener for the ans32 ImageView
        ans32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the image resource of ans31 and ans32 to circlestyle1
                ans31.setImageResource(R.drawable.circlestyle1);
                ans32.setImageResource(R.drawable.circlestyle1);

                // If 'c' is true
                if (c == true) {
                    // Increase the score by 10
                    score += 10;
                    // Set 'c' to false
                    c = false;
                    // Update the score1 TextView with the updated score
                    score1.setText(getString(R.string.score5) + String.valueOf(score));
                }
            }
        });

// Set an onClickListener for the ans41 ImageView
        ans41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the image resource of ans41 and ans42 to circlestyle3
                ans41.setImageResource(R.drawable.circlestyle3);
                ans42.setImageResource(R.drawable.circlestyle3);

                // If 'd' is true
                if (d == true) {
                    // Increase the score by 10
                    score += 10;
                    // Set 'd' to false
                    d = false;
                    // Update the score1 TextView with the updated score
                    score1.setText(getString(R.string.score5) + String.valueOf(score));
                }
            }
        });

// Set an onClickListener for the ans42 ImageView
        ans42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the image resource of ans41 and ans42 to circlestyle3
                ans41.setImageResource(R.drawable.circlestyle3);
                ans42.setImageResource(R.drawable.circlestyle3);

                // If 'd' is true
                if (d == true) {
                    // Increase the score by 10
                    score += 10;
                    // Set 'd' to false
                    d = false;
                    // Update the score1 TextView with the updated score
                    score1.setText(getString(R.string.score5) + String.valueOf(score));
                }
            }
        });

// Set an onClickListener for the ans51 ImageView
        ans51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the image resource of ans51 and ans52 to circlestyle3
                ans51.setImageResource(R.drawable.circlestyle3);
                ans52.setImageResource(R.drawable.circlestyle3);

                // If 'e' is true
                if (e == true) {
                    // Increase the score by 10
                    score += 10;
                    // Set 'e' to false
                    e = false;
                    // Update the score1 TextView with the updated score
                    score1.setText(getString(R.string.score5) + String.valueOf(score));
                }
            }
        });

// Set an onClickListener for the ans52 ImageView
        ans52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the image resource of ans51 and ans52 to circlestyle3
                ans51.setImageResource(R.drawable.circlestyle3);
                ans52.setImageResource(R.drawable.circlestyle3);

                // If 'e' is true
                if (e == true) {
                    // Increase the score by 10
                    score += 10;
                    // Set 'e' to false
                    e = false;
                    // Update the score1 TextView with the updated score
                    score1.setText(getString(R.string.score5) + String.valueOf(score));
                }
            }
        });
//endregion


    }
}
