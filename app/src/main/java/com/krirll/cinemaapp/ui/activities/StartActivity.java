package com.krirll.cinemaapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.krirll.cinemaapp.R;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        HandlerCompat.postDelayed(
                new Handler(),
                () -> startActivity(new Intent(StartActivity.this, MoviesActivity.class)),
                null,
                1500);
    }
}