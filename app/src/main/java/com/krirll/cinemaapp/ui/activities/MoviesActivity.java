package com.krirll.cinemaapp.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.krirll.cinemaapp.R;
import com.krirll.cinemaapp.adapters.MoviesAdapter;
import com.krirll.cinemaapp.network.models.Movie;
import com.krirll.cinemaapp.ui.contracts.MoviesContract;
import com.krirll.cinemaapp.ui.presenters.MoviesPresenter;

import java.io.Serializable;
import java.util.List;

public class MoviesActivity extends AppCompatActivity implements MoviesContract {

    private MoviesAdapter adapter;
    private ShimmerFrameLayout shimmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        initShimmer();
        if (savedInstanceState != null) {
            adapter = (MoviesAdapter) savedInstanceState.getSerializable("adapter");
            hideShimmer();
        }
        else {
            adapter = new MoviesAdapter();
            MoviesPresenter.getInstance(this).getMoviesSchedule();
        }
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void initShimmer() {
        shimmer = findViewById(R.id.shimmer);
        shimmer.startShimmer();
    }

    private void hideShimmer() {
        if (shimmer.isShimmerStarted()) {
            shimmer.stopShimmer();
            shimmer.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSuccess(List<Movie> listMovies) {
        hideShimmer();
        for (Movie item : listMovies)
            item.description = item.description.replaceAll("[<p/>]", "");
        adapter.setList(listMovies);
    }

    @Override
    public void onError(String errorMessage) {
        //todo dialog builder
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable("adapter", (Serializable) adapter);
        super.onSaveInstanceState(outState);
    }
}