package com.krirll.cinemaapp.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("image")
    @Expose
    String imageUrl;
}
