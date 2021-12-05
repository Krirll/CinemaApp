package com.krirll.cinemaapp.ui.contracts;

import com.krirll.cinemaapp.common.Messages;
import com.krirll.cinemaapp.network.models.Cinema;

import java.util.List;

public interface CinemasContract {
    void onSuccess(List<Cinema> listCinemas);
    void onError(Messages errorMessage);
    void startActivity(Cinema cinema);
}
