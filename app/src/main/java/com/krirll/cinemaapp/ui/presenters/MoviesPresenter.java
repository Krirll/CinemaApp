package com.krirll.cinemaapp.ui.presenters;

import androidx.annotation.NonNull;

import com.krirll.cinemaapp.common.Messages;
import com.krirll.cinemaapp.common.TimeConverter;
import com.krirll.cinemaapp.network.models.MovieModel;
import com.krirll.cinemaapp.network.retrofit.RetrofitClient;
import com.krirll.cinemaapp.network.retrofit.RetrofitService;
import com.krirll.cinemaapp.ui.contracts.MoviesContract;
import com.krirll.cinemaapp.ui.contracts.MoviesListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesPresenter {

    public static MoviesPresenter moviesPresenter;

    private static MoviesContract moviesContract;
    private final RetrofitService service;
    private final MoviesListener listener;

    public static MoviesPresenter getInstance(MoviesContract view) {
        if (moviesPresenter == null)
            moviesPresenter = new MoviesPresenter(view);
        else
            moviesContract = view;
        return moviesPresenter;
    }

    public MoviesPresenter(MoviesContract view) {
        moviesContract = view;
        service = new RetrofitService(new RetrofitClient());
        listener = movie -> moviesContract.startActivity(movie);
    }

    public void getMoviesSchedule() {
        service.getService()
                .getMovies(Long.toString(new TimeConverter().getCurrentTimeStamp()))
                .enqueue(new Callback<MovieModel>() {
                    @Override
                    public void onResponse(@NonNull Call<MovieModel> call, @NonNull Response<MovieModel> response) {
                        if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                            moviesContract.onSuccess(response.body().movies);
                        }
                        else
                            moviesContract.onError(Messages.INTERNET_CONNECTION_ERROR);
                    }

                    @Override
                    public void onFailure(@NonNull Call<MovieModel> call, @NonNull Throwable t) {
                        moviesContract.onError(Messages.SERVER_ERROR);
                    }
                });
    }

    public MoviesListener getListener() {
        return listener;
    }
}
