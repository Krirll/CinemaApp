package com.krirll.cinemaapp.network;

import com.krirll.cinemaapp.network.models.CinemaModel;
import com.krirll.cinemaapp.network.models.MovieModel;

import java.security.Timestamp;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("?location=msk&page_size=50&fields=id,title,poster,genres,publication_date,title,body_text,country,age_restriction,stars,director,writer,images")
    Call<MovieModel> getMovies(@Query("actual_since") String time);

    @GET("{id}/showings/?location=msk&expand=place&page_size=100&fields=place,datetime,price")
    Call<CinemaModel> getCinemas(@Path("id") int id, @Query("actual_since") String time);
}
