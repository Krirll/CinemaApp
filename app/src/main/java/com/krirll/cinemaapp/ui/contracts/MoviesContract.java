package com.krirll.cinemaapp.ui.contracts;

import com.krirll.cinemaapp.network.models.MovieModel;

import java.util.List;

public interface MoviesContract {
    void onSuccess(List<MovieModel> listMovies);
    void onError(String errorMessage);
}
