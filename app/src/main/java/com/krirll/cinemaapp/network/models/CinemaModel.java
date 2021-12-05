package com.krirll.cinemaapp.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CinemaModel {

    @SerializedName("results")
    @Expose
    public List<Cinema> cinemas;
}

