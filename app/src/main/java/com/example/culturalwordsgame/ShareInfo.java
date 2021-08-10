package com.example.culturalwordsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class ShareInfo extends AppCompatActivity {

    private TextView mTextViewAnswer;
    private ImageView mImageViewCurrentImage;
    private EditText mEditTextTitle;
    private String mAnswer;
    private String mAnswerDescription;
    private String mTagImage;
    private Uri mImageUri;
    private int mCurrentImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_info);

        mTextViewAnswer = findViewById(R.id.answer_share);
        mImageViewCurrentImage = findViewById(R.id.img_share);
        mEditTextTitle = findViewById(R.id.edit_text_title_share);

        mAnswer = getIntent().getStringExtra("correct_answer");
        mAnswerDescription = getIntent().getStringExtra("correct_answer_description");
        mCurrentImage = getIntent().getIntExtra("current_image_share", R.drawable.icon_2);
        Drawable current_image = getResources().getDrawable(mCurrentImage);
        mTagImage = getIntent().getStringExtra("tagImage");

        mTextViewAnswer.setText(mAnswer + " : " + mAnswerDescription);
        mImageViewCurrentImage.setImageDrawable(current_image);
    }//end onCreate()

    public void shareCurrentInfo(View view){
        shareImage();
    }//end shareCurrentInfo()

    private void shareImage(){
        mImageUri = Uri.parse("android.resource://" + getPackageName()
                + "/drawable/" + mTagImage);
        String txt = mEditTextTitle.getText().toString() + "\n" + mAnswer + " : " + mAnswerDescription;
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, txt);
        shareIntent.putExtra(Intent.EXTRA_STREAM, mImageUri);
        shareIntent.setType("image/*");
        startActivity(Intent.createChooser(shareIntent, null));
    }//end shareImage()
}//end class