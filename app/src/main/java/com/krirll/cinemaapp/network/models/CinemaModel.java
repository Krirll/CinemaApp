package com.krirll.cinemaapp.network.models;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.security.Timestamp;
import java.util.List;

public class CinemaModel {
    @Override
    public boolean equals(@Nullable Object obj) { return super.equals(obj); }

    @SerializedName("results")
    @Expose
    List<Cinema> movies;
}

class Cinema {

    @SerializedName("title")
    @Expose
    String title;

    @SerializedName("address")
    @Expose
    String address;

    @SerializedName("phone")
    @Expose
    String phone;

    @SerializedName("subway")
    @Expose
    String subway;

    @SerializedName("images")
    @Expose
    List<Image> listImages;

    @SerializedName("datetime")
    @Expose
    Timestamp dateTime;

    @SerializedName("price")
    @Expose
    String price;
}
