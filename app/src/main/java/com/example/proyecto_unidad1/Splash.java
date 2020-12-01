package com.example.proyecto_unidad1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    CountDownTimer cdt = new CountDownTimer(5000,1000) {
        @Override
        public void onTick(long segundos) {

        }
        @Override
        public void onFinish() {
            Intent i=new Intent(Splash.this,Login.class);
            startActivity(i);
            finish();
        }
    }.start();
}