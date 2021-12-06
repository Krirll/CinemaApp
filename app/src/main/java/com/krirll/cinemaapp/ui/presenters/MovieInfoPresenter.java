package com.krirll.cinemaapp.ui.presenters;

import com.krirll.cinemaapp.ui.contracts.MovieInfoContract;

public class MovieInfoPresenter {

    private static MovieInfoPresenter movieInfoPresenter;
    private static MovieInfoContract movieInfoContract;

    public static MovieInfoPresenter getInstance(MovieInfoContract view) {
        if (movieInfoPresenter == null)
            movieInfoPresenter = new MovieInfoPresenter(view);
        return movieInfoPresenter;
    }

    private MovieInfoPresenter(MovieInfoContract view) {
        movieInfoContract = view;
    }

    public void onClick(int id) {
        movieInfoContract.openCinemas(id);
    }

}
