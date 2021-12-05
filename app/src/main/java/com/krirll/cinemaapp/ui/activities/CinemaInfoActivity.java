package com.krirll.cinemaapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.krirll.cinemaapp.R;

public class CinemaInfoActivity extends AppCompatActivity {

    public static final String CINEMA = "CINEMA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_info);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}