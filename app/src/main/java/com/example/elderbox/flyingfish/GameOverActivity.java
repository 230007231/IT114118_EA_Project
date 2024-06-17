package com.example.elderbox.flyingfish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.elderbox.R;
import com.example.elderbox.Selectpage;

public class GameOverActivity extends AppCompatActivity {

    private Button playAgainBtn, exitBtn;
    private TextView scoreDisplayTextView;
    private String receiveScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_game_over);

        playAgainBtn = findViewById(R.id.play_again_button_id);
        scoreDisplayTextView = findViewById(R.id.displayScoreId);
        exitBtn = findViewById(R.id.button2);

        receiveScore = getIntent().getExtras().get("score").toString();

        playAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FishMainActivity.class);
                startActivity(intent);
            }
        });
        scoreDisplayTextView.setText(receiveScore);

            exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Selectpage.class);
                startActivity(intent);
            }
        });
        scoreDisplayTextView.setText(receiveScore);
    }
    }