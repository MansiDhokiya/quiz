package com.example.gkquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class QuizActivity extends AppCompatActivity {

    private TextView questions;
    private TextView questioncome;

    private AppCompatButton option1,option2,option3,option4;
    private AppCompatButton nextBtn;
    private Timer quizTimer;
    private int totalTimeInMins=1;
    private int seconds=0;
    private final List<QuestionsList> questionsLists=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        final ImageView backBtn=findViewById(R.id.backBtn);
        final TextView timer=findViewById(R.id.timer);
        final TextView selectedTopicName=findViewById(R.id.topicName);

        questions=findViewById(R.id.questions);
        questioncome=findViewById(R.id.questioncome);

        option1=findViewById(R.id.option1);
        option2=findViewById(R.id.option2);
        option3=findViewById(R.id.option3);
        option4=findViewById(R.id.option4);
        nextBtn=findViewById(R.id.nextBtn);

        final String getSelectedTopicName=getIntent().getStringExtra("selectedTopic");

        selectedTopicName.setText(getSelectedTopicName);
        startTimer(timer);
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quizTimer.purge();
                quizTimer.cancel();
                startActivity(new Intent(QuizActivity.this,MainActivity.class));
                finish();
            }
        });
    }
    private void startTimer(TextView timerTextView){

        quizTimer=new Timer();
        quizTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(seconds==0){
                    totalTimeInMins--;
                    seconds=59;
                } else if (seconds==0 && totalTimeInMins==0) {
                    quizTimer.purge();
                    quizTimer.cancel();
                    Toast.makeText(QuizActivity.this,"Time over",Toast.LENGTH_LONG).show();

                    Intent intent=new Intent(QuizActivity.this,QuizResults.class);
                    intent.putExtra("correct",getCorrectAnswers());
                    intent.putExtra("incorrect",getIncorrectAnswers());
                    startActivity(intent);

                    finish();
                }
                else {
                    seconds--;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String finalMinutes= String.valueOf(totalTimeInMins);
                        String finalSeconds= String.valueOf(seconds);

                        if(finalMinutes.length()==1){
                            finalMinutes="0"+finalMinutes;
                        }
                        if(finalSeconds.length()==1){
                            finalSeconds="0"+finalSeconds;
                        }
                        timerTextView.setText(finalMinutes+":"+finalSeconds);
                    }
                });
            }
        },1000,1000);
    }
    private int getCorrectAnswers(){

       int correctAnswer=0;
       for(int i=0;i<questionsLists.size();i++){

           final String getUserSelectedAnswer=questionsLists.get(i).getUserSelectedAnswer();
           final String getAnswer=questionsLists.get(i).getAnswer();

           if(getUserSelectedAnswer.equals(getAnswer)){

               correctAnswer++;
           }
       }
       return correctAnswer;
    }

    private int getIncorrectAnswers(){

        int correctAnswer=0;
        for(int i=0;i<questionsLists.size();i++){

            final String getUserSelectedAnswer=questionsLists.get(i).getUserSelectedAnswer();
            final String getAnswer=questionsLists.get(i).getAnswer();

            if(!getUserSelectedAnswer.equals(getAnswer)){

                correctAnswer++;
            }
        }
        return correctAnswer;
    }

    @Override
    public void onBackPressed() {

        quizTimer.purge();
        quizTimer.cancel();
        startActivity(new Intent(QuizActivity.this,MainActivity.class));
        finish();
    }
}