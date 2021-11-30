package com.krirll.cinemaapp.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieModel {

    @SerializedName("results")
    @Expose
    public List<Movie> movies;
}

