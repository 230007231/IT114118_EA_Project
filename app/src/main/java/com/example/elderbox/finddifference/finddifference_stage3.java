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

public class finddifference_stage3 extends AppCompatActivity {

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
        setContentView(R.layout.finddifference_activity_stage3);

        // Retrieve the extras from the intent that started the current activity
        Bundle extras = getIntent().getExtras();

        // If extras are not null
        if (extras != null) {
            // Retrieve the integer value of "EXTRA_SCORE" from the extras
            int receivedScore = extras.getInt("EXTRA_SCORE");
            // Assign the received score to the score variable
            score = receivedScore;
        }

        // Find the restartButton view by its ID
        Button restartButton = findViewById(R.id.restar);

        // Find the ImageView views by their IDs
        ImageView ans11 = findViewById(R.id.ans11);
        ImageView ans12 = findViewById(R.id.ans12);
        ImageView ans21 = findViewById(R.id.ans21);
        ImageView ans22 = findViewById(R.id.ans22);
        ImageView ans31 = findViewById(R.id.ans31);
        ImageView ans32 = findViewById(R.id.ans32);
        ImageView ans41 = findViewById(R.id.ans41);
        ImageView ans42 = findViewById(R.id.ans42);
        ImageView ans51 = findViewById(R.id.ans51);
        ImageView ans52 = findViewById(R.id.ans52);

        // Find the score1 TextView by its ID
        TextView score1 = findViewById(R.id.score);

        // Set the initial score text in the score1 TextView
        score1.setText("分數: " + String.valueOf(score));



        //region 1st
        ans11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the image resource for ans11 and ans12 to circlestyle3
                ans11.setImageResource(R.drawable.circlestyle3);
                ans12.setImageResource(R.drawable.circlestyle3);

                // If a is true
                if (a == true) {
                    // Increase the score by 10
                    score += 10;
                    // Set a to false to prevent further score increase
                    a = false;
                    // Update the score text in score1 TextView
                    score1.setText("分數: " + String.valueOf(score));
                }
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If the score is greater than or equal to 150
                if (score >= 150) {
                    // Start the finddifference_stage4 activity and pass the score as an extra
                    Intent intent = new Intent(finddifference_stage3.this, finddifference_stage4.class);
                    intent.putExtra("EXTRA_SCORE", score);
                    startActivity(intent);
                } else {
                    // Show an AlertDialog with a message and title
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(finddifference_stage3.this);
                    dlgAlert.setMessage(R.string.message);
                    dlgAlert.setTitle(R.string.hint);
                    dlgAlert.setPositiveButton("OK", null); // OK button with no action
                    dlgAlert.setCancelable(true); // Allow the dialog to be canceled by tapping outside
                    dlgAlert.create().show();
                }
            }
        });

        ans12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the image resource for ans11 and ans12 to circlestyle3
                ans11.setImageResource(R.drawable.circlestyle3);
                ans12.setImageResource(R.drawable.circlestyle3);

                // If a is true
                if (a == true) {
                    // Increase the score by 10
                    score += 10;
                    // Set a to false to prevent further score increase
                    a = false;
                    // Update the score text in score1 TextView
                    score1.setText(getString(R.string.score5) + String.valueOf(score));
                }
            }
        });

        ans21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the image resource for ans21 and ans22 to circlestyle2
                ans21.setImageResource(R.drawable.circlestyle2);
                ans22.setImageResource(R.drawable.circlestyle2);

                // If b is true
                if (b == true) {
                    // Increase the score by 10
                    score += 10;
                    // Set b to false to prevent further score increase
                    b = false;
                    // Update the score text in score1 TextView
                    score1.setText(getString(R.string.score5) + String.valueOf(score));
                }
            }
        });

        ans22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the image resource for ans21 and ans22 to circlestyle2
                ans21.setImageResource(R.drawable.circlestyle2);
                ans22.setImageResource(R.drawable.circlestyle2);

                // If b is true
                if (b == true) {
                    // Increase the score by 10
                    score += 10;
                    // Set b to false to prevent further score increase
                    b = false;
                    // Update the score text in score1 TextView
                    score1.setText(getString(R.string.score5) + String.valueOf(score));
                }
            }
        });

        ans31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the image resource for ans31 and ans32 to circlestyle1
                ans31.setImageResource(R.drawable.circlestyle1);
                ans32.setImageResource(R.drawable.circlestyle1);

                // If c is true
                if (c == true) {
                    // Increase the score by 10
                    score += 10;
                    // Set c to false to prevent further score increase
                    c = false;
                    // Update the score text in score1 TextView
                    score1.setText(getString(R.string.score5) + String.valueOf(score));
                }
            }
        });

        ans32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the image resource for ans31 and ans32 to circlestyle1
                ans31.setImageResource(R.drawable.circlestyle1);
                ans32.setImageResource(R.drawable.circlestyle1);

                // If c is true
                if (c == true) {
                    // Increase the score by 10
                    score += 10;
                    // Set c to false to prevent further score increase
                    c = false;
                    // Update the score text in score1 TextView
                    score1.setText(getString(R.string.score5) + String.valueOf(score));
                }
            }
        });
        //endregion

        //region 4th
        ans41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the image resource for ans41 and ans42 to circlestyle3
                ans41.setImageResource(R.drawable.circlestyle3);
                ans42.setImageResource(R.drawable.circlestyle3);

                // If d is true
                if (d == true) {
                    // Increase the score by 10
                    score += 10;
                    // Set d to false to prevent further score increase
                    d = false;
                    // Update the score text in score1 TextView
                    score1.setText(getString(R.string.score5) + String.valueOf(score));
                }
            }
        });

        ans42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the image resource for ans41 and ans42 to circlestyle3
                ans41.setImageResource(R.drawable.circlestyle3);
                ans42.setImageResource(R.drawable.circlestyle3);

                // If d is true
                if (d == true) {
                    // Increase the score by 10
                    score += 10;
                    // Set d to false to prevent further score increase
                    d = false;
                    // Update the score text in score1 TextView
                    score1.setText(getString(R.string.score5) + String.valueOf(score));
                }
            }
        });

        ans51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the image resource for ans51 and ans52 to circlestyle3
                ans51.setImageResource(R.drawable.circlestyle3);
                ans52.setImageResource(R.drawable.circlestyle3);

                // If e is true
                if (e == true) {
                    // Increase the score by 10
                    score += 10;
                    // Set e to false to prevent further score increase
                    e = false;
                    // Update the score text in score1 TextView
                    score1.setText(getString(R.string.score5) + String.valueOf(score));
                }
            }
        });

        ans52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the image resource for ans51 and ans52 to circlestyle3
                ans51.setImageResource(R.drawable.circlestyle3);
                ans52.setImageResource(R.drawable.circlestyle3);

                // If e is true
                if (e == true) {
                    // Increase the score by 10
                    score += 10;
                    // Set e to false to prevent further score increase
                    e = false;
                    // Update the score text in score1 TextView
                    score1.setText(getString(R.string.score5) + String.valueOf(score));
                }
            }
        });
//endregion

    }
}
