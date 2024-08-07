package com.example.elderbox.finddifference;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.elderbox.R;

public class finddifference_stage1 extends AppCompatActivity {

    private Button  restartButton ;
    int score=0;
    boolean a = true;
    boolean b = true;
    boolean c = true;
    boolean d = true;
    boolean e = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finddifference_activity_stage1);

        Button restartButton = findViewById(R.id.restar);

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

        TextView score1 = (TextView)findViewById(R.id.score);


        //region 1st
        ans11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ans11.setImageResource(R.drawable.circlestyle3);
                ans12.setImageResource(R.drawable.circlestyle3);
                if (a == true) {
                    score+=10;
                    a=false;
                    score1.setText("分數: " + String.valueOf(score));
                }
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (score >= 50) {
                    Intent intent = new Intent(finddifference_stage1.this, finddifference_stage2.class);
                    intent.putExtra("EXTRA_SCORE", score);
                    startActivity(intent);
                }else {
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(finddifference_stage1.this);
                    dlgAlert.setMessage("你還未找出所有不同之處,當找到所有不同之處時,才能進入下一關! OVO ");
                    dlgAlert.setTitle("提示");
                    dlgAlert.setPositiveButton("OK", null); // OK button with no action
                    dlgAlert.setCancelable(true); // Allow the dialog to be canceled by tapping outside
                    dlgAlert.create().show();
                }
            }
        });



        ans12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ans11.setImageResource(R.drawable.circlestyle3);
                ans12.setImageResource(R.drawable.circlestyle3);
                if (a == true) {
                    score+=10;
                    a=false;
                    score1.setText("分數: " + String.valueOf(score));
                }
            }
        });
        //endregion
        //region 2nd
        ans21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ans21.setImageResource(R.drawable.circlestyle2);
                ans22.setImageResource(R.drawable.circlestyle2);
                if (b == true) {
                    score+=10;
                    b=false;
                    score1.setText("分數: " + String.valueOf(score));
                }
            }
        });

        ans22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ans21.setImageResource(R.drawable.circlestyle2);
                ans22.setImageResource(R.drawable.circlestyle2);
                if (b == true) {
                    score+=10;
                    b=false;
                    score1.setText("分數: " + String.valueOf(score));
                }
            }
        });
        //endregion
        //region 3rd
        ans31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ans31.setImageResource(R.drawable.circlestyle1);
                ans32.setImageResource(R.drawable.circlestyle1);
                if (c == true) {
                    score+=10;
                    c=false;
                    score1.setText("分數: " + String.valueOf(score));
                }

            }
        });

        ans32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ans31.setImageResource(R.drawable.circlestyle1);
                ans32.setImageResource(R.drawable.circlestyle1);
                if (c == true) {
                    score+=10;
                    c=false;
                    score1.setText("分數: " + String.valueOf(score));
                }
            }
        });
        //endregion

        //region 4th
        ans41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ans41.setImageResource(R.drawable.circlestyle3);
                ans42.setImageResource(R.drawable.circlestyle3);
                if (d == true) {
                    score+=10;
                    d=false;
                    score1.setText("分數: " + String.valueOf(score));
                }

            }
        });

        ans42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ans41.setImageResource(R.drawable.circlestyle3);
                ans42.setImageResource(R.drawable.circlestyle3);
                if (d == true) {
                    score+=10;
                    d=false;
                    score1.setText("分數: " + String.valueOf(score));
                }
            }
        });
        //endregion

        //region 5th
        ans51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ans51.setImageResource(R.drawable.circlestyle3);
                ans52.setImageResource(R.drawable.circlestyle3);
                if (e == true) {
                    score+=10;
                    e=false;
                    score1.setText("分數: " + String.valueOf(score));
                }

            }
        });

        ans52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ans51.setImageResource(R.drawable.circlestyle3);
                ans52.setImageResource(R.drawable.circlestyle3);
                if (e == true) {
                    score+=10;
                    e=false;
                    score1.setText("分數: " + String.valueOf(score));
                }

            }
        });
//endregion


    }
}
