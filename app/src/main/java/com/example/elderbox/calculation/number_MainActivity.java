package com.example.elderbox.calculation;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.elderbox.R;

import java.util.Random;

public class number_MainActivity extends AppCompatActivity   {

    // Declaration of member variables for the activity
    private TextView textLevel, textRightAnswer, textQuestion;      // TextViews for displaying level, number of correct answers, and question
    private Button buttonOp1, buttonOp2, buttonOp3;                 // Buttons representing answer options

    private int level = 0;      // Variable to track the current level
    int great = 0;              // Variable to track the number of correct answers
    int rightAnswer = 0;        // Variable to store the correct answer for the current question

    String realOperation = ""; // Variable to store the arithmetic operation symbol for the current question

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.number_activity_main);


        // Retrieving references to the necessary UI elements
        textLevel = findViewById(R.id.textQuestionNum); // Text view for displaying the current question number
        textRightAnswer = findViewById(R.id.textrightAnswered); // Text view for displaying the number of correctly answered questions
        textQuestion = findViewById(R.id.textQuestions); // Text view for displaying the question itself

        buttonOp1 = findViewById(R.id.buttonOption1); // Button for the first option
        buttonOp2 = findViewById(R.id.buttonOption2); // Button for the second option
        buttonOp3 = findViewById(R.id.buttonOption3); // Button for the third option

// Setting the text for the level and right answer counters
        textLevel.setText("Q : " + level + " / 10"); // Displaying the current question number out of 10
        textRightAnswer.setText("RA " + great + " / 10"); // Displaying the number of correctly answered questions out of 10

