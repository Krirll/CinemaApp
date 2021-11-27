package com.krirll.cinemaapp.network.retrofit;

import com.krirll.cinemaapp.network.ApiService;

public class RetrofitService {

    private final RetrofitClient client;

    public RetrofitService(RetrofitClient client) {
        this.client = client;
    }

    public ApiService getService() {
        String url = "https://kudago.com/public-api/v1.4/movies/";
        return client.getRetrofit(url).create(ApiService.class);
    }
}
