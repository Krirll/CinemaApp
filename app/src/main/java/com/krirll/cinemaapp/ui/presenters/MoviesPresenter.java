package com.krirll.cinemaapp.ui.presenters;

import com.krirll.cinemaapp.ui.contracts.MoviesContract;

public class MoviesPresenter {

    public static MoviesPresenter moviesPresenter;
    private final MoviesContract moviesContract;

    public static MoviesPresenter getInstance(MoviesContract view) {
        if (moviesPresenter == null)
            moviesPresenter = new MoviesPresenter(view);
        return moviesPresenter;
    }

    public MoviesPresenter(MoviesContract view) {
        this.moviesContract = view;
    }

    public void getMoviesSchedule() {
        //todo work with model
    }
}