// Checking if the level is less than 10 to continue the game
        if (level < 10) {
            getARandomQuestion(); // Getting a random question for the next level
        }

    }

    private  void  getARandomQuestion(){

        // Setting the background resource for buttonOp1, buttonOp2, and buttonOp3
        buttonOp1.setBackgroundResource(R.drawable.buttons_option_bg); // Setting the background for the first option button
        buttonOp2.setBackgroundResource(R.drawable.buttons_option_bg); // Setting the background for the second option button
        buttonOp3.setBackgroundResource(R.drawable.buttons_option_bg); // Setting the background for the third option button

// Generating random numbers for calculations and options
        int firstNum = new Random().nextInt(10); // Generating a random number between 0 and 9 for the first operand
        int secondNum = new Random().nextInt(10); // Generating a random number between 0 and 9 for the second operand

        int operation = new Random().nextInt(4) + 1; // Generating a random number between 1 and 4 for the operation

        int optionA = new Random().nextInt(100); // Generating a random number between 0 and 99 for option A
        int optionB = new Random().nextInt(100); // Generating a random number between 0 and 99 for option B

// Checking if optionB is equal to optionA and adjusting the values if they are equal
        if (optionB == optionA) {
            optionA++; // Incrementing optionA by 1 to make it different from optionB
            optionB--; // Decrementing optionB by 1 to make it different from optionA
        }

        // Checking the value of the 'operation' variable to determine the arithmetic operation
        if (operation == 1) {
            realOperation = "+"; // Setting the operation symbol as addition
            rightAnswer = firstNum + secondNum; // Calculating the correct answer
            textQuestion.setText(firstNum + " " + realOperation + " " + secondNum + " = ?"); // Setting the question text with the addition operation
        } else {
            if (operation == 2) {
                realOperation = "*"; // Setting the operation symbol as multiplication
                rightAnswer = firstNum * secondNum; // Calculating the correct answer
                textQuestion.setText(firstNum + " " + realOperation + " " + secondNum + " = ?"); // Setting the question text with the multiplication operation
            } else {
                if (operation == 3) {
                    realOperation = "/"; // Setting the operation symbol as division
                    if (firstNum < secondNum) {
                        rightAnswer = secondNum / (firstNum + 1); // Calculating the correct answer when firstNum < secondNum
                        textQuestion.setText(secondNum + " " + realOperation + " " + (firstNum + 1) + " = ?"); // Setting the question text with the division operation
                    } else {
                        rightAnswer = firstNum / (secondNum + 1); // Calculating the correct answer when firstNum >= secondNum
                        textQuestion.setText(firstNum + " " + realOperation + " " + (secondNum + 1) + " = ?"); // Setting the question text with the division operation
                    }
                } else {
                    if (operation == 4) {
                        realOperation = "-"; // Setting the operation symbol as subtraction
                        if (firstNum < secondNum) {
                            rightAnswer = secondNum - firstNum; // Calculating the correct answer when firstNum < secondNum
                            textQuestion.setText(secondNum + " " + realOperation + " " + firstNum + " = ?"); // Setting the question text with the subtraction operation
                        } else {
                            rightAnswer = firstNum - secondNum; // Calculating the correct answer when firstNum >= secondNum
                            textQuestion.setText(firstNum + " " + realOperation + " " + secondNum + " = ?"); // Setting the question text with the subtraction operation
                        }
                    }
                }
            }
        }

        // Generating a random position for the correct answer among the options
        int position = new Random().nextInt(3) + 1;

// Setting the text for the option buttons based on the position
        if (position == 1) {
            buttonOp1.setText("" + rightAnswer); // Setting the correct answer as the text for buttonOp1
            buttonOp2.setText("" + optionA); // Setting optionA as the text for buttonOp2
            buttonOp3.setText("" + optionB); // Setting optionB as the text for buttonOp3
        } else {
            buttonOp1.setText("" + optionA); // Setting optionA as the text for buttonOp1

            if (position == 2) {
                buttonOp2.setText("" + rightAnswer); // Setting the correct answer as the text for buttonOp2
                buttonOp3.setText("" + optionB); // Setting optionB as the text for buttonOp3
            } else {
                buttonOp2.setText("" + optionB); // Setting optionB as the text for buttonOp2
                buttonOp3.setText("" + rightAnswer); // Setting the correct answer as the text for buttonOp3
            }
        }

        buttonOp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonOp1.getText().equals("" + rightAnswer)) {
                    // When the correct answer is selected
                    buttonOp1.setBackgroundResource(R.drawable.right_answer_bg); // Setting the background for buttonOp1 as the right answer background
                    great = great + 1; // Incrementing the number of correctly answered questions
                    level = level + 1; // Incrementing the current level
                    textLevel.setText("Q : " + level + " / 10"); // Updating the level text
                    textRightAnswer.setText("RA : " + great + " / 10"); // Updating the correct answer text
                } else {
                    // When an incorrect answer is selected
                    level = level + 1; // Incrementing the current level
                    textLevel.setText("Q : " + level + " / 10"); // Updating the level text
                    buttonOp1.setBackgroundResource(R.drawable.wrong_answer_bg); // Setting the background for buttonOp1 as the wrong answer background
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (level < 10) {
                            // If there are more questions remaining
                            getARandomQuestion(); // Getting a random question for the next level
                        } else {
                            // If all questions have been answered
                            Intent intent = new Intent(number_MainActivity.this, number_score_page.class);
                            intent.putExtra("EXTRA_SCORE", great * 10); // Passing the final score as an extra to the score page activity
                            startActivity(intent); // Starting the score page activity
                            finish(); // Finishing the current activity
                        }
                    }
                }, 1000); // Delaying the execution of the code inside the runnable for 1000 milliseconds (1 second)
            }
        });


        buttonOp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonOp2.getText().equals("" + rightAnswer)) {
                    // When the correct answer is selected
                    buttonOp2.setBackgroundResource(R.drawable.right_answer_bg); // Setting the background for buttonOp2 as the right answer background
                    great = great + 1; // Incrementing the number of correctly answered questions
                    level = level + 1; // Incrementing the current level
                    textLevel.setText("Q : " + level + " / 10"); // Updating the level text
                    textRightAnswer.setText("RA : " + great + " / 10"); // Updating the correct answer text
                } else {
                    // When an incorrect answer is selected
                    level = level + 1; // Incrementing the current level
                    textLevel.setText("Q : " + level + " / 10"); // Updating the level text
                    buttonOp2.setBackgroundResource(R.drawable.wrong_answer_bg); // Setting the background for buttonOp2 as the wrong answer background
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (level < 10) {
                            // If there are more questions remaining
                            getARandomQuestion(); // Getting a random question for the next level
                        } else {
                            // If all questions have been answered
                            Intent intent = new Intent(number_MainActivity.this, number_score_page.class);
                            intent.putExtra("EXTRA_SCORE", great * 10); // Passing the final score as an extra to the score page activity
                            startActivity(intent); // Starting the score page activity
                            finish(); // Finishing the current activity
                        }
                    }
                }, 1000); // Delaying the execution of the code inside the runnable for 1000 milliseconds (1 second)
            }
        });


        buttonOp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonOp3.getText().equals("" + rightAnswer)) {
                    // When the correct answer is selected
                    buttonOp3.setBackgroundResource(R.drawable.right_answer_bg); // Setting the background for buttonOp3 as the right answer background
                    great = great + 1; // Incrementing the number of correctly answered questions
                    level = level + 1; // Incrementing the current level
                    textLevel.setText("Q : " + level + " / 10"); // Updating the level text
                    textRightAnswer.setText("RA : " + great + " / 10"); // Updating the correct answer text
                } else {
                    // When an incorrect answer is selected
                    level = level + 1; // Incrementing the current level
                    textLevel.setText("Q : " + level + " / 10"); // Updating the level text
                    buttonOp3.setBackgroundResource(R.drawable.wrong_answer_bg); // Setting the background for buttonOp3 as the wrong answer background
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (level < 10) {
                            // If there are more questions remaining
                            getARandomQuestion(); // Getting a random question for the next level
                        } else {
                            // If all questions have been answered
                            Intent intent = new Intent(number_MainActivity.this, number_score_page.class);
                            intent.putExtra("EXTRA_SCORE", great * 10); // Passing the final score as an extra to the score page activity
                            startActivity(intent); // Starting the score page activity
                            finish(); // Finishing the current activity
                        }
                    }
                }, 1000); // Delaying the execution of the code inside the runnable for 1000 milliseconds (1 second)
            }
        });

    }
}
