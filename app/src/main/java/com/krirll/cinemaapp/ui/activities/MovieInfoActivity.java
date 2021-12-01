package com.krirll.cinemaapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.krirll.cinemaapp.R;
import com.krirll.cinemaapp.network.models.Movie;

public class MovieInfoActivity extends AppCompatActivity {

    public static final String MOVIE_INFO = "MOVIE_INFO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //todo некоторые поля могут быть пустыми, их надо сделать GONE
        //  поправить расположение элементов на разметке
        //  сделать ViewPager
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);
        Movie movie = (Movie) getIntent().getSerializableExtra(MOVIE_INFO);
        TextView desc = findViewById(R.id.description);
        desc.setText(movie.description);
        TextView age = findViewById(R.id.age);
        age.setText(movie.ageRest);
        TextView genres = findViewById(R.id.genres);
        genres.setText(movie.genres.toString());
        TextView country = findViewById(R.id.country);
        country.setText(movie.country);
        TextView stars = findViewById(R.id.stars);
        stars.setText(movie.stars);
        TextView director = findViewById(R.id.director);
        director.setText(movie.director);
        TextView writer = findViewById(R.id.writer);
        writer.setText(movie.writer);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}