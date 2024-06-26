package com.example.elderbox.calculation;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.elderbox.R;

import java.util.Objects;
import java.util.Random;

public class number_MainActivity extends AppCompatActivity   {

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
        setContentView(R.layout.number_activity_main);


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

        int operation = new  Random().nextInt(4)+1;

        int optionA = new  Random().nextInt(100);
        int optionB = new  Random().nextInt(100);

        if (optionB == optionA) {
             optionA++;
             optionB--;
        }

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
                if (operation == 3) {
                    realOperation = "/";
                    if (firstNum < secondNum) {
                        rightAnswer = secondNum / (firstNum + 1);
                        textQuestion.setText(secondNum + " " + realOperation + " " + (firstNum + 1) + " = ?");
                    } else {
                        rightAnswer = firstNum / (secondNum + 1);
                        textQuestion.setText(firstNum + " " + realOperation + " " + (secondNum + 1) + " = ?");
                    }
                } else {
                    if (operation == 4) {
                        realOperation = "-";
                        if (firstNum < secondNum) {
                            rightAnswer = secondNum - firstNum;
                            textQuestion.setText(secondNum + " " + realOperation + " " + firstNum + " = ?");
                        } else {
                            rightAnswer = firstNum - secondNum;
                            textQuestion.setText(firstNum + " " + realOperation + " " + secondNum + " = ?");
                        }
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
                            Intent intent = new Intent(number_MainActivity.this, number_score_page.class);
                            intent.putExtra("EXTRA_SCORE", great*10);
                            startActivity(intent);
                            finish();
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
                            Intent intent = new Intent(number_MainActivity.this, number_score_page.class);
                            intent.putExtra("EXTRA_SCORE", great*10);
                            startActivity(intent);
                            finish();
                            
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
                                Intent intent = new Intent(number_MainActivity.this, number_score_page.class);
                                intent.putExtra("EXTRA_SCORE", great*10);
                                startActivity(intent);
                                finish();

                        }
                    }
                },1000);
            }
        });

    }
}
