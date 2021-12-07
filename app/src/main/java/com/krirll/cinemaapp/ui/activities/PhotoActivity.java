package com.krirll.cinemaapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.krirll.cinemaapp.R;
import com.krirll.cinemaapp.adapters.ImagesAdapter;
import com.krirll.cinemaapp.network.models.Images;
import com.krirll.cinemaapp.ui.contracts.PhotoContract;
import com.krirll.cinemaapp.ui.presenters.PhotoPresenter;

public class PhotoActivity extends AppCompatActivity implements PhotoContract {

    public static final String PHOTOS = "PHOTOS";
    public static final String CURRENT_INDEX = "INDEX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        Images listImages = (Images) getIntent().getSerializableExtra(PHOTOS);
        int selectedIndex = getIntent().getIntExtra(CURRENT_INDEX, 0);

        PhotoPresenter.getInstance(this);

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
        tab.selectTab(tab.getTabAt(selectedIndex));

        Button back = findViewById(R.id.back);
        back.setOnClickListener(view -> finish());

        Button save = findViewById(R.id.save);
        save.setOnClickListener(
                view -> {
                    ImageView currentImage = images.getChildAt(images.getCurrentItem()).findViewById(R.id.imageTouch);
                    PhotoPresenter.getInstance(this).savePhoto(currentImage.getDrawable(), this);
                }
        );
    }

    @Override
    public void showToast() {
        Toast.makeText(this, getString(R.string.success), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}