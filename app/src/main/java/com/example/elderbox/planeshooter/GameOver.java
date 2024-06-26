package com.example.elderbox.planeshooter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.elderbox.R;

public class GameOver extends Activity {

    TextView tvName, tvScore, tvPersonalBest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plane_game_over);

        int score1 = getIntent().getExtras().getInt("score");

        SharedPreferences pref = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int scoreSP = pref.getInt("scoreSP", 0);
        String playerName = pref.getString("name", ""); // if no, set blank as result
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("score1", score1);
        if(score1 > scoreSP){
            scoreSP = score1;
            editor.putInt("scoreSP", scoreSP);
            editor.commit();
        }
        tvName = (TextView) findViewById(R.id.tvName);
        tvScore = (TextView) findViewById(R.id.tvScore);
        tvPersonalBest = (TextView) findViewById(R.id.tvPersonalBest);
        tvName.setText(""+playerName);
        tvScore.setText(""+score1);
        tvPersonalBest.setText(""+scoreSP);
    }
    public void restart(View view){
        Intent intent = new Intent(GameOver.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void exit(View view){
        finish();
    }
}
