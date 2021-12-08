package com.krirll.cinemaapp.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.krirll.cinemaapp.R;
import com.krirll.cinemaapp.adapters.CinemasAdapter;
import com.krirll.cinemaapp.common.Messages;
import com.krirll.cinemaapp.network.models.Cinema;
import com.krirll.cinemaapp.ui.contracts.CinemasContract;
import com.krirll.cinemaapp.ui.presenters.CinemasPresenter;

import java.util.List;

public class CinemasActivity extends AppCompatActivity implements CinemasContract {

    public static final String CINEMAS = "CINEMAS";

    private SwipeRefreshLayout swipeRefreshLayout;
    private CinemasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinemas);
        int id = getIntent().getIntExtra(CINEMAS, 0);
        swipeRefreshLayout = findViewById(R.id.refresh);
        if (savedInstanceState != null) {
            adapter = (CinemasAdapter) savedInstanceState.getSerializable("adapter");
            if (adapter.getList().size() > 0)
                hideShimmer();
        }
        else {
            adapter = new CinemasAdapter();
        }
        if (swipeRefreshLayout.isRefreshing() || adapter.getList().size() == 0) {
            CinemasPresenter.getInstance(this).getCinemas(id);
        }
        swipeRefreshLayout.setOnRefreshListener(
                () -> CinemasPresenter.getInstance(this).getCinemas(id));
        initRecyclerView();
    }

    @Override
    protected void onResume() {
        adapter.setListener(CinemasPresenter.getInstance(this).getListener());
        super.onResume();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.cinemas);
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
    public void onSuccess(List<Cinema> listCinemas) {
        hideShimmer();
        hideSwipe();
        if (listCinemas.size() != 0)
            adapter.setList(listCinemas);
        else {
            TextView message = findViewById(R.id.message);
            message.setText(getString(R.string.no_info_about_cinemas));
        }
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
    public void startActivity(Cinema cinema) {
        startActivity(
                new Intent(
                        this, CinemaInfoActivity.class
                ).putExtra(CinemaInfoActivity.CINEMA, cinema)
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