package com.krirll.cinemaapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.krirll.cinemaapp.R;
import com.krirll.cinemaapp.adapters.ImagesAdapter;
import com.krirll.cinemaapp.network.models.Cinema;
import com.krirll.cinemaapp.ui.contracts.CinemaInfoContract;

public class CinemaInfoActivity extends AppCompatActivity implements CinemaInfoContract {

    public static final String CINEMA = "CINEMA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_info);
        Cinema cinema = (Cinema) getIntent().getSerializableExtra(CINEMA);

        ViewPager images = findViewById(R.id.viewPager);
        TabLayout tab = findViewById(R.id.tab);
        images.setAdapter(new ImagesAdapter(cinema.place.listImages, this));
        tab.setupWithViewPager(images, true);

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void showPhoto() {
        startActivity(
                new Intent(
                        this, PhotoActivity.class
                )//.putExtra()
        );
    }
}