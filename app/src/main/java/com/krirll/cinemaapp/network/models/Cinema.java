package com.krirll.cinemaapp.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.security.Timestamp;
import java.util.List;

public class Cinema {

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

    @SerializedName("datetime")
    @Expose
    public Timestamp dateTime;

    @SerializedName("price")
    @Expose
    public String price;
}
