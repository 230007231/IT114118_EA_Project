package com.example.elderbox.finddifference;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.elderbox.R;
import com.example.elderbox.Selectpage;
import com.example.elderbox.calculation.number_menu;
import com.example.elderbox.calculation.number_score_page;


public class finddifference_score_page extends AppCompatActivity {

    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.finddifference_activity_score_page);


        // In NextActivity.java
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int receivedScore = extras.getInt("EXTRA_SCORE");
            score=receivedScore;
        }


        TextView totalscore = (TextView) findViewById(R.id.totalscore);
        totalscore.setText("分數: " + String.valueOf(score));

        Button home = (Button) findViewById(R.id.home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(finddifference_score_page.this, finddifference_menu.class));
            }
        });


        Button select = (Button) findViewById(R.id.Select1);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(finddifference_score_page.this, Selectpage.class));
            }
        });
    }

}