package com.krirll.cinemaapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.krirll.cinemaapp.R;
import com.krirll.cinemaapp.adapters.ImagesAdapter;
import com.krirll.cinemaapp.network.models.Genres;
import com.krirll.cinemaapp.network.models.Movie;
import com.krirll.cinemaapp.ui.contracts.MovieInfoContract;
import com.krirll.cinemaapp.ui.presenters.MovieInfoPresenter;


public class MovieInfoActivity extends AppCompatActivity implements MovieInfoContract {

    public static final String MOVIE_INFO = "MOVIE_INFO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);
        MovieInfoPresenter presenter = MovieInfoPresenter.getInstance(this);
        Movie movie = (Movie) getIntent().getSerializableExtra(MOVIE_INFO);

        ViewPager images = findViewById(R.id.viewPager);
        TabLayout tab = findViewById(R.id.tab);
        images.setAdapter(new ImagesAdapter(movie.listImages, this));
        tab.setupWithViewPager(images, true);

        TextView title = findViewById(R.id.title);
        title.setText(movie.title);

        TextView desc = findViewById(R.id.description);
        desc.setText(movie.description);

        TextView age = findViewById(R.id.age);
        age.setText(movie.ageRest);

        TextView genres = findViewById(R.id.genres);
        String genresString = "";
        for (Genres item : movie.genres)
            genresString = genresString.concat(item.name + ", ");
        genres.setText(genresString.substring(0, genresString.lastIndexOf(',')));

        TextView country = findViewById(R.id.country);
        country.setText(movie.country);

        TextView stars = findViewById(R.id.stars);
        stars.setText((movie.stars.isEmpty()) ? getString(R.string.no_stars) : movie.stars);

        TextView director = findViewById(R.id.director);
        director.setText(movie.director);

        Button button = findViewById(R.id.show);
        button.setOnClickListener(view -> presenter.onClick(movie.id));
    }

    @Override
    public void openCinemas(int id) {
        startActivity(
                new Intent(this, CinemasActivity.class)
                        .putExtra(CinemasActivity.CINEMAS, id)
        );
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}