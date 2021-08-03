package com.example.culturalwordsgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String CURRENT_BUNDLE_INDEX = "CURRENT_BUNDLE_INDEX";
    private static final String CURRENT_BUNDLE_LANGUAGE = "CURRENT_BUNDLE_LANGUAGE";
    private int[] images = {
            R.drawable.icon_1,
            R.drawable.icon_2,
            R.drawable.icon_3,
            R.drawable.icon_4,
            R.drawable.icon_5,
            R.drawable.icon_6,
            R.drawable.icon_7,
            R.drawable.icon_8,
            R.drawable.icon_9,
            R.drawable.icon_10,
            R.drawable.icon_11,
            R.drawable.icon_12,
            R.drawable.icon_13,
    };
    private String[] tagImages = {
            "icon_1", "icon_2", "icon_3", "icon_4", "icon_5", "icon_6",
            "icon_7", "icon_8", "icon_9", "icon_10", "icon_11", "icon_12", "icon_13"
    };

    private String[] answers;
    private String[] answers_description;
    private ImageView mImageView;
    private int mCurrentIndex = 0;
    private Random mRandom;
    private SharedPreferences sharedPreferences;
    private String mCurrentLanguage = Locale.getDefault().getLanguage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("app pref", MODE_PRIVATE);

        answers = getResources().getStringArray(R.array.answers);
        answers_description = getResources().getStringArray(R.array.answer_description);
        mImageView = findViewById(R.id.imageView);
        mRandom = new Random();
        mCurrentIndex = sharedPreferences.getInt("current image", mCurrentIndex);
        showImage(mCurrentIndex);
    }//end onCreate()

    public void changeImage(View view){
        mCurrentIndex = mRandom.nextInt(images.length);
        showImage(mCurrentIndex);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("current image", mCurrentIndex);
        editor.apply();
    }//end changeImage()

    public void showAnswer(View view){
        Intent intent = new Intent(this, AnswerActivity.class);
        intent.putExtra("correct_answer", answers[mCurrentIndex]);
        intent.putExtra("correct_answer_description", answers_description[mCurrentIndex]);
        startActivity(intent);
    }//end showAnswer()


    public void shareImage(View view){
        Intent intent = new Intent(this, ShareInfo.class);
        intent.putExtra("correct_answer", answers[mCurrentIndex]);
        intent.putExtra("correct_answer_description", answers_description[mCurrentIndex]);
        intent.putExtra("current_image_share", images[mCurrentIndex]);
        intent.putExtra("tagImage", tagImages[mCurrentIndex]);
        startActivity(intent);
    }//end shareImage()

    public void changeLanguage(View view){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.change_lang_text)
                .setItems(R.array.languages, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                mCurrentLanguage = "ar";
                                break;
                            case 1:
                                mCurrentLanguage = "en";
                                break;
                        }
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("current language", mCurrentLanguage);
                        editor.apply();
                        currentLanguage();
                    }
                }).create();
        alertDialog.show();
    }//end changeLanguage()

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_BUNDLE_INDEX, mCurrentIndex);
        String l = sharedPreferences.getString("current language", Locale.getDefault().getLanguage());
        outState.putString(CURRENT_BUNDLE_LANGUAGE, l);
    }//end onSaveInstanceState()

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCurrentIndex = savedInstanceState.getInt(CURRENT_BUNDLE_INDEX);
        showImage(mCurrentIndex);
        mCurrentLanguage = savedInstanceState.getString(CURRENT_BUNDLE_LANGUAGE);
        currentLanguage();
    }//end onRestoreInstanceState()

    private void showImage(int index){
        Drawable currentImage = getResources().getDrawable(images[index]);
        mImageView.setImageDrawable(currentImage);
    }//end showImage()

    private void currentLanguage(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        LocaleHelper.setLocale(MainActivity.this, mCurrentLanguage);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(intent);
    }//end currentLanguage()

}//end class