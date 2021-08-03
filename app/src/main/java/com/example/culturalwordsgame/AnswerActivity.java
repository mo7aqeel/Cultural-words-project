package com.example.culturalwordsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {

    private TextView mTextViewAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        mTextViewAnswer = findViewById(R.id.answer);
        String answer = getIntent().getStringExtra("correct_answer");
        String answer_description = getIntent().getStringExtra("correct_answer_description");
        mTextViewAnswer.setText(answer + " : " + answer_description);
    }//end onCreate()

    public void back(View view){
        finish();
    }//end back()
}//end class