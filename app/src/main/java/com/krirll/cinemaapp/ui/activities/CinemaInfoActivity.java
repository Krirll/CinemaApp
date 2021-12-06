package com.krirll.cinemaapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.krirll.cinemaapp.R;
import com.krirll.cinemaapp.adapters.ImagesAdapter;
import com.krirll.cinemaapp.network.models.Cinema;
import com.krirll.cinemaapp.network.models.Images;
import com.krirll.cinemaapp.ui.contracts.CinemaInfoContract;
import com.krirll.cinemaapp.ui.presenters.CinemaInfoPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CinemaInfoActivity extends AppCompatActivity implements CinemaInfoContract {

    public static final String CINEMA = "CINEMA";

    private Images list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_info);
        Cinema cinema = (Cinema) getIntent().getSerializableExtra(CINEMA);
        list = new Images(cinema.place.listImages);
        CinemaInfoPresenter.getInstance(this);

        ViewPager images = findViewById(R.id.viewPager);
        TabLayout tab = findViewById(R.id.tab);
        images.setAdapter(
                new ImagesAdapter(
                        cinema.place.listImages,
                        this,
                        this,
                        R.layout.image_item
                )
        );
        tab.setupWithViewPager(images, true);

        TextView title = findViewById(R.id.title);
        title.setText(cinema.place.title);

        TextView price = findViewById(R.id.price);
        price.setText(
                getString(
                        R.string.price,
                        (cinema.price == null) ? R.string.no_info : cinema.price
                )
        );

        TextView session = findViewById(R.id.session);
        session.setText(
                getString(
                        R.string.session,
                        new SimpleDateFormat(
                                "HH:mm",
                                Locale.ROOT
                        ).format(new Date(cinema.dateTime * 1000))
                )
        );

        TextView address = findViewById(R.id.address);
        address.setText(
                getString(
                        R.string.address,
                        cinema.place.address
                )
        );

        TextView subway = findViewById(R.id.subway);
        subway.setText(
                getString(
                        R.string.subway,
                        cinema.place.subway
                )
        );

        TextView phone = findViewById(R.id.phone);
        phone.setText(
                getString(
                        R.string.phone,
                        cinema.place.phone
                )
        );
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void showPhoto() {
        CinemaInfoPresenter.getInstance(this).show(list);
    }

    @Override
    public void startActivity(Images list) {
        startActivity(
                new Intent(
                        this, PhotoActivity.class
                ).putExtra(PhotoActivity.PHOTOS, list)
        );
    }
}