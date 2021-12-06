package com.krirll.cinemaapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;
import com.krirll.cinemaapp.R;
import com.krirll.cinemaapp.adapters.ImagesAdapter;
import com.krirll.cinemaapp.network.models.Image;
import com.krirll.cinemaapp.network.models.Images;

import java.io.Serializable;
import java.util.List;

public class PhotoActivity extends AppCompatActivity {

    public static final String PHOTOS = "PHOTOS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        Images listImages = (Images) getIntent().getSerializableExtra(PHOTOS);

        //todo toolbar
        //Toolbar toolbar = findViewById(R.id.toolbar);

        ViewPager images = findViewById(R.id.viewPager);
        TabLayout tab = findViewById(R.id.tab);
        images.setAdapter(
                new ImagesAdapter(
                        listImages.getList(),
                        this,
                        null,
                        R.layout.photo_item
                )
        );
        tab.setupWithViewPager(images, true);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}