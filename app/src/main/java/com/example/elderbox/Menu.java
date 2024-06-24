package com.example.elderbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.elderbox.ranking.Ranking;
import com.example.elderbox.record.Record;

public class Menu extends AppCompatActivity {

    Button game,record,ranking,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

        game = findViewById(R.id.btn4);
        record = findViewById(R.id.btn5);
        ranking = findViewById(R.id.btn6);
        logout = findViewById(R.id.btn7);

        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu.this,Selectpage.class);
                startActivity(intent);
            }
        });

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu.this, Record.class);
                startActivity(intent);
            }
        });

        ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu.this, Ranking.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu.this,First.class);
                startActivity(intent);
            }
        });
    }
}