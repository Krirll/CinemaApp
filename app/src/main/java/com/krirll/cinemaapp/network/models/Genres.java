package com.krirll.cinemaapp.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Genres implements Serializable {

    @SerializedName("name")
    @Expose
    public String name;
}
