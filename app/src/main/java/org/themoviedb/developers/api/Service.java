package org.themoviedb.developers.api;

import androidx.cardview.widget.CardView;

import org.themoviedb.developers.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("apy_key")String apikey);

}
