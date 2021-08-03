package com.example.culturalwordsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class ShareInfo extends AppCompatActivity {

    private TextView mTextViewAnswer;
    private ImageView mImageViewCurrentImage;
    private EditText mEditTextTitle;
    private String answer;
    private String answer_description;
    private String tagImage;
    private Uri imageUri;
    private int image_index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_info);

        mTextViewAnswer = findViewById(R.id.answer_share);
        mImageViewCurrentImage = findViewById(R.id.img_share);
        mEditTextTitle = findViewById(R.id.edit_text_title_share);

        answer = getIntent().getStringExtra("correct_answer");
        answer_description = getIntent().getStringExtra("correct_answer_description");
        image_index = getIntent().getIntExtra("current_image_share", R.drawable.icon_2);
        Drawable current_image = getResources().getDrawable(image_index);
        tagImage = getIntent().getStringExtra("tagImage");

        mTextViewAnswer.setText(answer + " : " + answer_description);
        mImageViewCurrentImage.setImageDrawable(current_image);
    }//end onCreate()

    public void shareCurrentInfo(View view){
        shareImage();
    }//end shareCurrentInfo()

    private void shareImage(){
        imageUri = Uri.parse("android.resource://" + getPackageName()+ "/drawable/" + tagImage);
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, mEditTextTitle.getText().toString() + "\n" + answer + " : " + answer_description);
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        shareIntent.setType("*/*");
        startActivity(shareIntent);
    }


}