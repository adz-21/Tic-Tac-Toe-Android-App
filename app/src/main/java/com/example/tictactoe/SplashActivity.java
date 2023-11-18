package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        // create a new handler which will handle the delay
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1900);

        TextView t1 = findViewById(R.id.tic);
        TextView t2 = findViewById(R.id.tac);
        TextView t3 = findViewById(R.id.toe);

        YoYo.with(Techniques.Flash).duration(2100).repeat(1).playOn(t1);
        YoYo.with(Techniques.Flash).duration(2100).repeat(1).playOn(t2);
        YoYo.with(Techniques.Flash).duration(2100).repeat(1).playOn(t3);
    }}
