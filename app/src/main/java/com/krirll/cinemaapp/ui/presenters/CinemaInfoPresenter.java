package com.krirll.cinemaapp.ui.presenters;

import com.krirll.cinemaapp.network.models.Images;
import com.krirll.cinemaapp.ui.contracts.CinemaInfoContract;

public class CinemaInfoPresenter {

    private static CinemaInfoPresenter cinemaInfoPresenter;
    private static CinemaInfoContract cinemaInfoContract;

    public CinemaInfoPresenter(CinemaInfoContract view) {
        cinemaInfoContract = view;
    }

    public static CinemaInfoPresenter getInstance(CinemaInfoContract view) {
        if (cinemaInfoPresenter == null)
            cinemaInfoPresenter = new CinemaInfoPresenter(view);
        return cinemaInfoPresenter;
    }

    public void show(Images list, int id) {
        cinemaInfoContract.startActivity(list, id);
    }

    public void makeCall() {
        cinemaInfoContract.openCallApp();
    }

    public void openMap() {
        cinemaInfoContract.openMap();
    }
}
