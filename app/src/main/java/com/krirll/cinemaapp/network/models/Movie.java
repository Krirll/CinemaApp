package com.krirll.cinemaapp.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {

    @SerializedName("id")
    @Expose
    public int id;

    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("body_text")
    @Expose
    public String description;

    @SerializedName("genres")
    @Expose
    public List<Genres> genres;

    @SerializedName("country")
    @Expose
    public String country;

    @SerializedName("age_restriction")
    @Expose
    public String ageRest;

    @SerializedName("stars")
    @Expose
    public String stars;

    @SerializedName("director")
    @Expose
    public String director;

    @SerializedName("images")
    @Expose
    public List<Image> listImages;

    @SerializedName("poster")
    @Expose
    public Image poster;
}
