package com.example.gkquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String selectedTopicName="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView java=findViewById(R.id.javaicon);
        final ImageView php=findViewById(R.id.phpIcon);
        final ImageView html=findViewById(R.id.htmlIcon);
        final  ImageView android=findViewById(R.id.androidIcon);

        final Button startbtn=findViewById(R.id.startquizbtn);

        java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName="java";

                java.setBackgroundResource(R.drawable.round_back_white_stroke);
                php.setBackgroundResource(R.drawable.round_back_white);
                html.setBackgroundResource(R.drawable.round_back_white);
                android.setBackgroundResource(R.drawable.round_back_white);
            }
        });

        php.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName="php";

                php.setBackgroundResource(R.drawable.round_back_white_stroke);
                java.setBackgroundResource(R.drawable.round_back_white);
                html.setBackgroundResource(R.drawable.round_back_white);
                android.setBackgroundResource(R.drawable.round_back_white);
            }
        });

        html.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName="html";

                html.setBackgroundResource(R.drawable.round_back_white_stroke);
                php.setBackgroundResource(R.drawable.round_back_white);
                java.setBackgroundResource(R.drawable.round_back_white);
                android.setBackgroundResource(R.drawable.round_back_white);
            }
        });

        android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName="android";

                android.setBackgroundResource(R.drawable.round_back_white_stroke);
                php.setBackgroundResource(R.drawable.round_back_white);
                html.setBackgroundResource(R.drawable.round_back_white);
                java.setBackgroundResource(R.drawable.round_back_white);
            }
        });

        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(selectedTopicName.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please select the Topic",Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent=new Intent(MainActivity.this,QuizActivity.class);
                    intent.putExtra("selectedTopic",selectedTopicName);
                    startActivity(intent);
                }
            }
        });
    }
}