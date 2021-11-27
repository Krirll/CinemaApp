package com.krirll.cinemaapp.network.models;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieModel {

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    @SerializedName("results")
    @Expose
    List<Movie> movies;
}

class Movie {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("title")
    @Expose
    String title;

    @SerializedName("body_text")
    @Expose
    String description;

    @SerializedName("genres")
    @Expose
    List<Genres> genres;

    @SerializedName("country")
    @Expose
    String country;

    @SerializedName("age_restriction")
    @Expose
    String ageRest;

    @SerializedName("stars")
    @Expose
    String stars;

    @SerializedName("director")
    @Expose
    String director;

    @SerializedName("writer")
    @Expose
    String writer;

    @SerializedName("age_restriction")
    @Expose
    List<Image> listImages;

    @SerializedName("poster")
    @Expose
    Image poster;
}

class Genres {
    @SerializedName("name")
    @Expose
    String name;
}