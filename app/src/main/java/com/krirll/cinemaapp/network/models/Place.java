package com.krirll.cinemaapp.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Place implements Serializable {

    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("address")
    @Expose
    public String address;

    @SerializedName("phone")
    @Expose
    public String phone;

    @SerializedName("subway")
    @Expose
    public String subway;

    @SerializedName("images")
    @Expose
    public List<Image> listImages;
}
