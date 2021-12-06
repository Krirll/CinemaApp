package com.krirll.cinemaapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.krirll.cinemaapp.R;
import com.krirll.cinemaapp.adapters.ImagesAdapter;
import com.krirll.cinemaapp.network.models.Image;

import java.io.Serializable;
import java.util.List;

class Images implements Serializable {

    private final List<Image> list;

    public Images(List<Image> newList) {
        list = newList;
    }

    public List<Image> getList() {
        return list;
    }
}

public class PhotoActivity extends AppCompatActivity {

    public static final String PHOTOS = "PHOTOS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        Images listImages = (Images) getIntent().getSerializableExtra(PHOTOS);

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