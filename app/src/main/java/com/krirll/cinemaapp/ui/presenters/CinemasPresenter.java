package com.krirll.cinemaapp.ui.presenters;

import androidx.annotation.NonNull;

import com.krirll.cinemaapp.common.Messages;
import com.krirll.cinemaapp.common.TimeConverter;
import com.krirll.cinemaapp.network.models.CinemaModel;
import com.krirll.cinemaapp.network.retrofit.RetrofitClient;
import com.krirll.cinemaapp.network.retrofit.RetrofitService;
import com.krirll.cinemaapp.ui.contracts.CinemaListener;
import com.krirll.cinemaapp.ui.contracts.CinemasContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CinemasPresenter {

    public static CinemasPresenter cinemaPresenter;

    private static CinemasContract cinemaContract;
    private final RetrofitService service;
    private final CinemaListener listener;

    public static CinemasPresenter getInstance(CinemasContract view) {
        if (cinemaPresenter == null)
            cinemaPresenter = new CinemasPresenter(view);
        else
            cinemaContract = view;
        return cinemaPresenter;
    }

    private CinemasPresenter(CinemasContract view) {
        cinemaContract = view;
        service = new RetrofitService(new RetrofitClient());
        listener = movie -> cinemaContract.startActivity(movie);
    }

    public void getCinemas(int id) {
        service.getService()
                .getCinemas(id, Long.toString(new TimeConverter().getCurrentTimeStamp()))
                .enqueue(new Callback<CinemaModel>() {
                    @Override
                    public void onResponse(@NonNull Call<CinemaModel> call, @NonNull Response<CinemaModel> response) {
                        if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                            cinemaContract.onSuccess(response.body().cinemas);
                        }
                        else
                            cinemaContract.onError(Messages.SERVER_ERROR);
                    }

                    @Override
                    public void onFailure(@NonNull Call<CinemaModel> call, @NonNull Throwable t) {
                        cinemaContract.onError(Messages.INTERNET_CONNECTION_ERROR);
                    }
                });
    }

    public CinemaListener getListener() {
        return listener;
    }
}
