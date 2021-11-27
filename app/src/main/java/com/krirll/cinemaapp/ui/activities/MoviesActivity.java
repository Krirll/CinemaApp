package com.krirll.cinemaapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.krirll.cinemaapp.R;
import com.krirll.cinemaapp.adapters.MoviesAdapter;
import com.krirll.cinemaapp.network.models.MovieModel;
import com.krirll.cinemaapp.ui.contracts.MoviesContract;
import com.krirll.cinemaapp.ui.presenters.MoviesPresenter;

import java.util.List;

public class MoviesActivity extends AppCompatActivity implements MoviesContract {

    private MoviesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        MoviesPresenter.getInstance(this).getMoviesSchedule();
    }

    private void initRecyclerView() {
        /*RecyclerView recyclerView = findViewById(R.id.movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MoviesAdapter();*/
    }

    @Override
    public void onSuccess(List<MovieModel> listMovies) {
        initRecyclerView();
        adapter.setList(listMovies);
    }

    @Override
    public void onError(String errorMessage) {
        //todo dialog builder
    }
}