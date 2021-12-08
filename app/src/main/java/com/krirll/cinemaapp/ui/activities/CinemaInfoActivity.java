package com.krirll.cinemaapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    private TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_info);
        Cinema cinema = (Cinema) getIntent().getSerializableExtra(CINEMA);
        list = new Images(cinema.place.listImages);
        CinemaInfoPresenter.getInstance(this);

        ViewPager images = findViewById(R.id.viewPager);
        tab = findViewById(R.id.tab);
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
        price.setText((cinema.price == null) ? getString(R.string.no_info) : cinema.price);

        TextView session = findViewById(R.id.session);
        session.setText(new SimpleDateFormat(
                                "HH:mm",
                                Locale.ROOT
                        ).format(new Date(cinema.dateTime * 1000))
        );

        TextView address = findViewById(R.id.address);
        address.setText(cinema.place.address);

        TextView subway = findViewById(R.id.subway);
        subway.setText(cinema.place.subway);

        TextView phone = findViewById(R.id.phone);
        phone.setText(cinema.place.phone);

        Button call = findViewById(R.id.callButton);
        call.setOnClickListener(view -> CinemaInfoPresenter.getInstance(this).makeCall(cinema.place.phone));

        Button map = findViewById(R.id.mapButton);
        map.setOnClickListener(view -> CinemaInfoPresenter.getInstance(this).openMap(cinema.place.address));
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void showPhoto() {
        CinemaInfoPresenter.getInstance(this).show(list, tab.getSelectedTabPosition());
    }

    @Override
    public void openCallApp(String phoneNumber) {
        startActivity(
                new Intent(Intent.ACTION_DIAL).setData(Uri.parse(getString(R.string.tel, phoneNumber)))
        );
    }

    @Override
    public void openMap(String address) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(getString(R.string.geo, address))));
        }
        catch(ActivityNotFoundException ex) {
            Toast.makeText(this, getString(R.string.no_maps), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void startActivity(Images list, int id) {
        startActivity(
                new Intent(
                        this, PhotoActivity.class
                ).putExtra(PhotoActivity.PHOTOS, list)
                 .putExtra(PhotoActivity.CURRENT_INDEX, id)
        );
    }
}