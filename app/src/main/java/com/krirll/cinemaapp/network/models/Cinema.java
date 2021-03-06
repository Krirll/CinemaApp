package com.krirll.cinemaapp.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cinema implements Serializable {

    @SerializedName("place")
    @Expose
    public Place place;

    @SerializedName("datetime")
    @Expose
    public Long dateTime;

    @SerializedName("price")
    @Expose
    public String price;
}

