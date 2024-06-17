package com.example.elderbox.calculation;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.elderbox.R;
import com.example.elderbox.music;

import java.util.Random;

public class number_main extends AppCompatActivity   {

    private TextView textLevel,textRightAnswer , textQuestion;
    private  Button buttonOp1,buttonOp2,buttonOp3;

    private int level =0 ;
    int great =0;
    int rightAnswer = 0 ;

    String realOperation="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_number_main);


        //Objects.requireNonNull(getSupportActionBar()).hide();

        textLevel = findViewById(R.id.textQuestionNum);
        textRightAnswer = findViewById(R.id.textrightAnswered);
        textQuestion =findViewById(R.id.textQuestions);

        buttonOp1=findViewById(R.id.buttonOption1);
        buttonOp2=findViewById(R.id.buttonOption2);
        buttonOp3=findViewById(R.id.buttonOption3);

        textLevel.setText("Q : "+level+" / 10");
        textRightAnswer.setText("RA "+great+ " / 10");

        if (level <10){
            getARandomQuestion();
        }

    }

    private  void  getARandomQuestion(){

        buttonOp1.setBackgroundResource(R.drawable.buttons_option_bg);
        buttonOp2.setBackgroundResource(R.drawable.buttons_option_bg);
        buttonOp3.setBackgroundResource(R.drawable.buttons_option_bg);

        int firstNum = new Random().nextInt(10);
        int secondNum = new Random().nextInt(10);

        int operation = new  Random().nextInt(3)+1;

        int optionA = new  Random().nextInt(100);
        int optionB = new  Random().nextInt(100);

        if (operation==1){
            realOperation="+";
            rightAnswer= firstNum + secondNum;
            textQuestion.setText(firstNum + " " +realOperation+ " " +secondNum + " = ?");
        }else {
            if (operation==2){
                realOperation="*";
                rightAnswer= firstNum * secondNum;
                textQuestion.setText(firstNum + " " +realOperation+ " " +secondNum + " = ?");
            }else {
                if (operation==3){
                    realOperation="-";
                    if (firstNum < secondNum ){
                        rightAnswer= secondNum - firstNum;
                        textQuestion.setText(secondNum+ " " +realOperation+ " " +firstNum + " = ?");
                    }else {
                        rightAnswer=  firstNum-secondNum;
                        textQuestion.setText(firstNum+ " " +realOperation+ " " +secondNum + " = ?");
                    }
                }
            }
        }

        int position = new  Random().nextInt(3)+1;
        if (position==1){
            buttonOp1.setText(""+rightAnswer);
            buttonOp2.setText(""+optionA);
            buttonOp3.setText(""+optionB);
        }else {
            buttonOp1.setText(""+optionA);
            if (position==2){
                buttonOp2.setText(""+rightAnswer);
                buttonOp3.setText(""+optionB);
            }else {
                buttonOp2.setText(""+optionB);
                buttonOp3.setText(""+rightAnswer);
            }
        }

        buttonOp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonOp1.getText().equals(""+rightAnswer)){

                    buttonOp1.setBackgroundResource(R.drawable.right_answer_bg);
                    great=great+1;
                    level=level+1;
                    textLevel.setText("Q : "+ level + " / 10");
                    textRightAnswer.setText("RA : "+great+" / 10");
                }else {
                    level= level+1;
                    textLevel.setText("Q : "+ level + " / 10");
                    buttonOp1.setBackgroundResource(R.drawable.wrong_answer_bg);
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (level <10){
                            getARandomQuestion();
                        }else {
                            //Intent intent = new Intent(MainActivity.this, ResultActivity2.class);
                            // intent.putExtra("RA",great);
                            //startActivity(intent);
                            // finish();




                            // Show a confirmation dialog to the user
                            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(number_main.this);
                            dlgAlert.setMessage("得分 : "+ great +" / 10");
                            dlgAlert.setTitle("分數");
                            dlgAlert.setPositiveButton("確認!", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // User confirmed, restart the activity
                                    Intent intent = new Intent(number_main.this, number_main.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            //dlgAlert.setNegativeButton("No", null);
                            dlgAlert.setCancelable(true);
                            dlgAlert.create().show();





                            /*AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(MainActivity.this);
                            dlgAlert.setMessage("You answered "+ great +" / 10");
                            dlgAlert.setTitle("Score");
                            dlgAlert.setPositiveButton("OK", null);
                            dlgAlert.setCancelable(true);
                            dlgAlert.create().show();*/

                            //Intent intent = getIntent();
                            //finish();
                            //startActivity(intent);



                        }
                    }
                },1000);
            }
        });


        buttonOp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonOp2.getText().equals(""+rightAnswer)){

                    buttonOp2.setBackgroundResource(R.drawable.right_answer_bg);
                    great=great+1;
                    level=level+1;
                    textLevel.setText("Q : "+ level + " / 10");
                    textRightAnswer.setText("RA : "+great+" / 10");
                }else {
                    level= level+1;
                    textLevel.setText("Q : "+ level + " / 10");
                    buttonOp2.setBackgroundResource(R.drawable.wrong_answer_bg);
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (level <10){
                            getARandomQuestion();
                        }else {
                            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(number_main.this);
                            dlgAlert.setMessage("得分 : "+ great +" / 10");
                            dlgAlert.setTitle("分數");
                            dlgAlert.setPositiveButton("確認!", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // User confirmed, restart the activity
                                    Intent intent = new Intent(number_main.this, number_main.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            //dlgAlert.setNegativeButton("No", null);
                            dlgAlert.setCancelable(true);
                            dlgAlert.create().show();

                        }
                    }
                },1000);
            }
        });


        buttonOp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonOp3.getText().equals(""+rightAnswer)){

                    buttonOp3.setBackgroundResource(R.drawable.right_answer_bg);
                    great=great+1;
                    level=level+1;
                    textLevel.setText("Q : "+ level + " / 10");
                    textRightAnswer.setText("RA : "+great+" / 10");
                }else {
                    level= level+1;
                    textLevel.setText("Q : "+ level + " / 10");
                    buttonOp3.setBackgroundResource(R.drawable.wrong_answer_bg);
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (level <10){
                            getARandomQuestion();
                        }else {
                            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(number_main.this);
                            dlgAlert.setMessage("得分 : "+ great +" / 10");
                            dlgAlert.setTitle("分數");
                            dlgAlert.setPositiveButton("確認!", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // User confirmed, restart the activity
                                    Intent intent = new Intent(number_main.this, number_main.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            //dlgAlert.setNegativeButton("No", null);
                            dlgAlert.setCancelable(true);
                            dlgAlert.create().show();
                        }
                    }
                },1000);
            }
        });

    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        music.stop(this);
    }


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        music.play(this, R.raw.betterday);
    }
}