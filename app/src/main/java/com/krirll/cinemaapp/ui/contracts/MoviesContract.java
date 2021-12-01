package com.krirll.cinemaapp.ui.contracts;

import com.krirll.cinemaapp.common.Messages;
import com.krirll.cinemaapp.network.models.Movie;

import java.util.List;

public interface MoviesContract {
    void onSuccess(List<Movie> listMovies);
    void onError(Messages errorMessage);
    void startActivity(Movie movie);
}
