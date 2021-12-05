package com.krirll.cinemaapp.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.krirll.cinemaapp.R;
import com.krirll.cinemaapp.adapters.MoviesAdapter;
import com.krirll.cinemaapp.common.Messages;
import com.krirll.cinemaapp.network.models.Movie;
import com.krirll.cinemaapp.ui.contracts.MoviesContract;
import com.krirll.cinemaapp.ui.presenters.MoviesPresenter;

import java.util.List;

public class MoviesActivity extends AppCompatActivity implements MoviesContract {

    private MoviesAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        swipeRefreshLayout = findViewById(R.id.refresh);
        if (savedInstanceState != null) {
            adapter = (MoviesAdapter) savedInstanceState.getSerializable("adapter");
            if (adapter.getList().size() > 0)
                hideShimmer();
        }
        else {
            adapter = new MoviesAdapter();
        }
        if (swipeRefreshLayout.isRefreshing() || adapter.getList().size() == 0) {
            MoviesPresenter.getInstance(this).getMoviesSchedule();
        }
        swipeRefreshLayout.setOnRefreshListener(
                () -> MoviesPresenter.getInstance(this).getMoviesSchedule());
        initRecyclerView();
    }

    @Override
    protected void onResume() {
        adapter.setListener(MoviesPresenter.getInstance(this).getListener());
        super.onResume();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void hideShimmer() {
        ShimmerFrameLayout shimmer = findViewById(R.id.shimmer);
        shimmer.setVisibility(View.GONE);
    }

    private void hideSwipe() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onSuccess(List<Movie> listMovies) {
        hideShimmer();
        hideSwipe();
        for (Movie item : listMovies) //очищаю все элементы от тегов <p></p>
            item.description = item.description.replaceAll("[<p/>]", "");
        adapter.setList(listMovies);
    }

    @Override
    public void onError(Messages errorMessage) {
        hideSwipe();
        String message = "";
        //в зависимости от ошибки (enum), из ресурсов береться определенная строка
        switch(errorMessage) {
            case INTERNET_CONNECTION_ERROR:
                message = getString(R.string.internet_error);
                break;
            case SERVER_ERROR:
                message = getString(R.string.server_error);
            default: {}
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startActivity(Movie movie) {
        startActivity(
                new Intent(
                        this,
                        MovieInfoActivity.class
                )
                .putExtra(MovieInfoActivity.MOVIE_INFO, movie)
        );
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onPause() {
        adapter.setListener(null);
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable("adapter", adapter);
        super.onSaveInstanceState(outState);
    }
}