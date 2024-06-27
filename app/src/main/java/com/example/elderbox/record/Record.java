// Ref: http://www.androidhive.info/2012/01/android-json-parsing-tutorial/

package com.example.elderbox.record;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.elderbox.R;

public class Record extends AppCompatActivity {

    TextView tvName, tvBest1, tvBest2, tvBest3, tvBest4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);


        SharedPreferences pref = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int scoreSP = pref.getInt("scoreSP", 0);
        int scoreSP2 = pref.getInt("scoreSP2", 0);
        int scoreSP3 = pref.getInt("scoreSP3", 0);
        int scoreSP4 = pref.getInt("scoreSP4", 0);
        String playerName = pref.getString("name", ""); // if no, set blank as result

        tvName = (TextView) findViewById(R.id.tvName);
        tvBest1 = (TextView) findViewById(R.id.tvBest1);
        tvBest2 = (TextView) findViewById(R.id.tvBest2);
        tvBest3 = (TextView) findViewById(R.id.tvBest3);
        tvBest4 = (TextView) findViewById(R.id.tvBest4);
        tvName.setText(""+playerName);
        tvBest1.setText(""+scoreSP);
        tvBest2.setText(""+scoreSP2);
        tvBest3.setText(""+scoreSP3);
        tvBest4.setText(""+scoreSP4);

    }


    public void exit(View view) {
        Intent intent = new Intent(Record.this, com.example.elderbox.Menu.class);
        startActivity(intent);
        finish();
    }
}

